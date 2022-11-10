package com.example.myfirstapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Buy;
import com.example.myfirstapp.model.DetailBuy;
import com.example.myfirstapp.persistence.MovilesDataBase;
import com.example.myfirstapp.utils.DetailBuyListAdapter;

import java.util.List;

public class DetailBuyActivity extends AppCompatActivity {

    private List<DetailBuy> detailBuys;
    private MovilesDataBase movilesDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buy);

        movilesDataBase = new MovilesDataBase(this);

        Buy buy = null;
        savedInstanceState = getIntent().getExtras();
        if(savedInstanceState!=null){
            buy = (Buy) savedInstanceState.get("buy");
        }

        TextView txtId = findViewById(R.id.txtId);
        TextView txtDate = findViewById(R.id.txtDate);
        TextView txtTotal = findViewById(R.id.txtTotal);

        txtId.setText("Numero de compra: " + buy.getIdBuy().toString());
        txtDate.setText("Fecha: " + buy.getIdDate());
        txtTotal.setText("Total: $" + buy.getTotal().toString());

        detailBuys = movilesDataBase.getDetailBuys(buy.getIdBuy().toString());

        DetailBuyListAdapter detailBuyListAdapter = new DetailBuyListAdapter(detailBuys);
        RecyclerView recyclerView = findViewById(R.id.recyclerDetailBuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(detailBuyListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
