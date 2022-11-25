package com.example.myfirstapp.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;

public class PromotionPalleteViewHolder extends RecyclerView.ViewHolder {

    private TextView txtProducts;
    private ImageView imgProduct1;
    private ImageView imgProduct2;
    private TextView txtPrice;

    public PromotionPalleteViewHolder(@NonNull View itemView) {
        super(itemView);
        txtProducts = itemView.findViewById(R.id.txtProducts);
        imgProduct1 = itemView.findViewById(R.id.imgProduct1);
        imgProduct2 = itemView.findViewById(R.id.imgProduct2);
        txtPrice = itemView.findViewById(R.id.txtPrice);
    }

    public TextView getTxtProducts() {
        return txtProducts;
    }

    public ImageView getImgProduct1() {
        return imgProduct1;
    }

    public ImageView getImgProduct2() {
        return imgProduct2;
    }

    public TextView getTxtPrice() {
        return txtPrice;
    }
}
