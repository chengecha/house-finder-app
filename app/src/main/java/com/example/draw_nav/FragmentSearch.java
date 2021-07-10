package com.example.draw_nav;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class FragmentSearch extends Fragment {
EditText LOCSEARCH,COSTSEARCH;
Spinner TYPESEARCH;
    ArrayList<String>found=new ArrayList<String>();
private ArrayAdapter arrayAdapter;
Button btnsearch;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.search_frag,container,false);
LOCSEARCH=view.findViewById(R.id.locser);
COSTSEARCH=view.findViewById(R.id.costser);
TYPESEARCH=view.findViewById(R.id.typeser);
btnsearch=view.findViewById(R.id.searchbtn);

        ArrayAdapter<CharSequence> arAdapter=ArrayAdapter.createFromResource(getContext(), R.array.Housetypes,
                android.R.layout.simple_spinner_dropdown_item);
        arAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        TYPESEARCH.setAdapter(arAdapter);


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
verify();
            }
        });
return view;
    }
    private void verify() {
        final String loc = LOCSEARCH.getText().toString().trim();
        final String cost = COSTSEARCH.getText().toString().trim();
        if(checkloc(loc)&& checkcost(cost))
        {
searchdb(loc,cost);
        }

    }

    private void searchdb(String loc, String cost) {
        String url="";
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String LOCATION = jsonObject.getString("firstName");
                    String COST = jsonObject.getString("lastName");
                    found.add(LOCATION);
                    found.add(COST);
                    arrayAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1 , found);

                    //  firstNameTV.setText(firstName);
                    //lastNameTV.setText(lastName);

                } catch (JSONException e) {
                    e.printStackTrace();
                }






            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            protected HashMap<String,String> getParams() throws AuthFailureError {
            HashMap<String,String>map=new HashMap<>();
            map.put("location",loc);
            map.put("cost" ,cost);
            return map;
        }
        };
        requestQueue.add(stringRequest);


    }

    public boolean checkloc(String location)
    {
        if (TextUtils.isEmpty(location)) {
            LOCSEARCH.setError("please enter your email ");
            return false;
        }
        return true;
    }
    public boolean checkcost(  String cost )
    {
        if (TextUtils.isEmpty(cost)) {
            COSTSEARCH.setError("please enter your phone no");
            return false;
        }
        return true;
    }


}
