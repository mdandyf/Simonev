package simonev.mitrais.com.simonev.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import simonev.mitrais.com.simonev.R;
import simonev.mitrais.com.simonev.contract.LoginFingerprintContract;
import simonev.mitrais.com.simonev.presenter.LoginFingerprintPresenter;

import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
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
        alertDialogExit("Confirm", "Are you sure to exit?");
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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
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

    private void alertDialogExit(String title, String message) {
        // make an alert dialog "are you sure to exit??"
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.create().show();
    }
}
