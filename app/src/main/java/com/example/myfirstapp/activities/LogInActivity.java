package com.example.myfirstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.User;
import com.example.myfirstapp.utils.PreferencesUtils;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity{

    User userReg;
    PreferencesUtils preferencesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencesUtils = new PreferencesUtils(this.getApplicationContext(), preferencesUtils.PREFS_NAME_GRAL);

        TextView txtUser = findViewById(R.id.user);
        TextView txtPassword = findViewById(R.id.password);
        TextView txtRecoveryPassword = findViewById(R.id.txtRecoveryPassword);
        TextView txtRegister = findViewById(R.id.txtRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userReg = preferencesUtils.getUser();

                User user = new User();
                user.setIdUser(txtUser.getText().toString());
                user.setPassword(txtPassword.getText().toString());

                if(user.getIdUser().equals(userReg.getIdUser()) && user.getPassword().equals(userReg.getPassword())){
                    Intent homeIntent = new Intent(LogInActivity.this, HomeActivity.class);
                    //homeIntent.putExtra("user", txtUser.getText());
                    //homeIntent.putExtra("pwd", txtPassword.getText());
                    //homeIntent.putExtra("user", user);
                    startActivity(homeIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Contrasena y/o usuario incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtRecoveryPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recoveryIntent = new Intent(LogInActivity.this, RecoveryActivity.class);
                startActivity(recoveryIntent);
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}
