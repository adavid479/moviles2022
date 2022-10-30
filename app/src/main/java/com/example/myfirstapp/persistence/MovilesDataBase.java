package com.example.myfirstapp.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myfirstapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MovilesDataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MovilesDataBase.db";

    public MovilesDataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Product(" +
                "idProduct TEXT PRIMARY KEY, " +
                "name TEXT, " +
                "price DOUBLE, " +
                "imgURL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addProduct(Product product){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("INSERT INTO Product VALUES ('" + product.getId() + "', '" + product.getName()
                + "', " + product.getPrice() + ", '" + product.getImgURL() + "')");
    }

    public List<Product> getProducts(){

        SQLiteDatabase database = this.getReadableDatabase();

        List<Product> products = new ArrayList<Product>();
        Product product;

        Cursor cursor = database.rawQuery("SELECT * FROM Product", null);
        while(cursor.moveToNext()){
            product = new Product();
            product.setId(cursor.getString(0));
            product.setName(cursor.getString(1));
            product.setPrice(cursor.getDouble(2));
            //product.setImgURL(cursor.getString(4));
            products.add(product);
        }
        return products;
    }

    public Product getProduct(String idProduct){
        Product product = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String[] selectionArgs = {idProduct};
        Cursor cursor = database.rawQuery("SELECT * FROM Product WHERE idProduct = ?", selectionArgs);
        while(cursor.moveToNext()){
            product = new Product();
            product.setId(cursor.getString(0));
            product.setName(cursor.getString(1));
            product.setPrice(cursor.getDouble(2));
        }
        return product;
    }
}
