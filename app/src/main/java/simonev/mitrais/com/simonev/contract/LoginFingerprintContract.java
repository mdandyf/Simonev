package simonev.mitrais.com.simonev.contract;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import simonev.mitrais.com.simonev.model.Login;
import simonev.mitrais.com.simonev.presenter.LoginFingerprintPresenter;

public interface LoginFingerprintContract {
    interface ViewAction{
        // actions done before the login process is executed
        void onCheckCompatibility(Context context, FingerprintManager fingerprintManager, KeyguardManager keyguardManager);
    }

    interface LoginView extends RemoteViews.RemoteView {
        // actions done after the login process is executed
        void showEmpty();
        String onShowErrorMessage(String errorType);
        void showError(String message);
        void onLoginSuccess();
        void onLoginError(String message);
        void onLoginFailed();
    }
}
