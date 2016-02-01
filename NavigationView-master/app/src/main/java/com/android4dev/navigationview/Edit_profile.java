package com.android4dev.navigationview;

import android.content.DialogInterface;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.coba_edit, container, false);
        EditText eT_company_name= (EditText)v.findViewById(R.id.company_name);
        EditText eT_merk_alias= (EditText)v.findViewById(R.id.merk_alias);
        EditText eT_office_address= (EditText)v.findViewById(R.id.office_address);
        EditText eT_city= (EditText)v.findViewById(R.id.city);
        EditText eT_zip_code= (EditText)v.findViewById(R.id.zip_code);
        EditText eT_telephone= (EditText)v.findViewById(R.id.telephone);
        EditText eT_fax= (EditText)v.findViewById(R.id.fax);
        EditText eT_email= (EditText)v.findViewById(R.id.email);
        EditText eT_chairman= (EditText)v.findViewById(R.id.chairman);
        EditText eT_position= (EditText)v.findViewById(R.id.position);

        return v;
    }

}




