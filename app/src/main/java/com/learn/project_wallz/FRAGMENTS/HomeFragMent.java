package com.learn.project_wallz.FRAGMENTS;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.learn.project_wallz.ADAPTERS.ImageAdapter;
import com.learn.project_wallz.MODELS.ImageModel;
import com.learn.project_wallz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragMent extends Fragment {
    ImageAdapter adapter;
    RecyclerView recyclerView;

    public HomeFragMent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home_frag_ment, container, false);
        getimageapidata();
        recyclerView = view.findViewById(R.id.HomeFragRecycler);
        return view;
    }

    public void getimageapidata() {
        String QeryString = "night";
        ArrayList<ImageModel> photos = new ArrayList<>();
        String url = "https://api.unsplash.com/search/photos/?client_id=wo7f5-hNMfbAuGiOPxr4lit4_aZhe-Uq9h45u_kc75E&page=1&per_page=100&query=" + QeryString;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr = response.getJSONArray("results");
                            //Toast.makeText(getApplicationContext(), "Size is " + arr.length() , Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < arr.length(); i++) {
                                ImageModel img = new ImageModel();
                                JSONObject resultobject = (JSONObject) arr.get(i);
                                img.setInfo(resultobject.getString("description"));
                                JSONObject urlobject = resultobject.getJSONObject("urls");
                                img.setUrl(urlobject.getString("regular"));
                                photos.add(img);

                            }
                            adapter = new ImageAdapter(getContext(),photos);
                            adapter.updatedata(photos);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest);
    }

}


