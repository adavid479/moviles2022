package com.example.myfirstapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Product;

public class DetailProductActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        TextView txtId = findViewById(R.id.txtId);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtPrice = findViewById(R.id.txtPrice);

        savedInstanceState = getIntent().getExtras();
        if(savedInstanceState!=null){
            product = (Product) savedInstanceState.get("product");
            txtId.setText("ID: " + product.getId());
            txtName.setText("PRODUCTO: " + product.getName());
            txtPrice.setText("PRECIO: $" + product.getPrice());
        }
    }
}
