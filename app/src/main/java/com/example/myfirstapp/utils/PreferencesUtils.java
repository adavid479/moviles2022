package com.example.myfirstapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myfirstapp.model.Product;
import com.example.myfirstapp.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PreferencesUtils {

    public static final String PREFS_NAME_GRAL = "com.example.myfirstapp.prefs";
    public static final String PREFS_NAME_CART = "com.example.myfirstapp.cart";
    private Context context;
    private SharedPreferences sharedPreferences;

    private static final String USER_ID = "user_id";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_NAME = "user_name";
    private static final String USER_EMAIL = "user_email";

    public PreferencesUtils(Context context, String prefsName){
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
    }

    public void setUser(User user){
        sharedPreferences.edit().putString(USER_ID, user.getIdUser()).apply();
        sharedPreferences.edit().putString(USER_PASSWORD, user.getPassword()).apply();
        sharedPreferences.edit().putString(USER_NAME, user.getName()).apply();
        sharedPreferences.edit().putString(USER_EMAIL, user.getEmail()).apply();
    }

    public User getUser(){
        User user = new User();
        user.setIdUser(sharedPreferences.getString(USER_ID, ""));
        user.setPassword(sharedPreferences.getString(USER_PASSWORD, ""));
        user.setName(sharedPreferences.getString(USER_NAME, ""));
        user.setEmail(sharedPreferences.getString(USER_EMAIL, ""));
        return user;
    }

    public void setUserPassword(String userPassword){
        sharedPreferences.edit().putString(USER_PASSWORD, userPassword).apply();
    }

    public void addProductToCart(String product, Integer quantity){
        quantity += sharedPreferences.getInt(product, 0);
        sharedPreferences.edit().putInt(product, quantity).apply();
    }

    public void showAll(){
        System.out.println(sharedPreferences.getAll());
    }

    public Map<String, ?> getCart(){
        return sharedPreferences.getAll();
    }

    public void deleteProductCart(String idProduct){
        sharedPreferences.edit().remove(idProduct);
    }

    public void emptyCart(){
        sharedPreferences.edit().clear().apply();
    }

}
