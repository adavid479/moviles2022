package com.example.myfirstapp.utils;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;

public class PalleteViewHolder extends RecyclerView.ViewHolder{
    private TextView txtIdProduct;
    private TextView txtNameProduct;
    private TextView txtPriceProduct;
    private ImageButton btnAddToCart;

    public PalleteViewHolder(View itemView){
        super(itemView);
        //txtIdProduct = itemView.findViewById(R.id.txtIdProduct);
        txtNameProduct = itemView.findViewById(R.id.txtProduct);
        txtPriceProduct = itemView.findViewById(R.id.txtPrice);
        btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
    }

    public TextView getTxtIdProduct() {
        return txtIdProduct;
    }

    public TextView getTxtNameProduct() {
        return txtNameProduct;
    }

    public TextView getTxtPriceProduct() {
        return txtPriceProduct;
    }

    public ImageButton getBtnAddToCart(){
        return btnAddToCart;
    }
}
