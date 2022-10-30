package com.example.myfirstapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.CartProduct;
import com.example.myfirstapp.model.Product;
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

        cartProducts = new ArrayList<CartProduct>();
        movilesDataBase = new MovilesDataBase(getApplicationContext());
        initCartProducts();

        CartListAdapter cartListAdapter = new CartListAdapter(cartProducts);
        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(cartListAdapter);
        recyclerCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    public void initCartProducts(){
        PreferencesUtils cartPreferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_CART);
        for(Map.Entry<String, ?> entry: cartPreferencesUtils.getCart().entrySet()){
            //System.out.println(entry.getKey() + " " + entry.getValue());
            cartProducts.add(new CartProduct(movilesDataBase.getProduct(entry.getKey()), Integer.valueOf(entry.getValue().toString())));
        }
    }
}
