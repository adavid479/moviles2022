package com.example.myfirstapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.model.Buy;

import java.util.List;

public class BuyListAdapter extends RecyclerView.Adapter<BuyPalleteViewHolder> implements View.OnClickListener{

    private View.OnClickListener onClickListener;

    private List<Buy> data;

    public BuyListAdapter(List data){
        this.data = data;
    }

    @NonNull
    @Override
    public BuyPalleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_buy, parent, false);
        row.setOnClickListener(this);
        return new BuyPalleteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyPalleteViewHolder holder, int position) {
        Buy buy = data.get(position);
        holder.getTxtId().setText("ID: " + buy.getIdBuy().toString());
        holder.getTxtDate().setText(buy.getIdDate());
        holder.getTxtTotal().setText("$" + buy.getTotal().toString());
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
        if(onClickListener!=null){
            onClickListener.onClick(view);
        }
    }
}
