package com.example.myfirstapp.utils;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;

public class BuyPalleteViewHolder extends RecyclerView.ViewHolder {

    private TextView txtId;
    private TextView txtDate;
    private TextView txtTotal;

    public BuyPalleteViewHolder(@NonNull View itemView) {
        super(itemView);
        txtId = itemView.findViewById(R.id.txtId);
        txtDate = itemView.findViewById(R.id.txtDate);
        txtTotal = itemView.findViewById(R.id.txtTotal);
    }

    public TextView getTxtId() {
        return txtId;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtTotal() {
        return txtTotal;
    }
}
