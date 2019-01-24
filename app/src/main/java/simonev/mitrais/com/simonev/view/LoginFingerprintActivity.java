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
