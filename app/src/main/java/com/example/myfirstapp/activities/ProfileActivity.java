package com.example.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.User;
import com.example.myfirstapp.utils.PreferencesUtils;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    User user;
    PreferencesUtils preferencesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        preferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_GRAL);
        user = preferencesUtils.getUser();

        TextView txtUser = findViewById(R.id.txtUser);
        TextView txtPassword = findViewById(R.id.txtPassword);
        TextView txtEmail = findViewById(R.id.txtEmail);
        Button btnChangePwd = findViewById(R.id.btnChangePwd);

        txtUser.setText("Usuario: " + user.getIdUser());
        txtPassword.setText("Contrasena: " + user.getPassword());
        txtEmail.setText("E-Mail: " + user.getEmail());

        btnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentChangePwd = new Intent(ProfileActivity.this, ChangePwdActivity.class);
                startActivity(intentChangePwd);
            }
        });
    }
}