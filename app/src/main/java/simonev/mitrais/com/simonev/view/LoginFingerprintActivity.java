package simonev.mitrais.com.simonev.view;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import simonev.mitrais.com.simonev.R;
import simonev.mitrais.com.simonev.contract.LoginFingerprintContract;
import simonev.mitrais.com.simonev.presenter.LoginFingerprintPresenter;

import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Annotation;

public class LoginFingerprintActivity extends AppCompatActivity implements LoginFingerprintContract.LoginView {

    private LoginFingerprintPresenter presenter;

    @BindView(R.id.textviewMessage) TextView textView;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fingerprint);
        ButterKnife.bind(this);
        presenter = new LoginFingerprintPresenter();
        presenter.attachView(this);

        keyguardManager =
                (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager =
                (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        presenter.onCheckCompatibility(getApplicationContext(), fingerprintManager, keyguardManager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showEmpty() {
        textView.setText("");
    }

    @Override
    public String onShowErrorMessage(String errorType) {
        switch (errorType) {
            case "deviceOSVersion":
                return "Your device does not support fingerprint";
            case "deviceFingerprintSensor":
                return "Your device doesn't support fingerprint authentication";
            case "checkSelfPermission":
                return "Please enable the fingerprint permission";
            case "checkEnrolledFingerprint":
                return "No fingerprint configured. Please register at least one fingerprint" +
                        " in your device's Settings";
            case "checkLockScreen":
                return "Please enable lockscreen security in your device's Settings";
        }
        return null;
    }

    @Override
    public void showError(String message) {
        textView.setText(message);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Login using fingerprint Success!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, "Authentication error\n" + message, Toast.LENGTH_LONG).
                show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "Login using fingerprint Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
