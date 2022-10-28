package com.example.myfirstapp.utils;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;

public class PalleteViewHolder extends RecyclerView.ViewHolder{
    private TextView txtIdProduct;
    private TextView txtNameProduct;
    private TextView txtPriceProduct;
    private Button btnAddToCart;

    public PalleteViewHolder(View itemView){
        super(itemView);
        txtIdProduct = itemView.findViewById(R.id.txtIdProduct);
        txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
        txtPriceProduct = itemView.findViewById(R.id.txtPriceProduct);
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

    public Button getBtnAddToCart(){
        return btnAddToCart;
    }
}
