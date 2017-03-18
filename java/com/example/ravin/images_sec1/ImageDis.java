package com.example.ravin.images_sec1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageDis extends AppCompatActivity {

    ImageView imageView;
    ArrayList<String>  arrayList;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_dis);
        int path=getIntent().getIntExtra("path",0);
        imageView=(ImageView)findViewById(R.id.image1);
        arrayList=new ArrayList<>();
        arrayList=MainActivity.arrayList;
        Glide.with(ImageDis.this).load(arrayList.get(path)).into(imageView);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        i++;
        Toast.makeText(this, "press two time to exit", Toast.LENGTH_SHORT).show();
        if(i==2) {
            startActivity(new Intent(ImageDis.this, MainActivity.class));
            Toast.makeText(this, "backed", Toast.LENGTH_SHORT).show();
        }

    }
}
