package com.example.draw_nav;

import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.zip.Inflater;

public class FragmentLogin extends Fragment {
TextView Regwithus;
EditText UsernameET,PasswordET;
Button loginbtn;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_frag,
                container,
                false);

        //View view= inflater.inflate(R.layout.register_frag,container,false);


        Regwithus = view.findViewById(R.id.registerlink);
        UsernameET = view.findViewById(R.id.username);
        PasswordET = view.findViewById(R.id.password);
        loginbtn=view.findViewById(R.id.buttonlogin);

        Regwithus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefrag(new FragmentRegister());
            }
        });
loginbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        verify();
    }
});
        return view;

    }

    private void replacefrag(Fragment fragment) {
        FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

    public void verify()
        {
            String Uname=UsernameET.getText().toString().trim();
            String Pword=PasswordET.getText().toString().trim();
            if(checkusername(Uname)&& checkpassword(Pword));
            {
                //Toast.makeText(getActivity(),"welcome "+Uname,Toast.LENGTH_SHORT).show();
                loginuser(Uname,Pword);
            }
    }
    private boolean checkusername(String uname)
{
    if(TextUtils.isEmpty(uname))
    {
        UsernameET.setError("please enter you name");
        return false;
    }
    return true;
}
private boolean checkpassword(String pword)
{
    if(TextUtils.isEmpty(pword))
    {
        PasswordET.setError("please enter your password");
        return false;
    }
    else if(pword.length()<8)
    {
        PasswordET.setError("please enter a valid password");
        return false;
    }
    return true;
}
    private void loginuser(String uname, String pword) {
        String url = "http://192.168.43.58/Final-project/submituser.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESpond", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Responce", "" + error);
            }
        }) {
            protected HashMap<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", uname);
                map.put("password", pword);
                return map;
            }
        };
requestQueue.add(stringRequest);
        }
    }