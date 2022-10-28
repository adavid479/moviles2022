package com.example.myfirstapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.utils.PreferencesUtils;

import java.util.Map;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        PreferencesUtils cartPreferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_CART);
        for(Map.Entry<String, ?> entry: cartPreferencesUtils.getCart().entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
