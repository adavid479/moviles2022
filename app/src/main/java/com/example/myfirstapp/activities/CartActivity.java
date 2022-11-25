package com.example.myfirstapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Buy;
import com.example.myfirstapp.model.CartProduct;
import com.example.myfirstapp.model.DetailBuy;
import com.example.myfirstapp.model.Product;
import com.example.myfirstapp.persistence.BuyDAO;
import com.example.myfirstapp.persistence.MovilesDataBase;
import com.example.myfirstapp.utils.CartListAdapter;
import com.example.myfirstapp.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private List<CartProduct> cartProducts;
    MovilesDataBase movilesDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartProducts = new ArrayList<CartProduct>();
        movilesDataBase = new MovilesDataBase(getApplicationContext());
        initCartProducts();

        CartListAdapter cartListAdapter = new CartListAdapter(cartProducts);
        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(cartListAdapter);
        recyclerCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Button btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total = 0;

                Buy buy = new Buy();
                buy.setIdDate("");
                buy.setDiscount(0.0);
                buy.setTotal(0.0);
                movilesDataBase.addBuy(buy);
                buy.setIdBuy(movilesDataBase.getLastIdBuy());

                //movilesDataBase.upgrade();

                for(CartProduct cp: cartProducts){
                    DetailBuy detailBuy = new DetailBuy();
                    detailBuy.setBuy(buy);
                    detailBuy.setProduct(cp.getProduct());
                    detailBuy.setQuantity(cp.getQuantity());
                    total += cp.getProduct().getPrice();
                    movilesDataBase.addDetailBuy(detailBuy);
                }

                buy.setTotal(total);
                buy.setIdBuy(movilesDataBase.getLastIdBuy());
                movilesDataBase.updateBuy(buy);

                for(Buy b: movilesDataBase.getBuys()){
                    System.out.println(b.getIdBuy() + " " + b.getIdDate() + " " + b.getDiscount() + " " + b.getTotal());
                }
                for(DetailBuy db: movilesDataBase.getDetailBuys()){
                    System.out.println(db.getBuy().getIdBuy() + " " + db.getProduct().getName() + " " + db.getQuantity());
                }
                System.out.println(movilesDataBase.getLastIdBuy());
            }
        });

    }

    public void initCartProducts(){
        PreferencesUtils cartPreferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_CART);
        for(Map.Entry<String, ?> entry: cartPreferencesUtils.getCart().entrySet()){
            //System.out.println(entry.getKey() + " " + entry.getValue());
            cartProducts.add(new CartProduct(movilesDataBase.getProduct(entry.getKey()), Integer.valueOf(entry.getValue().toString())));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                CartActivity.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
