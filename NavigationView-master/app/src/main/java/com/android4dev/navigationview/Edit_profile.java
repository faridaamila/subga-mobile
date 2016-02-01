package com.android4dev.navigationview;

import android.content.DialogInterface;
import android.database.Cursor;
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
        EditText eT_mobile= (EditText)v.findViewById(R.id.mobile);
        EditText eT_username= (EditText)v.findViewById(R.id.username);
        EditText eT_full_name= (EditText)v.findViewById(R.id.full_name);
        EditText eT_position2= (EditText)v.findViewById(R.id.position2);
        EditText eT_email2= (EditText)v.findViewById(R.id.email2);
        EditText eT_mobile2= (EditText)v.findViewById(R.id.mobile2);
        EditText eT_akta= (EditText)v.findViewById(R.id.akta);
        EditText eT_ijin_usaha= (EditText)v.findViewById(R.id.ijin_usaha);
        EditText eT_npwp= (EditText)v.findViewById(R.id.npwp);

        /*Cursor rs = mydb.getDataLogin(usernameku);
        if (rs!=null) {
            rs.moveToFirst();

            akta_pendirian = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_AKTA_PENDIRIAN));
            alamat_kantor = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_ALAMAT_KANTOR));
            email_company = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_EMAIL_COMPANY));
            email_user = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_EMAIL_USER));
            ijin_usaha = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_IJIN_USAHA));
            jabatan_pimpinan = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_JABATAN_PIMPINAN));
            jabatan_user = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_JABATAN_USER));
            jenis_kantor = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_JENIS_KANTOR));
            kode_pos = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_KODEPOS));
            kota = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_KOTA));
            merk_brand = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_MERK_BRAND));
            nama_lengkap = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_NAMA_LENGKAP_USER));
            nama_perusahaan = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_NAMA_PERUSAHAAN));
            nama_pimpinan = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_NAMA_PIMPINAN));
            nama_user = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_USERNAME));
            no_hp_pim = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_NO_HP_PIM));
            no_hp_user = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_NO_HP_USER));
            no_telp_kantor = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_TELEPHONE_KANTOR));
            no_fax_kantor = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_FAX_KANTOR));
            npwp = rs.getString(rs.getColumnIndex(DBHelper.DATA_MEMBER_COLUMN_NPWP));

            if (!rs.isClosed()) {
                rs.close();
            }
        }*/

        return v;
    }

}




