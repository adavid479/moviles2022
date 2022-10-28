package com.example.myfirstapp.utils;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<PalleteViewHolder> implements View.OnClickListener{

    private View.OnClickListener onClickListener;

    private List<Product> data;

    PreferencesUtils preferencesCartUtils;

    public ListAdapter(@NonNull List<Product> data){
        this.data = data;
    }

    @NonNull
    @Override
    public PalleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product, parent, false);
        row.setOnClickListener(this);

        preferencesCartUtils = new PreferencesUtils(parent.getContext(), PreferencesUtils.PREFS_NAME_CART);

        return new PalleteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PalleteViewHolder holder, int position) {
        Product product = data.get(position);
        holder.getTxtIdProduct().setText(product.getId());
        holder.getTxtNameProduct().setText(product.getName());
        holder.getTxtPriceProduct().setText(product.getPrice().toString());
        holder.getBtnAddToCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesCartUtils.addProductToCart(product.getId(), 1);
                /*System.out.println(product.getName());
                preferencesCartUtils.setProductCart(null);
                preferencesCartUtils.getProductCart();
                preferencesCartUtils.showAll();*/
                preferencesCartUtils.showAll();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View view) {
        if(onClickListener != null)
            onClickListener.onClick(view);
    }
}
