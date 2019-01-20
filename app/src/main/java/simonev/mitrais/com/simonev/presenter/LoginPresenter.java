package simonev.mitrais.com.simonev.presenter;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import simonev.mitrais.com.simonev.contract.LoginContract;
import simonev.mitrais.com.simonev.dao.LoginDao;
import simonev.mitrais.com.simonev.dao.LoginDaoImplementation;
import simonev.mitrais.com.simonev.model.Login;
import simonev.mitrais.com.simonev.view.LoginActivity;
import simonev.mitrais.com.simonev.view.MainActivity;

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
        } else if(isUserNameValid(login.getUsername())) {
            getView().showError("email", getView().onShowErrorMessage("emailInvalid"));
            success = false;
        }

        if(!success) {
           getView().onLoginFailed();
        } else {
            getView().showProgress(true);
            mAuthTask = new UserLoginTask(login.getUsername(), login.getPassword());
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

    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;
        private LoginDao loginDao = new LoginDaoImplementation();
        private LoginPresenter presenter = new LoginPresenter();

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
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
            presenter.onClearLoginTask();
            presenter.getView().showProgress(false);

            if (success) {
                presenter.getView().onLoginSuccess();
            } else {
                presenter.getView().showError("password", presenter.getView().onShowErrorMessage("passwordIncorect"));
            }
        }

        @Override
        protected void onCancelled() {
            presenter.onClearLoginTask();
            presenter.getView().showProgress(false);
        }
    }

}
