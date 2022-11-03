package com.example.myfirstapp.utils;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;

public class DetailBuyPalleteViewHolder extends RecyclerView.ViewHolder {

    private TextView txtProduct;
    private TextView txtPrice;
    private TextView txtQuantity;
    private TextView txtSub;

    public DetailBuyPalleteViewHolder(View itemView){
        super(itemView);
        txtProduct = itemView.findViewById(R.id.txtProduct);
        txtPrice = itemView.findViewById(R.id.txtPrice);
        txtQuantity = itemView.findViewById(R.id.txtQuantity);
        txtSub = itemView.findViewById(R.id.txtSub);
    }

    public TextView getTxtProduct() {
        return txtProduct;
    }

    public TextView getTxtPrice() {
        return txtPrice;
    }

    public TextView getTxtQuantity() {
        return txtQuantity;
    }

    public TextView getTxtSub() {
        return txtSub;
    }
}
