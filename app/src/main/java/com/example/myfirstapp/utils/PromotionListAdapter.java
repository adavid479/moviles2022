package com.example.myfirstapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Promotion;

import java.util.List;

public class PromotionListAdapter extends RecyclerView.Adapter<PromotionPalleteViewHolder> {

    private List<Promotion> data;

    public PromotionListAdapter(List data){
        this.data = data;
    }

    @NonNull
    @Override
    public PromotionPalleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_promotion, parent, false);
        return new PromotionPalleteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionPalleteViewHolder holder, int position) {
        Promotion promotion = data.get(position);
        holder.getTxtProducts().setText(promotion.getProducts().get(0).getName() + " + " + promotion.getProducts().get(1).getName());
        holder.getTxtPrice().setText("$ " + promotion.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
