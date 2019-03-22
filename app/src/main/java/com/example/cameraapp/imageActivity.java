package com.example.cameraapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class imageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

    }
    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo=Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

    public void getAndSet(View view) {
        ImageView imgView = (ImageView) findViewById(R.id.picView);
        TextInputEditText id=(TextInputEditText) findViewById(R.id.picID);
        SQLiteOpenHelper cameradb=new cameraDatabase(getApplicationContext());
        SQLiteDatabase db=cameradb.getWritableDatabase();
        String imId=id.getText().toString();
        Cursor cursor= db.query ("CAMERAIMAGES",
                new String[] {"IMAGE"},
                "_id = ?",
                new String[] {imId},
                null, null,null);
        if (cursor.moveToFirst()) {
            System.out.println("What happend");
            byte[] bytes = cursor.getBlob(0);
            Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);
            //Bitmap ph = scaleDownBitmap(bitmapImage, 110, getApplicationContext());
            imgView.setImageBitmap(bitmapImage);
        }
    }
}
