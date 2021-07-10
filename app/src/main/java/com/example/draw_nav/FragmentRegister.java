 package com.example.draw_nav;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

 public class FragmentRegister extends Fragment {
EditText USERNAME,EMAIL,PHONE,PASSWORD,CONFIRMPASSWORD;
Button REGBTN;
Spinner Clients;
TextView LINKLOGIN,WARNTYPE;
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.register_frag,container,false);
USERNAME=view.findViewById(R.id.regusername);

WARNTYPE=view.findViewById(R.id.warn);
EMAIL=view.findViewById(R.id.regemail);
PHONE=view.findViewById(R.id.regphonenumber);
Clients=view.findViewById(R.id.regtype);
PASSWORD=view.findViewById(R.id.regpassword);
CONFIRMPASSWORD=view.findViewById(R.id.regconfirmpassword);
REGBTN=view.findViewById(R.id.regbtn);
LINKLOGIN=view.findViewById(R.id.regloginlink);

        ArrayAdapter<CharSequence>arrayAdapter=ArrayAdapter.createFromResource(getContext(), R.array.TYPES,
                android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Clients.setAdapter(arrayAdapter);

        LINKLOGIN.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
replacefrag(new FragmentLogin());
            }
        });
REGBTN.setOnClickListener(new OnClickListener() {
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
         fragmentTransaction.addToBackStack(null);
     }

    private void verify() {
        final String username=USERNAME.getText().toString().trim();
        final String email=EMAIL.getText().toString().trim();
       final String phone=PHONE.getText().toString().trim();
       final String password=PASSWORD.getText().toString().trim();
        final String confirmpassword=CONFIRMPASSWORD.getText().toString().trim();
       //  S= Clients.getSelectedItem().toString());

        if(checkname(username) && checkemail(email) && checkphone(phone) && checkpassword(password) &&checkconfirmpassword(confirmpassword,password) );
        {
           // Toast.makeText(getActivity(),"welcome to Mnyumba "+usernameR,Toast.LENGTH_SHORT).show();
regusertodb(username,email,phone,password,confirmpassword);
        }
    }


    private boolean checkname(String usernamer) {
        if (TextUtils.isEmpty(usernamer)) {
            USERNAME.setError("please enter you name");
            return false;
        }
        return true;
    }
        public boolean checkemail(String emailr)
        {
         if (TextUtils.isEmpty(emailr)) {
            EMAIL.setError("please enter your email ");
            return false;
        }
         return true;
        }
        public boolean checkphone(  String phoner )
        {
        if (TextUtils.isEmpty(phoner)) {
            PHONE.setError("please enter your phone no");
            return false;
        }
        return true;
        }
        public boolean checkpassword(String passwordr)
        {
        if (TextUtils.isEmpty(passwordr)) {
            PASSWORD.setError("please enter your password");
            return false;
        }
        return true;
        }
        public boolean checkconfirmpassword(String confirmpasswordr,  String passwordr)
        {
        if (TextUtils.isEmpty(confirmpasswordr)) {
            CONFIRMPASSWORD.setError("please confirm your password");
            return false;
        }
        else if(confirmpasswordr!=passwordr)
        {
            CONFIRMPASSWORD.setError("please confirm you password");
return true;
        }
        return false;
        }
   /*  public boolean checktype(int pos)
     {

         if (pos < 0) {
             WARNTYPE.setError("please select type of client");

         } else {
             Toast.makeText(getActivity(),"thanks for registering with us ",Toast.LENGTH_SHORT);

         }
         return true;
     }*/
   private void regusertodb(String username, String email, String phone, String password, String confirmpassword) {

       String url="http://192.168.43.58/php/reguser.php";
       RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
       StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

           @Override
           public void onResponse(String response) {
Log.d("responce",response);
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
Log.d("error",""  +error);
               Toast.makeText(getActivity(),"registration failed" + error,Toast.LENGTH_SHORT).show();

           }
       })
       {
           protected HashMap<String,String> getParams() throws AuthFailureError {
               HashMap<String,String>map=new HashMap<>();
               map.put("name",username);
               map.put("email",email);
               map.put("phone",phone);
               map.put("password",password);
               map.put("confirm-password",confirmpassword);
               return  map;
           }
       };
       requestQueue.add(stringRequest);

   }


     }


