package com.example.myfirstapp.utils;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;

public class CartPalleteViewHolder extends RecyclerView.ViewHolder {

    private TextView txtIdProduct;
    private TextView txtNameProduct;
    private TextView txtPriceProduct;
    private TextView txtQuantityProduct;
    private TextView txtSubProduct;
    private ImageButton btnDelete;

    public CartPalleteViewHolder(View itemView) {
        super(itemView);
        txtIdProduct = itemView.findViewById(R.id.txtIdProduct);
        txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
        txtPriceProduct = itemView.findViewById(R.id.txtPriceProduct);
        txtQuantityProduct = itemView.findViewById(R.id.txtQuantityProduct);
        txtSubProduct = itemView.findViewById(R.id.txtSubProduct);
        btnDelete = itemView.findViewById(R.id.btnDelete);
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

    public TextView getTxtQuantityProduct() {
        return txtQuantityProduct;
    }

    public TextView getTxtSubProduct() {
        return txtSubProduct;
    }

    public ImageButton getBtnDelete() {
        return btnDelete;
    }
}
