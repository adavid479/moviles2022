package com.example.myfirstapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Product;
import com.example.myfirstapp.persistence.MovilesDataBase;
import com.example.myfirstapp.persistence.ProductDAO;

public class AddProductActivity extends AppCompatActivity {

    MovilesDataBase movilesDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        movilesDataBase = new MovilesDataBase(getApplicationContext());

        EditText txtId = findViewById(R.id.txtId);
        EditText txtName = findViewById(R.id.txtName);
        EditText txtPrice = findViewById(R.id.txtPrice);
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();
                product.setId(txtId.getText().toString());
                product.setName(txtName.getText().toString());
                product.setPrice(Double.valueOf(txtPrice.getText().toString()));
                movilesDataBase.addProduct(product);

                for(Product p: movilesDataBase.getProducts()){
                    System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
                }
            }
        });
    }
}
