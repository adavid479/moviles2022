package com.example.myfirstapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.CartProduct;
import com.example.myfirstapp.model.Product;
import com.example.myfirstapp.utils.CartListAdapter;
import com.example.myfirstapp.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private List<CartProduct> cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initCartProducts();

        CartListAdapter cartListAdapter = new CartListAdapter(cartProducts);
        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(cartListAdapter);
        recyclerCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        PreferencesUtils cartPreferencesUtils = new PreferencesUtils(this.getApplicationContext(), PreferencesUtils.PREFS_NAME_CART);
        for(Map.Entry<String, ?> entry: cartPreferencesUtils.getCart().entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void initCartProducts(){
        cartProducts = new ArrayList<CartProduct>();
        cartProducts.add(new CartProduct(new Product("0", "Papa", 100.0), 5));
        cartProducts.add(new CartProduct(new Product("1", "Queso", 200.0), 3));
        cartProducts.add(new CartProduct(new Product("2", "Jamon", 300.0), 1));
    }
}
