package com.example.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.User;
import com.example.myfirstapp.utils.PreferencesUtils;


public class RegisterActivity extends AppCompatActivity {

    User user;
    PreferencesUtils preferencesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        preferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_GRAL);

        EditText txtUser = findViewById(R.id.txtUser);
        EditText txtPassword = findViewById(R.id.txtPassword);
        EditText txtName = findViewById(R.id.txtName);
        EditText txtEmail = findViewById(R.id.txtEmail);
        Button btnReg = findViewById(R.id.btnReg);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                user.setIdUser(txtUser.getText().toString());
                user.setPassword(txtPassword.getText().toString());
                user.setName(txtName.getText().toString());
                user.setEmail(txtEmail.getText().toString());
                preferencesUtils.setUser(user);
                Toast.makeText(getApplicationContext(), "Se registro el usuario correctamente", Toast.LENGTH_LONG).show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}
