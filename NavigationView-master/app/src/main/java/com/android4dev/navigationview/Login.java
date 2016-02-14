package com.android4dev.navigationview;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.Console;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener{

    public static final String LOGIN_URL = "http://subga.info/Assets/get_data/login.php";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private ImageButton buttonLogin;
    private ProgressDialog loading;
    CheckBox ch;
    public static String  PREFS_NAME="mypre";
    public static String PREF_USERNAME="username";
    public static String PREF_PASSWORD="password";

    public static String username;
    private String password;
    DBHelper mydb;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mydb = new DBHelper(this);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        ch = (CheckBox) findViewById(R.id.remember_me);

        buttonLogin = (ImageButton) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(Login.this);

        TextView forgot_password = (TextView) findViewById(R.id.forgot_password);
        forgot_password.setMovementMethod(LinkMovementMethod.getInstance());

        TextView register_here = (TextView) findViewById(R.id.register_here);
        register_here.setMovementMethod(LinkMovementMethod.getInstance());



    }

    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        password = md5(password).trim();

        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success")){
                            if(ch.isChecked()){
                                rememberMe(username,password);}
                            getDatatoDB();
                            openProfile();
                        }else{
                            Toast.makeText(Login.this,"Username dan Password Salah", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("username",username);
                map.put("password",password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void rememberMe(String user, String password){
        //save username and password in SharedPreferences
        getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                .edit()
                .putString(PREF_USERNAME,user)
                .putString(PREF_PASSWORD,password)
                .commit();
    }

    public void onStart(){
        super.onStart();
        //read username and password from SharedPreferences
        getUser();
    }

    public void getUser(){
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);

        if (username != null || password != null) {
            //directly show logout form
            getDatatoDB();
            openProfile();
        }
    }

    private void openProfile(){
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    private void getDatatoDB() {
        //spasi convert
        int i;
        for (i=0; i<username.length();i++){
            char a_char = username.charAt(i);
            if (a_char == ' '){
                username=username.replace(" ","%20");
            }
        }

        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        String url = "http://subga.info/Assets/get_data/data_member.php?username="+username;
        //Toast.makeText( getActivity(),url,Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSONdb(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSONdb(String response){
        String nama_perusahaanjson="";
        String usernamejson="";
        String jenis_memberjson="";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject collegeData = result.getJSONObject(0);
            nama_perusahaanjson=collegeData.getString("nama_perusahaan");
            usernamejson=collegeData.getString("username");
            jenis_memberjson=collegeData.getString("jenis_member");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        boolean test = mydb.insertMember(usernamejson,jenis_memberjson,nama_perusahaanjson);
        Log.d("status insert", String.valueOf(test));
    }

    @Override
    public void onClick(View v) {
        userLogin();
    }

    public static String md5(String s)
    {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")),0,s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}



