package com.learn.project_wallz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.learn.project_wallz.ADAPTERS.LoadingP;
import com.learn.project_wallz.FRAGMENTS.AboutFragMents;
import com.learn.project_wallz.FRAGMENTS.FavouritesFragMent;
import com.learn.project_wallz.FRAGMENTS.HomeFragMent;
import com.learn.project_wallz.FRAGMENTS.SearchFragment;

public class MainActivity extends AppCompatActivity {
    ImageView Home;
    ImageView Search;
    ImageView Favorites;
    ImageView About;
    String[] Arr = {"android.permission.SET_WALLPAPER","android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE","android.permission.INTERNET"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(Arr,80);
        }

        Home = findViewById(R.id.HomeButon);
        Search = findViewById(R.id.SearchButon);
        Favorites = findViewById(R.id.FavoritesButon);
        About = findViewById(R.id.AboutButon);
        LoadingP loadingP = new LoadingP(MainActivity.this);
        Handler handler = new Handler();
        loadingP.startloading();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingP.loadstop();
            }
        },3000);
        beginfrag();
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragMent homeFragMent = new HomeFragMent();
                FragmentTransaction hometransaction = getSupportFragmentManager().beginTransaction();
                hometransaction.replace(R.id.ReplacebleLayout,homeFragMent);
                hometransaction.addToBackStack(null);
                hometransaction.commit();


            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction searchtransaction = getSupportFragmentManager().beginTransaction();
                searchtransaction.replace(R.id.ReplacebleLayout,searchFragment);
                searchtransaction.addToBackStack(null);
                searchtransaction.commit();

            }
        });
        Favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavouritesFragMent favouritesFragMent = new FavouritesFragMent();
                FragmentTransaction favtransaction = getSupportFragmentManager().beginTransaction();
                favtransaction.replace(R.id.ReplacebleLayout,favouritesFragMent);
                favtransaction.addToBackStack(null);
                favtransaction.commit();


            }
        });
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutFragMents aboutFragMents = new AboutFragMents();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.ReplacebleLayout,aboutFragMents);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

    }

    private void beginfrag() {
        HomeFragMent homeFragMent = new HomeFragMent();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ReplacebleLayout,homeFragMent);
        transaction.commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==80){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

            }else{

            }

        }
    }
}