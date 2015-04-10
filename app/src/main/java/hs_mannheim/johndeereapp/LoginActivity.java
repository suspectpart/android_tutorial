package hs_mannheim.johndeereapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.AlwaysAuthenticated;
import com.example.Authenticator;


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
        Authenticator authenticator = new AlwaysAuthenticated();

        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if (authenticator.authenticate(username, password)) {
            mErrorMessage.setVisibility(View.INVISIBLE);

            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        } else {
            mErrorMessage.setText("Wrong username or password.");
            mErrorMessage.setVisibility(View.VISIBLE);

            mPassword.setText("");
        }
    }
}
