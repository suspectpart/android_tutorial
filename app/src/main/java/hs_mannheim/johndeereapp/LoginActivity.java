package hs_mannheim.johndeereapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Authenticator;
import com.example.AuthenticatorImpl;


public class LoginActivity extends ActionBarActivity {

    private EditText mUsername;
    private EditText mPassword;

    private TextView mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);
    }

    public void onLoginClicked(View view) {
        Authenticator authenticator = new AuthenticatorImpl();

        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if (authenticator.authenticate(username, password)) {
            mErrorMessage.setVisibility(View.INVISIBLE);
        } else {
            mErrorMessage.setText("Wrong username or password.");
            mErrorMessage.setVisibility(View.VISIBLE);

            mPassword.setText("");
        }
    }
}
