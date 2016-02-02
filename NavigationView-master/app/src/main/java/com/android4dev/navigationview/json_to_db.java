package com.android4dev.navigationview;

import android.app.Activity;
import android.database.Cursor;
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
public class json_to_db extends Activity{
    int id_To_Update = 0;
    DBHelper mydb = new DBHelper(this);
    String jenis_member,password,npwp,username,no_fax_kantor,akta_pendirian,alamat_kantor,email_company,email_user,id_member,ijin_usaha,jabatan_pimpinan,jabatan_user,jenis_kantor,kode_pos,kota, merk_brand, nama_lengkap,nama_perusahaan,nama_pimpinan,nama_user,no_hp_pim,no_hp_user,no_telp_kantor;

    public json_to_db (String usernameku){
        Cursor rs = mydb.getDataLogin(usernameku);
        if (rs==null) {
            getData();
        }
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
                        Toast.makeText(json_to_db.this,"Check koneksi" ,Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject memberData = result.getJSONObject(0);
            id_member = memberData.getString("id_member");
            nama_perusahaan = memberData.getString("nama_perusahaan");
            merk_brand = memberData.getString("merk_brand_alias");
            alamat_kantor = memberData.getString("alamat_kantor");
            kota = memberData.getString("kota");
            kode_pos = memberData.getString("kode_pos");
            jenis_kantor = memberData.getString("jenis_kantor");
            no_telp_kantor = memberData.getString("no_telepon");
            no_fax_kantor = memberData.getString("fax_kantor");
            email_company = memberData.getString("email_kantor");
            nama_pimpinan = memberData.getString("nama_pimpinan");
            jabatan_pimpinan = memberData.getString("jabatan");
            no_hp_pim = memberData.getString("nomor_hp_pimpinan_kantor");
            username = memberData.getString("username");
            password = memberData.getString("password");
            nama_lengkap = memberData.getString("nama_lengkap_member");
            jabatan_user = memberData.getString("jabatan_member");
            email_user = memberData.getString("email_member");
            no_hp_user = memberData.getString("nomor_hp_member");
            akta_pendirian = memberData.getString("akta_pendirian_kantor");
            ijin_usaha = memberData.getString("ijin_usaha_kantor");
            npwp = memberData.getString("no_npwp");
            jenis_member = memberData.getString("jenis_member");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mydb.insertMember(id_member,nama_perusahaan,merk_brand,alamat_kantor,kota,kode_pos,jenis_kantor,no_telp_kantor,no_fax_kantor,
                email_company,nama_pimpinan,jabatan_pimpinan,no_hp_pim,username, password, nama_lengkap,
                jabatan_user,email_user,no_hp_user,akta_pendirian,ijin_usaha,npwp,jenis_member);
    }
}
