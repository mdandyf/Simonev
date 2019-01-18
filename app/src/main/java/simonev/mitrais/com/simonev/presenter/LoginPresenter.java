package simonev.mitrais.com.simonev.presenter;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;

import simonev.mitrais.com.simonev.contract.LoginContract;
import simonev.mitrais.com.simonev.dao.LoginDao;
import simonev.mitrais.com.simonev.dao.LoginDaoImplementation;
import simonev.mitrais.com.simonev.model.Login;
import simonev.mitrais.com.simonev.view.LoginActivity;
import simonev.mitrais.com.simonev.view.MainActivity;

public class LoginPresenter implements LoginContract.Presenter {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;
    private LoginContract.View loginView = new LoginActivity();

    @Override
    public void attach(LoginContract.View view) {

    }

    @Override
    public void detach() {

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @Override
    public void login(String email, String password) {
        if (mAuthTask != null) {
            return;
        }

        //reset error
        loginView.onViewCommand("resetError", "");

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginView.onViewCommand("passwordInvalid", "");
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            loginView.onViewCommand("emailRequired", "");
            cancel = true;
        } else if (!isEmailValid(email)) {
            loginView.onViewCommand("emailInvalid", "");
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    @Override
    public void clearAuthTask() {
        mAuthTask = null;
    }

    @Override
    public void startNewActivity() {
        loginView.startNewActivity(MainActivity.class);
    }

    @Override
    public void commandView(String command) {
        loginView.onViewCommand(command, "");
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
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
        private LoginPresenter presenter = new LoginPresenter();
        private LoginDao loginDao = new LoginDaoImplementation();

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

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            presenter.clearAuthTask();
            presenter.commandView("hideProgress");

            if (success) {
                presenter.startNewActivity();
            } else {
                presenter.commandView("passwordIncorrect");
            }
        }

        @Override
        protected void onCancelled() {
            presenter.clearAuthTask();
            presenter.commandView("hideProgress");
        }
    }

}
