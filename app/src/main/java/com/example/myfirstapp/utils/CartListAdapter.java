package com.example.myfirstapp.utils;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartPalleteViewHolder>{

    private List data;

    public CartListAdapter(@NonNull List data){
        this.data = data;
    }

    @NonNull
    @Override
    public CartPalleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CartPalleteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
