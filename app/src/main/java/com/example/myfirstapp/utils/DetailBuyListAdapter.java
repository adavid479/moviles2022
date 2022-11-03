package com.example.myfirstapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.DetailBuy;

import java.util.List;

public class DetailBuyListAdapter extends RecyclerView.Adapter<DetailBuyPalleteViewHolder> {

    private List<DetailBuy> data;

    public DetailBuyListAdapter(List data){
        this.data = data;
    }

    @NonNull
    @Override
    public DetailBuyPalleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_buy, parent, false);
        return new DetailBuyPalleteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailBuyPalleteViewHolder holder, int position) {
        DetailBuy detailBuy = data.get(position);
        Double sub = detailBuy.getProduct().getPrice() * detailBuy.getQuantity();
        holder.getTxtProduct().setText(detailBuy.getProduct().getName());
        holder.getTxtPrice().setText(detailBuy.getProduct().getPrice().toString());
        holder.getTxtQuantity().setText(detailBuy.getQuantity().toString());
        holder.getTxtSub().setText(sub.toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
