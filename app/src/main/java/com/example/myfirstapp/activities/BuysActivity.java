package com.example.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Buy;
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

        buyListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtId = view.findViewById(R.id.txtId);
                TextView txtDate = view.findViewById(R.id.txtDate);
                TextView txtTotal = view.findViewById(R.id.txtTotal);
                Buy buy = new Buy();
                buy.setIdBuy(Integer.valueOf(txtId.getText().toString().substring(4)));
                buy.setIdDate(txtDate.getText().toString());
                buy.setTotal(Double.valueOf(txtTotal.getText().toString().substring(2)));
                Intent detailBuyIntent = new Intent(BuysActivity.this, DetailBuyActivity.class);
                detailBuyIntent.putExtra("buy", buy);
                startActivity(detailBuyIntent);
            }
        });
    }
}
