package com.example.myfirstapp.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myfirstapp.model.Buy;
import com.example.myfirstapp.model.DetailBuy;
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

        sqLiteDatabase.execSQL("CREATE TABLE Buy(" +
                "idBuy INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idDate DATE, " +
                "discount DOUBLE, " +
                "total DOUBLE)");

        sqLiteDatabase.execSQL("CREATE TABLE DetailBuy(" +
                "idBuy INTEGER PRIMARY KEY, " +
                "idProduct TEXT PRIMARY KEY, " +
                "quantity INTEGER, " +
                "FOREIGN KEY(idBuy) REFERENCES Buy(idBuy), " +
                "FOREIGN KEY(idProduct) REFERENCES Product(idProduct))");
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

    public void addBuy(Buy buy){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] selectionArgs = {buy.getDiscount().toString(), buy.getTotal().toString()};
        database.execSQL("INSERT INTO Buy (idDate, discount, total)VALUES(CURRENT_DATE, ?, ?)", selectionArgs);
    }

    public List<Buy> getBuys(){
        SQLiteDatabase database = this.getReadableDatabase();
        List<Buy> buys = new ArrayList<Buy>();
        Cursor cursor = database.rawQuery("SELECT * FROM Buy", null);
        while(cursor.moveToNext()){
            Buy buy = new Buy();
            buy.setIdBuy(cursor.getInt(0));
            buy.setIdDate(cursor.getString(1));
            buy.setDiscount(cursor.getDouble(2));
            buy.setTotal(cursor.getDouble(3));
            buys.add(buy);
        }
        return buys;
    }

    public Buy getBuy(Integer idBuy){
        SQLiteDatabase database = this.getReadableDatabase();
        String[] selectionArgs = {idBuy.toString()};
        Cursor cursor = database.rawQuery("SELECT * FROM Buy WHERE idBuy = ?", selectionArgs);
        while (cursor.moveToNext()){
            Buy buy = new Buy();
            buy.setIdBuy(cursor.getInt(0));
            buy.setIdDate(cursor.getString(1));
            buy.setDiscount(cursor.getDouble(2));
            buy.setTotal(cursor.getDouble(3));
            return buy;
        }
        return null;
    }

    public void addDetailBuy(DetailBuy detailBuy){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] bindArgs = {detailBuy.getBuy().getIdBuy().toString(), detailBuy.getProduct().getId(), detailBuy.getQuantity().toString()};
        database.execSQL("INSERT INTO DetailBuy VALUES(?, ? ,?)", bindArgs);
    }

    public Integer getLastIdBuy(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT MAX(idBuy) FROM Buy", null);
        while(cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return null;
    }

    public void updateBuy(Buy buy){
        SQLiteDatabase database = this.getWritableDatabase();
        String[] bindArgs = {buy.getDiscount().toString(), buy.getTotal().toString(), buy.getIdBuy().toString()};
        database.execSQL("UPDATE Buy SET discount = ?, total = ? WHERE idBuy = ?", bindArgs);
    }

    public List<DetailBuy> getDetailBuys(){
        SQLiteDatabase database = this.getReadableDatabase();
        List<DetailBuy> detailBuys = new ArrayList<DetailBuy>();
        Cursor cursor = database.rawQuery("SELECT * FROM DetailBuy", null);
        while(cursor.moveToNext()){
            DetailBuy detailBuy = new DetailBuy();
            detailBuy.setBuy(getBuy(cursor.getInt(0)));
            detailBuy.setProduct(getProduct(cursor.getString(1)));
            detailBuy.setQuantity(cursor.getInt(2));
            detailBuys.add(detailBuy);
        }
        return detailBuys;
    }

    public List<DetailBuy> getDetailBuys(String idBuy){
        SQLiteDatabase database = this.getReadableDatabase();
        List<DetailBuy> detailBuys = new ArrayList<DetailBuy>();
        String[] selectionArgs = {idBuy};
        Cursor cursor = database.rawQuery("SELECT * FROM DetailBuy WHERE idBuy = ?", selectionArgs);
        while(cursor.moveToNext()){
            DetailBuy detailBuy = new DetailBuy();
            detailBuy.setBuy(getBuy(cursor.getInt(0)));
            detailBuy.setProduct(getProduct(cursor.getString(1)));
            detailBuy.setQuantity(cursor.getInt(2));
            detailBuys.add(detailBuy);
        }
        return detailBuys;
    }

    public void upgrade(){
        this.getWritableDatabase().execSQL("DROP TABLE DetailBuy");
        this.getWritableDatabase().execSQL("CREATE TABLE DetailBuy(" +
                "idBuy INTEGER, " +
                "idProduct TEXT, " +
                "quantity INTEGER, " +
                "PRIMARY KEY(idBuy, idProduct), " +
                "FOREIGN KEY(idBuy) REFERENCES Buy(idBuy), " +
                "FOREIGN KEY(idProduct) REFERENCES Product(idProduct))");
    }

    public void clearProducts(){
        this.getWritableDatabase().execSQL("DELETE FROM Product");
    }
}
