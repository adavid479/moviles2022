package com.example.myfirstapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.User;
import com.example.myfirstapp.utils.PreferencesUtils;

public class ChangePwdActivity extends AppCompatActivity {

    PreferencesUtils preferencesUtils;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        preferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_GRAL);
        user = preferencesUtils.getUser();

        EditText txtActual = findViewById(R.id.txtActual);
        EditText txtNew = findViewById(R.id.txtNew);
        Button btnChangePwd = findViewById(R.id.btnChangePwd);
        btnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtActual.getText().toString().equals(user.getPassword()))
                    Toast.makeText(getApplicationContext(), "Contrasena incorrecta", Toast.LENGTH_SHORT).show();
                else{
                    preferencesUtils.setUserPassword(txtNew.getText().toString());
                    Toast.makeText(getApplicationContext(), "Contrasena cambiada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
