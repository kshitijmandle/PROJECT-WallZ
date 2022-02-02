package com.learn.project_wallz.ACTIVITIES;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.learn.project_wallz.DataBase.Mydatabase;
import com.learn.project_wallz.R;

import java.io.File;
import java.io.IOException;

public class ClikedActivity extends AppCompatActivity {
    TextView SetWallpaper;
    ImageView like;
    ImageView download;
    Mydatabase db;
    String URL_IMG;
    String URL_DESC;
    ImageView imageView;
    WallpaperManager manager;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliked);
        db = new Mydatabase(getApplicationContext());
        URL_IMG = getIntent().getStringExtra("WALLPAPER");
        URL_DESC = getIntent().getStringExtra("WALLPAPER_INFO");
        imageView = findViewById(R.id.setwallimgview);
        TextView imagedecription = findViewById(R.id.imagedescription);
        imagedecription.setText(URL_DESC);
        Glide.with(getApplicationContext()).load(URL_IMG).into(imageView);
        ImageView back = findViewById(R.id.backbuuten);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SetWallpaper = findViewById(R.id.setwallpaper);
        SetWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                manager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    Toast.makeText(getApplicationContext(), " WallPaper Sets SuccessFull ! ", Toast.LENGTH_SHORT).show();
                    manager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });
        like = findViewById(R.id.likebutten);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addNewCourse(URL_IMG,URL_DESC);
                Toast.makeText(getApplicationContext(), "Added To Favorites", Toast.LENGTH_SHORT).show();

            }
        });
        download = findViewById(R.id.dowloadbutten);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newImageDownload(URL_DESC,URL_IMG);
            }
        });
    }


    public void newImageDownload(String name , String url){

            DownloadManager downloadManager = null;
            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri download = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(download);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(name)
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,File.separator+name+".jpg");
            downloadManager.enqueue(request);
            Toast.makeText(getApplicationContext(), "Image Downloaded", Toast.LENGTH_SHORT).show();



    }
}