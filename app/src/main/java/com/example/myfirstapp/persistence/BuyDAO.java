package com.example.myfirstapp.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.myfirstapp.model.Buy;

public class BuyDAO {

    MovilesDataBase movilesDataBase;
    SQLiteDatabase databaseW;
    SQLiteDatabase databaseR;

    public BuyDAO(Context context){
        movilesDataBase = new MovilesDataBase(context);
        databaseW = movilesDataBase.getWritableDatabase();
        databaseR = movilesDataBase.getReadableDatabase();
    }

    public void addBuy(Buy buy){
        String[] selectionArgs = {buy.getIdBuy().toString(), buy.getIdDate(), buy.getDiscount().toString(), buy.getTotal().toString()};
        databaseW.rawQuery("INSERT INTO Buy VALUES(?, ?, ?, ?)", selectionArgs);
    }

    public List<Buy> getBuys(){
        List<Buy> buys = new ArrayList<Buy>();
        Cursor cursor = databaseR.rawQuery("SELECT * FROM Buy", null);
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

}
