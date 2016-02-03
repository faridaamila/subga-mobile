package com.android4dev.navigationview;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by S. Harsono on 1/27/2016.
 */
public class Edit_profile extends Fragment{

    String login_username;
    private ProgressDialog loading;
    EditText eT_company, eT_username, eT_fullname;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.coba_edit, container, false);
        eT_company = (EditText) v.findViewById(R.id.company_name);
        eT_username = (EditText) v.findViewById(R.id.username);
        eT_fullname = (EditText) v.findViewById(R.id.full_name);

        getData();
        return v;
    }
    private void getData() {
        Login loginku = new Login();
        login_username = loginku.username;

        //spasi convert
        int i;
        for (i=0; i<login_username.length();i++){
            char a_char = login_username.charAt(i);
            if (a_char == ' '){
               login_username=login_username.replace(" ","%20");
            }
        }

        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

        String url = "http://subga.info/Assets/get_data/data_member.php?username="+login_username;
        Toast.makeText( getActivity(),url,Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String username="";
        String nama_perusahaan="";
        String nama_lengkap_member="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject collegeData = result.getJSONObject(0);
            username = collegeData.getString("username");
            nama_perusahaan = collegeData.getString("nama_perusahaan");
            nama_lengkap_member = collegeData.getString("nama_lengkap_member");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eT_username.setText(username);
        eT_company.setText(nama_perusahaan);
        eT_fullname.setText(nama_lengkap_member);
    }
}




