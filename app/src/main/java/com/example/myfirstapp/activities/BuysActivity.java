package com.example.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.persistence.MovilesDataBase;
import com.example.myfirstapp.utils.BuyListAdapter;

public class BuysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buys);

        MovilesDataBase movilesDataBase = new MovilesDataBase(getApplicationContext());

        BuyListAdapter buyListAdapter = new BuyListAdapter(movilesDataBase.getBuys());
        RecyclerView recyclerView = findViewById(R.id.recyclerBuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(buyListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        buyListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("hola");
                Intent detailBuyIntent = new Intent(BuysActivity.this, DetailBuyActivity.class);
                startActivity(detailBuyIntent);
            }
        });
    }
}
