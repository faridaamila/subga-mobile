package com.android4dev.navigationview;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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
 * Created by S. Harsono on 2/3/2016.
 */
public class Internal_news extends Fragment {
    private ProgressDialog loading;
    TableLayout tl;
    EditText no;
    String[] jml_download;
    String[] direktori_file;
    String[] status;
    String[] refer;
    String[] subjek;
    String[] ga_info;
    String[] periode_akhir_muncul;
    String[] periode_awal_muncul;
    String[] tgl_release;
    String[] jenis_dokumen;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.internal_news, container, false);


      /* Find Tablelayout defined in main.xml */
        tl = (TableLayout) v.findViewById(R.id.tableLayout1);
        getData();

        String [] values = {"Issue Date","Subject","GA Info","Refer"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String selected = parentView.getItemAtPosition(position).toString();
                Context context = parentView.getContext();
                CharSequence text = selected;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //  TextView bk = (TextView) v.findViewById(R.id.by);

                switch (position) {
                    case 0:

                        break;
                    case 1:


                        break;
                    case 2:


                        break;
                    default:
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return v;
    }

    private void getData() {
        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

        String url = "http://subga.info/Assets/get_data/data_file_e.php?kategori=11";

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
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {


        JSONArray result = null;
        try {
            JSONObject jsonObject = new JSONObject(response);
            result = jsonObject.getJSONArray("result");
            tgl_release = new String[result.length()];
            periode_awal_muncul = new String[result.length()];
            periode_akhir_muncul = new String[result.length()];
            ga_info = new String[result.length()];
            subjek = new String[result.length()];
            refer = new String[result.length()];
            status = new String[result.length()];
            jenis_dokumen = new String[result.length()];
            direktori_file = new String[result.length()];
            jml_download = new String[result.length()];


            for (int i = 0; i < result.length(); i++) {
                JSONObject file = result.getJSONObject(i);
                jenis_dokumen[i]=file.getString("jenis_dokumen");
                tgl_release[i] = file.getString("tgl_release");
                periode_awal_muncul[i] = file.getString("periode_awal_muncul");
                periode_akhir_muncul[i] = file.getString("periode_akhir_muncul");
                ga_info[i] = file.getString("ga_info");
                subjek[i] = file.getString("subjek");
                refer[i] = file.getString("refer");
                status[i] = file.getString("status");
                direktori_file[i] = file.getString("direktori_file");
                jml_download[i] = file.getString("jml_download");
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        int k = 1;
        for (int u = 0; u < result.length(); u++) {

            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j <= 7; j++) {
                TextView tv = new TextView(getActivity());
                ImageButton ib = new ImageButton(getActivity());
                ImageButton ibk = new ImageButton(getActivity());
                if (j == 0) {
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setPadding(10, 10, 10, 10);
                    tv.setText(Integer.toString(k));
                    k++;
                    row.addView(tv);
                } else if (j == 1) {
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setPadding(10, 10, 10, 10);
                    tv.setText(tgl_release[u]);
                    row.addView(tv);
                } else if (j == 2) {
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setPadding(10, 10, 10, 10);
                    tv.setText(ga_info[u]);
                    row.addView(tv);
                } else if (j == 3) {
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setPadding(10, 10, 10, 10);
                    tv.setText(subjek[u]);
                    tv.setEms(3);
                    row.addView(tv);
                } else if (j == 4) {
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setPadding(10, 10, 10, 10);
                    tv.setText(jml_download[u]);
                    row.addView(tv);
                } else if (j == 5) {
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setPadding(10, 10, 10, 10);
                    tv.setText(refer[u]);
                    tv.setEms(3);
                    row.addView(tv);
                } else if (j == 6) {
                    ibk.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    ibk.setBackgroundResource(R.mipmap.btn_download);
                    ibk.getLayoutParams().width = 100;
                    ibk.getLayoutParams().height = 100;
                    ibk.setPadding(10, 10, 10, 10);
                    row.addView(ibk);
                    final int finalU = u;
                    ibk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("http://subga.info/" + direktori_file[finalU]));
                            getActivity().startActivity(i);
                        }
                    });

                } else if (j == 7) {
                    ib.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    ib.setBackgroundResource(R.mipmap.btn_star_full);
                    ib.getLayoutParams().width = 100;
                    ib.getLayoutParams().height = 100;
                    ib.setPadding(10, 10, 10, 10);
                    row.addView(ib);
                    ib.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {

                        }

                    });
                }
            }
            tl.addView(row);
        }
    }
}


