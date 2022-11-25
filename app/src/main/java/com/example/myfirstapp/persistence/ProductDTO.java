package com.example.myfirstapp.persistence;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProductDTO{

    private HttpURLConnection connection;
    private URL url;
    private InputStream inputStream;
    private RequestQueue queue;

    public ProductDTO(){
    }

    /*private String convertStreamToString(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try{
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return stringBuilder.toString();
    }*/


}
