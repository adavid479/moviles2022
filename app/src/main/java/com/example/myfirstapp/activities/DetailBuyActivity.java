package com.example.myfirstapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
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

        String idBuy = null;
        savedInstanceState = getIntent().getExtras();
        if(savedInstanceState!=null){
            idBuy = savedInstanceState.getString("idBuy");
        }

        System.out.println(idBuy);

        idBuy = String.valueOf(idBuy);

        detailBuys = movilesDataBase.getDetailBuys(idBuy);

        DetailBuyListAdapter detailBuyListAdapter = new DetailBuyListAdapter(detailBuys);
        RecyclerView recyclerView = findViewById(R.id.recyclerDetailBuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(detailBuyListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
