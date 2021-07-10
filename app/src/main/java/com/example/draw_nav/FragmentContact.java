package com.example.draw_nav;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;

public class FragmentContact extends Fragment {
TextView WHATTSAPP,EMAIL,PHONE;
Button SENDFEED;
EditText FEEDBACK;
Intent intent=null, chooser=null;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.contact_frag,container,false);
WHATTSAPP=view.findViewById(R.id.whattsapp);
EMAIL=view.findViewById(R.id.email);
PHONE=view.findViewById(R.id.phone);
SENDFEED=view.findViewById(R.id.sendfeedback);
FEEDBACK=view.findViewById(R.id.feedback);
WHATTSAPP.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String k ="+254708288862";
        String url = "https://api.whatsapp.com/send?phone="+k;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
});
EMAIL.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto"));
        intent.putExtra(Intent.EXTRA_EMAIL, "felixgitau01@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"TELL US");
        intent.setType("message/rfc822");

    }
});
sendfeed();
        return view;
    }
private void sendfeed()
{
    String feed=FEEDBACK.getText().toString().trim();
getfeedback(feed);
}
    private void getfeedback(String ssd) {
        String url="";
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               Log.d("responce",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"failed" + error,Toast.LENGTH_SHORT).show();

            }
        })
        {
            protected HashMap<String,String>getParams() throws AuthFailureError{
                HashMap<String,String>map=new HashMap<>();
                map.put("feed",ssd);
                return map;
            }
        };
        requestQueue.add(stringRequest);


    }

}
