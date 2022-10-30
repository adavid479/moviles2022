package com.example.myfirstapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.CartProduct;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartPalleteViewHolder>{

    private List<CartProduct> data;

    PreferencesUtils preferencesCartUtils;

    public CartListAdapter(@NonNull List data){
        this.data = data;
    }

    @NonNull
    @Override
    public CartPalleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cart, parent, false);
        preferencesCartUtils = new PreferencesUtils(parent.getContext(), PreferencesUtils.PREFS_NAME_CART);
        return new CartPalleteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CartPalleteViewHolder holder, int position) {
        CartProduct cartProduct = data.get(position);
        Double sub = cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
        holder.getTxtIdProduct().setText("id:" + cartProduct.getProduct().getId());
        holder.getTxtNameProduct().setText(cartProduct.getProduct().getName());
        holder.getTxtPriceProduct().setText("$" + cartProduct.getProduct().getPrice().toString());
        holder.getTxtQuantityProduct().setText("cant.: " + cartProduct.getQuantity().toString());
        holder.getTxtSubProduct().setText("sub: $" + sub.toString());
        holder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesCartUtils.deleteProductCart(cartProduct.getProduct().getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
