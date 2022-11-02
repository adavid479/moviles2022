package com.example.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.User;
import com.example.myfirstapp.utils.PreferencesUtils;

public class HomeActivity extends AppCompatActivity {

    User user;
    PreferencesUtils preferencesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_GRAL);
        user = preferencesUtils.getUser();

        TextView txtUser = findViewById(R.id.txtUser);

        txtUser.setText(user.getIdUser());

        Button btnRecycler = findViewById(R.id.btnRecycler);
        Button btnAcelerometer = findViewById(R.id.btnAcelerometer);
        ImageButton btnUser = findViewById(R.id.btnUser);
        Button btnAddProduct = findViewById(R.id.btnAddProduct);
        Button btnBuys = findViewById(R.id.btnBuys);

        btnRecycler.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent recyclerIntent = new Intent(HomeActivity.this, RecyclerActivity.class);
                startActivity(recyclerIntent);
            }
        });

        btnAcelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent acelerometerIntent = new Intent(HomeActivity.this, AcelerometerActivity.class);
                startActivity(acelerometerIntent);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addProductIntent = new Intent(HomeActivity.this, AddProductActivity.class);
                startActivity(addProductIntent);
            }
        });

        btnBuys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buysIntent = new Intent(HomeActivity.this, BuysActivity.class);
                startActivity(buysIntent);
            }
        });
    }
}
