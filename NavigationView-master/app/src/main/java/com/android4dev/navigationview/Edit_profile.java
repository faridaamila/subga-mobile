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
public class Edit_profile extends Fragment implements View.OnClickListener{

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
    private void getData() {
        Login login = new Login();
        String url = "http://subga.info/Assets/get_data/data_member.php?username=" + login.username;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String id_member = "";
        String nama_perusahaan = "";
        String merk_brand_alias = "";
        String alamat_kantor = "";
        String kota = "";
        String kode_pos = "";
        String jenis_kantor = "";
        String no_telepon = "";
        String fax_kantor = "";
        String email_kantor = "";
        String nama_pimpinan = "";
        String jabatan = "";
        String nomor_hp_pimpinan_kantor = "";
        String username = "";
        String password = "";
        String nama_lengkap_member = "";
        String jabatan_member = "";
        String email_member = "";
        String nomor_hp_member = "";
        String akta_pendirian_kantor = "";
        String ijin_usaha_kantor = "";
        String no_npwp= "";
        String jenis_member="";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject memberData = result.getJSONObject(0);
            id_member = memberData.getString("id_member");
            nama_perusahaan = memberData.getString("nama_perusahaan");
            merk_brand_alias = memberData.getString("merk_brand_alias");
            alamat_kantor = memberData.getString("alamat_kantor");
            kota = memberData.getString("kota");
            kode_pos = memberData.getString("kode_pos");
            jenis_kantor = memberData.getString("jenis_kantor");
            no_telepon = memberData.getString("no_telepon");
            fax_kantor = memberData.getString("fax_kantor");
            email_kantor = memberData.getString("email_kantor");
            nama_pimpinan = memberData.getString("nama_pimpinan");
            jabatan = memberData.getString("jabatan");
            nomor_hp_pimpinan_kantor = memberData.getString("nomor_hp_pimpinan_kantor");
            username = memberData.getString("username");
            password = memberData.getString("password");
            nama_lengkap_member = memberData.getString("nama_lengkap_member");
            jabatan_member = memberData.getString("jabatan_member");
            email_member = memberData.getString("email_member");
            nomor_hp_member = memberData.getString("nomor_hp_member");
            akta_pendirian_kantor = memberData.getString("akta_pendirian_kantor");
            ijin_usaha_kantor = memberData.getString("ijin_usaha_kantor");
            no_npwp = memberData.getString("no_npwp");
            jenis_member = memberData.getString("jenis_member");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //textViewResult.setText("Name:\t"+name+"\nAddress:\t" +address+ "\nVice Chancellor:\t"+ vc);
    }

    @Override
    public void onClick(View v) {
        getData();
    }
}




