package com.android4dev.navigationview;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by S. Harsono on 1/26/2016.
 */
public class Home extends Fragment {
    private ProgressDialog loading;
    private boolean viewIsAtHome;
    ViewFlipper flip;
    int mFlipping = 0;
    private TextView tv;
   // String title = getString(R.string.app_name);


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home,container,false);

        flip = (ViewFlipper)v.findViewById(R.id.viewFlipper1);
        if (mFlipping == 0) {
            flip.startFlipping();
            mFlipping = 1;
        } else {
            flip.stopFlipping();
            mFlipping = 0;
        }



        // tv2.setText("Mulai tanggal 12 Januari 2015 Garuda Indonesia membuka rute Surabaya ke Shanghai via Denpasar.");
        // tv2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        // tv2.setSingleLine(true);
        // tv2.setMarqueeRepeatLimit(5);


        ImageButton btn_new = (ImageButton)v.findViewById(R.id.btn_new);
        ImageButton btn_bookmark = (ImageButton)v.findViewById(R.id.btn_bookmark);
        ImageButton btn_download = (ImageButton)v.findViewById(R.id.btn_download);
        ImageButton btn_setting = (ImageButton)v.findViewById(R.id.btn_setting);

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav_new fragment14 = new Nav_new();
                //Toast.makeText(getActivity(),"New Document!", Toast.LENGTH_SHORT).show();
                android.support.v4.app.FragmentManager fm1 = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.frame, fragment14);
                ft1.addToBackStack(null);
                ft1.commit();
                //  title  = getString(R.string.home);
            }
        });
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav_bookmark fragment15 = new Nav_bookmark();
                android.support.v4.app.FragmentManager fm2 = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft2 = fm2.beginTransaction();
                ft2.replace(R.id.frame, fragment15);
                ft2.addToBackStack(null);
                ft2.commit();
            }
        });
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav_download fragment16 = new Nav_download();
                android.support.v4.app.FragmentManager fm3 = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft3 = fm3.beginTransaction();
                ft3.replace(R.id.frame, fragment16);
                ft3.addToBackStack(null);
                ft3.commit();
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav_setting fragment17 = new Nav_setting();
                android.support.v4.app.FragmentManager fm4 = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.frame, fragment17);
                ft4.addToBackStack(null);
                ft4.commit();
            }
        });

        tv=(TextView)v.findViewById(R.id.hot_news2);
        tv.setSelected(true);
        tv.setFocusable(true);
        tv.setFocusableInTouchMode(true);
        getData();


       // tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
       // tv.setSingleLine(true);
       // tv.setMarqueeRepeatLimit(5);
        //tv.setSelected(true);

        return v;

    }
    private void getData() {
        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

        String url = "http://subga.info/Assets/get_data/freetext.php";

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
        String isi_freetext="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject collegeData = result.getJSONObject(0);
            isi_freetext = collegeData.getString("isi_freetext");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv.setText(isi_freetext);
    }
}


