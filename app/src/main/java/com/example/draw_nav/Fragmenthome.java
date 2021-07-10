package com.example.draw_nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragmenthome extends Fragment {
    public static String url = "";
    RecyclerView recyclerView;
    myadapter adapter;
    List<Houses> homes;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag, container, false);
        homes=new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadproducts();

        return view;
    }
    private void loadproducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray properties= null;
                        try {
                            properties = new JSONArray(response);
                            for(int i=0;i<properties.length();i++)
                            {
                                JSONObject propertiesobject=properties.getJSONObject(i);
                                String NAME=propertiesobject.getString("name");
                                String EMAIL=propertiesobject.getString("email");
                                int PHONE= Integer.parseInt(propertiesobject.getString("phone"));
                                 String IMAGE=propertiesobject.getString("image");

                                 Houses houses=new Houses(NAME,EMAIL,PHONE,IMAGE);
homes.add(houses);
                                adapter=new myadapter(  getContext(), homes);
                                recyclerView.setAdapter(adapter);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}
