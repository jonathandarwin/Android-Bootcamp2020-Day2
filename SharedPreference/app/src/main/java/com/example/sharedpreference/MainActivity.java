package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedpreference.model.User;

public class MainActivity extends AppCompatActivity {

    // Declare some object from our XML that wants to be manipulated here
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate the object value by using findViewById()
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        // Make a instance of shared preferences
        SharedPref sharedPref = new SharedPref(MainActivity.this);
        User user = sharedPref.load();

        // if the user that loaded from shared pref is not null, then intent to HomeActivity
        if(!user.getUsername().equals("") && !user.getPassword().equals("")){
            // Move to Home Activity
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please input username and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    // move to HomeActivity
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);

                    // save the user data in shared preferences
                    SharedPref sharedPref = new SharedPref(MainActivity.this);
                    sharedPref.save(user);

                    // source, destination
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
