package com.example.draw_nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentAccount extends Fragment {

private  List<ACCOUNTSS>account;
    ArrayAdapter<ACCOUNTSS>myadapter;
    private ListView lsvv;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.account_frag,container,false);
       lsvv=view.findViewById(R.id.lsv);
       getuserdata();
return view;
    }
    private void getuserdata()
    {
        String url="";
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject jsonObject=array.getJSONObject(i);
                        String NAME=jsonObject.getString("username");
                        int ID=jsonObject.getInt("idno");
                        double NO=jsonObject.getDouble("no");
                        String LOCATION=jsonObject.getString("location");
                                String USERs=jsonObject.getString("user");

                                ACCOUNTSS accountss=new ACCOUNTSS(NAME,ID,NO,LOCATION,USERs);
                        account.add(accountss);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myadapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1 , account);
                lsvv.setAdapter(myadapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"failed" + error,Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(stringRequest);

    }
}
