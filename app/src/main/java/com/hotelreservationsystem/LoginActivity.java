package com.hotelreservationsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login, register;
    CheckBox rememberme;
    TextView forgotpassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        sharedPreferences = getSharedPreferences("Userinfo", 0);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        rememberme = findViewById(R.id.remember);
        forgotpassword = findViewById(R.id.forgotpassword);

        if(sharedPreferences.getBoolean("rememberme",false)){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                String usernameValue = email.getText().toString();
                String passwordValue = password.getText().toString();

                String registeredUsername = sharedPreferences.getString("email", "");
                String registeredPassword = sharedPreferences.getString("password", "");
                if (usernameValue.equals(registeredUsername) &&
                        passwordValue.equals(registeredPassword)) {
                if(rememberme.isChecked()){
                    sharedPreferences.edit().putBoolean("rememberme",true).commit();
                }
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                } else
                    Toast.makeText(LoginActivity.this, "Invalid username or password:",
                            Toast.LENGTH_LONG).show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
