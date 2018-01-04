package com.example.suneetsrivastava.task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageViewFull extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        imageView = (ImageView) findViewById(R.id.resizedImage);
        Intent i =getIntent();
        String uri= i.getStringExtra("uri");
        Picasso.with(this).load(uri).into(imageView);
    }
}
