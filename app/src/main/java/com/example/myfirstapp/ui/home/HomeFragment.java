package com.example.myfirstapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfirstapp.R;
import com.example.myfirstapp.activities.ProfileActivity;
import com.example.myfirstapp.databinding.FragmentHomeBinding;
import com.example.myfirstapp.model.Product;
import com.example.myfirstapp.model.Promotion;
import com.example.myfirstapp.persistence.MovilesDataBase;
import com.example.myfirstapp.persistence.ProductDTO;
import com.example.myfirstapp.utils.PromotionListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    //private ProductDTO productDTO;
    RequestQueue queue;
    List<Promotion> promotions;
    MovilesDataBase movilesDataBase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        movilesDataBase = new MovilesDataBase(getContext());

        //queue = Volley.newRequestQueue(getContext());
        //queue.add(setData(root));
        loadData();

        ImageView imgUser = binding.imgUser;
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(getContext(), ProfileActivity.class);
                startActivity(profileIntent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadData(){
        queue = Volley.newRequestQueue(getContext());
        queue.add(loadProducts());
        queue.add(loadPromotions());
    }

    private JsonObjectRequest loadProducts(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://mocki.io/v1/4a04a593-e4f3-4949-977f-64e9b05b1401", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    movilesDataBase.clearProducts();
                    JSONArray jsonArray = response.getJSONArray("products");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Product product = new Product();
                        product.setId(jsonObject.getString("id"));
                        product.setName(jsonObject.getString("name"));
                        product.setPrice(jsonObject.getDouble("price"));
                        movilesDataBase.addProduct(product);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return request;
    }

    public JsonObjectRequest loadPromotions(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://mocki.io/v1/3637a56e-34d3-4fbf-b332-63a22ca2a694", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("promotions");
                    promotions = new ArrayList<Promotion>();
                    for(int i = 0; i < jsonArray.length(); i++){
                        Promotion promotion = new Promotion();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        List<Product> products = new ArrayList<Product>();
                        JSONArray jsonArrayProducts = jsonObject.getJSONArray("products");
                        for(int j = 0; j < jsonArrayProducts.length(); j++){
                            String idProduct = jsonArrayProducts.getJSONObject(j).getString("id");
                            Product product = movilesDataBase.getProduct(idProduct);
                            System.out.println(product.getName());
                            products.add(product);
                        }
                        Integer id = jsonObject.getInt("id");
                        Double price = jsonObject.getDouble("price");
                        promotion.setIdPromotion(id);
                        promotion.setPrice(price);
                        promotion.setProducts(products);
                        promotions.add(promotion);
                    }
                    PromotionListAdapter promotionListAdapter = new PromotionListAdapter(promotions);
                    RecyclerView recyclerView = binding.recyclerPromotion;
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(promotionListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        return request;
    }
}