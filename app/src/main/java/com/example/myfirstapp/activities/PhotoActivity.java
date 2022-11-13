package com.example.myfirstapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.myfirstapp.BuildConfig;
import com.example.myfirstapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class PhotoActivity extends AppCompatActivity {

    ImageView imgUser;
    Button btnSelectImg;

    private static final int COD_SELECT = 1;
    private static final int COD_PHOTO = 2;

    private static final String DIR_PRODUCT_IMGS = "imgs/products";
    private String path;
    File imgFile;
    //Bitmap bitmap;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgUser = findViewById(R.id.imgUser);
        btnSelectImg = findViewById(R.id.btnSelectImg);
        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptionsDialog();
            }
        });
        Bitmap bitmap = BitmapFactory.decodeFile(getExternalFilesDir(Environment.DIRECTORY_PICTURES)+ "/user.jpg");
        imgUser.setImageBitmap(bitmap);
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imgBitmap = null;
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case COD_PHOTO:
                    imgBitmap = BitmapFactory.decodeFile(path);
                    imgUser.setImageBitmap(imgBitmap);
                    break;
                case COD_SELECT:
                    uri = data.getData();
                    imgUser.setImageURI(uri);
                    break;
            }
        }
    }

    private File createImage() throws IOException{
        String nameFile = "user.jpg";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File image = File.createTempFile(nameFile, ".jpg", dir);
        File image = new File(dir, nameFile);
        path = image.getAbsolutePath();
        return image;
    }

    private void showOptionsDialog(){
        CharSequence[] options = {"Tomar foto", "Elegir imagen", "Cancelar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione una opcion");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(options[i].equals("Tomar foto")){
                    openCamera();
                }else if(options[i].equals("Elegir imagen")){
                    selectImg();
                }else{
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    private void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imgFile = null;
        try{
            imgFile = createImage();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        if(imgFile!=null){
            Uri imgUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".fileprovider", imgFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        }
        startActivityIfNeeded(cameraIntent, COD_PHOTO);
    }

    private void selectImg(){
        Intent mediaIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mediaIntent.setType("image/");
        startActivityIfNeeded(Intent.createChooser(mediaIntent, "Seleccione la aplicacion"), COD_SELECT);
    }

    private void selectImg1(){
        Intent mediaIntent = new Intent(Intent.ACTION_PICK);
        File imgFile = null;
        try{
            imgFile = createImage();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        if(imgFile!=null){
            Uri imgUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".fileprovider", imgFile);
            mediaIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        }
        startActivityIfNeeded(mediaIntent, COD_SELECT);
    }

    private String getRealPathFromURI(Uri contentUri){
        String result;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if(cursor == null){
            result = contentUri.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void saveImage(){
        int bufferSize = 1024;
        try{
            InputStream input = getContentResolver().openInputStream(uri);
            File storagePath = Environment.getExternalStorageDirectory();
            OutputStream output = new FileOutputStream(getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/user.jpg");
            byte[] buffer = new byte[bufferSize];
            int bytesRead = 0;
            while((bytesRead = input.read(buffer, 0, buffer.length)) >= 0){
                output.write(buffer, 0, bytesRead);
            }
            output.close();
            input.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
