package com.mistershorr.databases;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    public static final String EXTRA_USERNAME = "login username";
    public static final String EXTRA_PASSWORD = "login password";
    public static final int REQUEST_CREATE_ACCOUNT = 1;

    private TextView textViewCreateAccount;
    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Backendless.initApp(this,Credentials.APP_ID,Credentials.API_KEY);

        wireWidgets();
        setListeners();
    }

    private void setListeners() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginToBackendless();
            }
        });

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO replace create account startActivity with startAcitivtyForResult
                Intent createAccountIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                createAccountIntent.putExtra(EXTRA_USERNAME, editTextUsername.getText().toString());
                createAccountIntent.putExtra(EXTRA_PASSWORD, editTextPassword.getText().toString());
//                startActivity(createAccountIntent);
                startActivityForResult(createAccountIntent, REQUEST_CREATE_ACCOUNT);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CREATE_ACCOUNT){
            //check if result is ok
            if(resultCode == RESULT_OK){
                editTextPassword.setText(data.getStringExtra(RegistrationActivity.EXTRA_PASSWORD));
                editTextUsername.setText(data.getStringExtra(RegistrationActivity.EXTRA_USERNAME));

            }

        }
    }

    private void loginToBackendless() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        if (!username.isEmpty() && !password.isEmpty()) {
            Toast.makeText(this, "You clicked login. Nothing happens.", Toast.LENGTH_SHORT).show();
            // do not forget to call Backendless.initApp in the app initialization code

            Backendless.UserService.login(username, password, new AsyncCallback<BackendlessUser>() {
                public void handleResponse(BackendlessUser user) {
                    // user has been logged in
                    Toast.makeText(LoginActivity.this,"welcome" + user.getProperty("username"),Toast.LENGTH_SHORT).show();
                }

                public void handleFault(BackendlessFault fault) {
                    // login failed, to get the error code call fault.getCode()
                    Toast.makeText(LoginActivity.this,fault.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this,"Enter a username and password.",Toast.LENGTH_SHORT).show();
        }
    }

    private void wireWidgets() {
        textViewCreateAccount = findViewById(R.id.textview_login_create_account);
        buttonLogin = findViewById(R.id.button_login_login);
        editTextUsername = findViewById(R.id.edit_text_login_username);
        editTextPassword = findViewById(R.id.edit_text_login_password);
    }
}
