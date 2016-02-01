package com.android4dev.navigationview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
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
 * Created by faridaamila on 01/02/2016.
 */
public class get_member extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void getData() {
        Login login = new Login();
        String url = "http://subga.info/Assets/get_data/data_member.php?username=" + login.username;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(get_member.this,"Check koneksi" ,Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
        //textViewResult.setText("Name:\t"+name+"\nAddress:\t" +address+ "\nVice Chancellor:\t"+ vc);8
    }
}
