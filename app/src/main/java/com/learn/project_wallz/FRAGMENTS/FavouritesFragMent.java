package com.learn.project_wallz.FRAGMENTS;

import static android.widget.Toast.makeText;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.learn.project_wallz.ADAPTERS.FavoritesAdapter;
import com.learn.project_wallz.DataBase.FavDataModel;
import com.learn.project_wallz.DataBase.Mydatabase;
import com.learn.project_wallz.MODELS.ImageModel;
import com.learn.project_wallz.R;

import java.util.ArrayList;

public class FavouritesFragMent extends Fragment {
    Mydatabase db;
    RecyclerView recyclerView;
    FavoritesAdapter adapter;

    public FavouritesFragMent() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites_frag_ment, container, false);
        db = new Mydatabase(getContext());
        ArrayList<ImageModel> data = db.GETDATA();
        recyclerView = view.findViewById(R.id.favrecycler);
        adapter = new FavoritesAdapter(getContext(),data);
        adapter.updatedata(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        Toast toast = Toast.makeText(getContext(), " Liked Images Saved Here ! ", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,800);
        toast.show();
        return view;
    }
}