package com.android4dev.navigationview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by S. Harsono on 1/27/2016.
 */
public class Edit_profile extends Fragment{

    String login_username;
    public static String jenis_member="";
    private ProgressDialog loading;

    EditText eT_npwp,eT_ijinusaha,eT_akta, eT_mobile2, eT_email2, eT_position2, eT_mobile, eT_position, eT_telephone, eT_zipcode, eT_city, eT_fax, eT_chairman, eT_email, eT_company, eT_officeaddress, eT_username, eT_fullname, eT_merkalias;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Login loginku = new Login();
        login_username = loginku.username;
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
        Button submit =(Button) v.findViewById(R.id.btn_form_edit_profile);

        getData();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfil();
            }
        });

        return v;
    }
    private void editProfil(){
        final String company = eT_company.getText().toString().trim();
        //if (company == null) {company.replace(null, " ");}
        final String merkalias = eT_merkalias.getText().toString().trim();
        //if (merkalias == null) {merkalias.replace(null, " ");}
        final String officeaddress = eT_officeaddress.getText().toString().trim();
        //if (officeaddress == null) {officeaddress.replace(null, " ");}
        final String city = eT_city.getText().toString().trim();
        //if (city == null) {city.replace(null, " ");}
        final String zipcode = eT_zipcode.getText().toString().trim();
        //if (zipcode == null) {zipcode.replace(null, " ");}
        final String telephone = eT_telephone.getText().toString().trim();
        //if (telephone == null) {telephone.replace(null, " ");}
        final String fax = eT_fax.getText().toString().trim();
        //if (fax == null) {fax.replace(null, " ");}
        final String email = eT_email.getText().toString().trim();
        //if (email == null) {email.replace(null, " ");}
        final String chairman = eT_chairman.getText().toString().trim();
        //if (chairman == null) {chairman.replace(null, " ");}
        final String position = eT_position.getText().toString().trim();
        //if (position == null) {position.replace(null, " ");}
        final String mobile = eT_mobile.getText().toString().trim();
        //if (mobile == null) {mobile.replace(null, " ");}
        final String username = eT_username.getText().toString().trim();
        //if (username == null) {username.replace(null, " ");}
        final String fullname = eT_fullname.getText().toString().trim();
        //if (fullname == null) {fullname.replace(null, " ");}
        final String position2 = eT_position2.getText().toString().trim();
        //if (position2 == null) {position2.replace(null, " ");}
        final String email2 = eT_email2.getText().toString().trim();
        //if (email2 == null) {email2.replace(null, " ");}
        final String mobile2 = eT_email2.getText().toString().trim();
        //if (mobile2 == null) {mobile2.replace(null, " ");}
        final String akta =  eT_akta.getText().toString().trim();
        //if (akta == null) {akta.replace(null, " ");}
        final String ijinusaha =  eT_ijinusaha.getText().toString().trim();
        //if (ijinusaha == null) {ijinusaha.replace(null, " ");}
        final String npwp =  eT_npwp.getText().toString().trim();
        //if (npwp == null) {npwp.replace(null, " ");}


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://subga.info/Assets/get_data/update_profile.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("nama_perusahaan",company);
                params.put("merk_brand_alias",merkalias);
                params.put("alamat_kantor",officeaddress);
                params.put("kota",city);
                params.put("kode_pos",zipcode);
                params.put("no_telepon_kantor",telephone);
                params.put("fax_kantor",fax);
                params.put("email_kantor",email);
                params.put("nama_pimpinan",chairman);
                params.put("jabatan",position);
                params.put("nomor_hp_pimpinan_kantor",mobile);
                params.put("username",username);
                params.put("nama_lengkap_member",fullname);
                params.put("jabatan_member",position2);
                params.put("email_member",email2);
                params.put("nomor_hp_member",mobile2);
                params.put("akta_pendirian_kantor",akta);
                params.put("ijin_usaha_kantor",ijinusaha);
                params.put("no_npwp",npwp);
                params.put("usernameblm",login_username);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void getData() {


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
            jenis_member=collegeData.getString("jenis_member");
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




