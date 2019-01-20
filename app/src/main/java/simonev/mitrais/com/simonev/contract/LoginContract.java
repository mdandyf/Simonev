package simonev.mitrais.com.simonev.contract;

import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import simonev.mitrais.com.simonev.model.Login;

public interface LoginContract {
    interface ViewAction{
        // actions done before the login process is executed
        void onLoginAttempt(@NonNull Login login);
        void onClearLoginTask();
    }

    interface LoginView extends RemoteViews.RemoteView {
        // actions done after the login process is executed
        void showEmpty();
        void showError(String component, String email);
        void showProgress(boolean state);
        String onShowErrorMessage(String errorType);
        void onLoginSuccess();
        void onLoginFailed();
    }
}
