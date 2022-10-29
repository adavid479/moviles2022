package com.example.myfirstapp.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfirstapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private SQLiteDatabase databaseW;
    private SQLiteDatabase databaseR;

    public ProductDAO(Context context){
        databaseW = new MovilesDataBase(context).getWritableDatabase();
        databaseR = new MovilesDataBase(context).getReadableDatabase();
    }

    public void addProduct(Product product){
        databaseW.execSQL("INSERT INTO Product VALUES ('" + product.getId() + "', '" + product.getName()
        + "', " + product.getPrice() + ", '" + product.getImgURL() + "')");
    }

    public List<Product> getProducts(){

        List<Product> products = new ArrayList<Product>();
        Product product;

        Cursor cursor = databaseR.rawQuery("SELECT * FROM Product", null);
        while(cursor.moveToNext()){
            product = new Product();
            product.setId(cursor.getString(1));
            product.setName(cursor.getString(2));
            product.setPrice(cursor.getDouble(3));
            product.setImgURL(cursor.getString(4));
            products.add(product);
        }
        return products;
    }

}
