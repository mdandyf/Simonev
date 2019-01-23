package simonev.mitrais.com.simonev.presenter;

import android.os.AsyncTask;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import simonev.mitrais.com.simonev.contract.LoginContract;
import simonev.mitrais.com.simonev.dao.LoginDao;
import simonev.mitrais.com.simonev.dao.implementation.LoginDaoImplementation;
import simonev.mitrais.com.simonev.model.Login;

public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.ViewAction {

    private UserLoginTask mAuthTask = null;

    @Override
    public void onLoginAttempt(@NonNull Login login) {

        if(mAuthTask != null) {
            return;
        }

        getView().showEmpty();
        boolean success = true;

        // Check for a valid password, if the user entered one.
        if(!TextUtils.isEmpty(login.getUsername()) && !isPasswordValid(login.getPassword())) {
            getView().showError("password", getView().onShowErrorMessage("passwordInvalid"));
            success = false;
        }

        if(TextUtils.isEmpty(login.getUsername())) {
            getView().showError("email", getView().onShowErrorMessage("emailRequired"));
            success = false;
        } else if(!isUserNameValid(login.getUsername())) {
            getView().showError("email", getView().onShowErrorMessage("emailInvalid"));
            success = false;
        }

        if(!success) {
           getView().onLoginFailed();
        } else {
            getView().showProgress(true);
            mAuthTask = new UserLoginTask(login.getUsername(), login.getPassword(), new LoginDaoImplementation(), this, getView());
            mAuthTask.execute((Void) null);
        }
    }

    @Override
    public void onClearLoginTask() {
        mAuthTask = null;
    }

    private boolean isUserNameValid(String username) {
        return username.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */

    protected class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;
        private LoginDao loginDao;
        private LoginPresenter loginPresenter;
        private LoginContract.LoginView loginView;

        protected UserLoginTask(String email, String password, LoginDao loginDao, LoginPresenter loginPresenter, LoginContract.LoginView loginView) {
            this.mEmail = email;
            this.mPassword = password;
            this.loginDao = loginDao;
            this.loginPresenter = loginPresenter;
            this.loginView = loginView;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (Login credential : loginDao.findAll()) {
                if (credential.getUsername().equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return credential.getPassword().equals(mPassword);
                }
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            loginPresenter.onClearLoginTask();
            if (success) {
                loginView.onLoginSuccess();
            } else {
                loginView.showError("password", loginView.onShowErrorMessage("passwordIncorect"));
            }
        }

        @Override
        protected void onCancelled() {
            loginPresenter.onClearLoginTask();
        }
    }

}
