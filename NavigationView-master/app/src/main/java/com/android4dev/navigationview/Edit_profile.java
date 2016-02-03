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
    EditText eT_npwp,eT_ijinusaha,eT_akta, eT_mobile2, eT_email2, eT_position2, eT_mobile, eT_position, eT_telephone, eT_zipcode, eT_city, eT_fax, eT_chairman, eT_email, eT_company, eT_officeaddress, eT_username, eT_fullname, eT_merkalias;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.coba_edit, container, false);
        eT_company = (EditText) v.findViewById(R.id.company_name);
        eT_merkalias = (EditText) v.findViewById(R.id.merk_alias);
        eT_officeaddress = (EditText) v.findViewById(R.id.office_address);
        eT_city = (EditText) v.findViewById(R.id.city);
        eT_zipcode = (EditText) v.findViewById(R.id.zip_code);
        eT_telephone = (EditText) v.findViewById(R.id.telephone);
        eT_fax = (EditText) v.findViewById(R.id.fax);
        eT_email = (EditText) v.findViewById(R.id.email);
        eT_chairman = (EditText) v.findViewById(R.id.chairman);
        eT_position = (EditText) v.findViewById(R.id.position);
        eT_mobile = (EditText) v.findViewById(R.id.mobile);
        eT_username = (EditText) v.findViewById(R.id.username);
        eT_fullname = (EditText) v.findViewById(R.id.full_name);
        eT_position2 = (EditText) v.findViewById(R.id.position2);
        eT_email2 = (EditText) v.findViewById(R.id.email2);
        eT_mobile2 = (EditText) v.findViewById(R.id.mobile2);
        eT_akta = (EditText) v.findViewById(R.id.akta);
        eT_ijinusaha = (EditText) v.findViewById(R.id.ijin_usaha);
        eT_npwp = (EditText) v.findViewById(R.id.npwp);

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
        //Toast.makeText( getActivity(),url,Toast.LENGTH_LONG).show();

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
        String nama_perusahaan="";
        String merk_brand_alias="";
        String alamat_kantor="";
        String kota="";
        String kode_pos="";
        String no_telepon="";
        String fax_kantor="";
        String email_kantor="";
        String nama_pimpinan="";
        String jabatan="";
        String nomor_hp_pimpinan_kantor="";
        String username="";
        String nama_lengkap_member="";
        String jabatan_member="";
        String email_member="";
        String nomor_hp_member="";
        String akta_pendirian_kantor="";
        String ijin_usaha_kantor="";
        String no_npwp="";



        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject collegeData = result.getJSONObject(0);
            nama_perusahaan=collegeData.getString("nama_perusahaan");
            merk_brand_alias=collegeData.getString("merk_brand_alias");
            alamat_kantor=collegeData.getString("alamat_kantor");
            kota=collegeData.getString("kota");
            kode_pos=collegeData.getString("kode_pos");
            no_telepon=collegeData.getString("no_telepon");
            fax_kantor=collegeData.getString("fax_kantor");
            email_kantor=collegeData.getString("email_kantor");
            nama_pimpinan=collegeData.getString("nama_pimpinan");
            jabatan=collegeData.getString("jabatan");
            nomor_hp_pimpinan_kantor=collegeData.getString("nomor_hp_pimpinan_kantor");
            username=collegeData.getString("username");
            nama_lengkap_member=collegeData.getString("nama_lengkap_member");
            jabatan_member=collegeData.getString("jabatan_member");
            email_member=collegeData.getString("email_member");
            nomor_hp_member=collegeData.getString("nomor_hp_member");
            akta_pendirian_kantor=collegeData.getString("akta_pendirian_kantor");
            ijin_usaha_kantor=collegeData.getString("ijin_usaha_kantor");
            no_npwp=collegeData.getString("no_npwp");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        eT_company.setText(nama_perusahaan);
        eT_merkalias.setText(merk_brand_alias);
        eT_officeaddress.setText(alamat_kantor);
        eT_city.setText(kota);
        eT_zipcode.setText(kode_pos);
        eT_telephone.setText( no_telepon);
        eT_fax.setText(fax_kantor);
        eT_email.setText(email_kantor);
        eT_chairman.setText(nama_pimpinan);
        eT_position.setText(jabatan);
        eT_mobile.setText(nomor_hp_pimpinan_kantor);
        eT_username.setText(username);
        eT_fullname.setText(nama_lengkap_member);
        eT_position2.setText(jabatan_member);
        eT_email2.setText(email_member);
        eT_mobile2.setText(nomor_hp_member);
        eT_akta.setText(akta_pendirian_kantor);
        eT_ijinusaha.setText(ijin_usaha_kantor);
        eT_npwp.setText(no_npwp);
    }
}




