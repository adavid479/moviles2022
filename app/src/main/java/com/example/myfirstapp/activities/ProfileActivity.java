package com.example.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_GRAL);
        user = preferencesUtils.getUser();

        TextView txtUser = findViewById(R.id.txtUser);
        TextView txtPassword = findViewById(R.id.txtPassword);
        TextView txtEmail = findViewById(R.id.txtEmail);
        Button btnChangePwd = findViewById(R.id.btnChangePwd);
        ImageButton imgBtnUser = findViewById(R.id.imgBtnUser);

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

        imgBtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(ProfileActivity.this, PhotoActivity.class);
                startActivity(photoIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                ProfileActivity.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
