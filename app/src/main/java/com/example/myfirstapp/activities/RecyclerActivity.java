package com.example.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.persistence.MovilesDataBase;
import com.example.myfirstapp.utils.ListAdapter;
import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private List<Product> products;
    MovilesDataBase movilesDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        movilesDataBase = new MovilesDataBase(getApplicationContext());
        products = movilesDataBase.getProducts();

        ListAdapter listAdapter = new ListAdapter(products);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        listAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailProductIntent = new Intent(RecyclerActivity.this, DetailProductActivity.class);
                detailProductIntent.putExtra("product", products.get(recyclerView.getChildAdapterPosition(view)));
                startActivity(detailProductIntent);
            }
        });

        ImageButton btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(RecyclerActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

    }

    public void initProducts(){
        products = new ArrayList<Product>();
        products.add(new Product("0", "Papa", 100.0));
        products.add(new Product("1", "Queso", 200.0));
        products.add(new Product("2", "Jamon", 300.0));
    }
}
