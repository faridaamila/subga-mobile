package com.android4dev.navigationview;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
public class International extends Fragment {
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
    int urutposisi;
    TableRow row;
    String isisearch;
    String urls;
    int search;
    int check;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_on, container, false);

        tl = (TableLayout) v.findViewById(R.id.tableLayout1);


        ////////////////////SEARCH BY//////////////////////////////////////////////////////////////
        String [] values = {"All", "GA Info","Subject"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);

                switch (position) {
                    case 0:
                        search = 0;
                        break;
                    case 1:
                        search = 1;
                        break;
                    case 2:
                        search = 2;
                        break;
                }
                if (search != 0) {
                    getSearch();
                } else {
                    getData();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ImageButton ibsearch = (ImageButton) v.findViewById(R.id.btn_search);
        final EditText etsearch = (EditText) v.findViewById(R.id.search_bar);

        ibsearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                isisearch = etsearch.getText().toString();
                if (search != 0) {
                    getSearch();
                }
                else
                    Toast.makeText(getActivity(), "Please check the option search", Toast.LENGTH_SHORT).show();
            }
        });


        //////SORT BY///////////////////////////////////////////////////////////////////////////////////////////////////////////
        final CharSequence[] options = {"Issue Date","Subject","GA Info","Hits","Refer","Cancel"};
        ImageButton btn_sort = (ImageButton)v.findViewById(R.id.btn_sort);

        btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Sort by");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item) {
                            case 0:
                                urutposisi = 1;
                                break;
                            case 1:
                                urutposisi = 2;
                                break;
                            case 2:
                                urutposisi = 3;
                                break;
                            case 3:
                                urutposisi = 4;
                                break;
                            case 4:
                                urutposisi = 5;
                                break;
                            case 5:
                                dialog.dismiss();
                                break;
                            default:
                                break;
                        }
                        if (isisearch != null) {
                            getSearch();
                        } else {
                            getData();
                        }
                    }
                });builder.show();
            }
        });

        return v;
    }

    private void getSearch() {
        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);
        if (search == 1) {
            urls = "http://subga.info/Assets/get_data/search_gainfo.php?kategori=2&internal=%27E%27&urut=" + urutposisi + "&gainfo=%27" + isisearch + "%27";
        }  else {
            urls = "http://subga.info/Assets/get_data/search_subject.php?kategori=2&internal=%27E%27&urut=" + urutposisi + "&subject=%27" + isisearch + "%27";
        }

        StringRequest stringRequest = new StringRequest(urls, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                tl.removeAllViews();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getActivity(), urls, Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void getData() {
        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);


        String url = "http://subga.info/Assets/get_data/data_file.php?kategori=2&internal=%27E%27&urut="+urutposisi;


        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                tl.removeAllViews();
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
        int c = result.length()+1;

        for (int u = 0; u < c; u++) {
            row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            if (u ==0) {
                for (int j = 0; j <= 7; j++) {
                    TextView lala = new TextView(getActivity());
                    if (j == 0) {
                        lala.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        //left top right bottom
                        lala.setPadding(25,25,50,25);
                        lala.setText("#");
                        row.addView(lala);
                        row.setBackgroundResource(R.color.Grey);
                    }
                    else if (j == 1) {
                        lala.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        lala.setPadding(25, 25, 50, 25);
                        lala.setText("Issue Date");
                        row.addView(lala);
                        row.setBackgroundResource(R.color.Grey);
                    }
                    else if (j == 2) {
                        lala.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        lala.setPadding(25, 25, 250, 25);
                        lala.setText("GA INFO");
                        row.addView(lala);
                        row.setBackgroundResource(R.color.Grey);
                    }
                    else if (j == 3) {
                        lala.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        lala.setPadding(25, 25, 450, 25);
                        lala.setText("Subject");
                        row.addView(lala);
                        row.setBackgroundResource(R.color.Grey);
                    }
                    else if (j == 4) {
                        lala.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        lala.setPadding(25, 25, 25, 25);
                        lala.setText("Hits");
                        row.addView(lala);
                        row.setBackgroundResource(R.color.Grey);
                    }
                    else if (j == 5) {
                        lala.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        lala.setPadding(25, 25, 350, 25);
                        lala.setText("Refer");
                        row.addView(lala);
                        row.setBackgroundResource(R.color.Grey);
                    }
                    else if (j == 6) {
                        lala.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        lala.setPadding(25, 25, 0, 25);
                        lala.setText("Action");
                        row.addView(lala);
                        row.setBackgroundResource(R.color.Grey);
                    }
                    else if (j == 7) {
                        lala.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        lala.setPadding(25, 25, 50, 25);
                        lala.setText(" ");
                        row.addView(lala);
                        row.setBackgroundResource(R.color.Grey);
                    }
                }
            }
            if (u>0) {
                int g =u -1;
                for (int j = 0; j <= 7; j++) {
                    TextView tv = new TextView(getActivity());
                    ImageButton ib = new ImageButton(getActivity());
                    ImageButton ibk = new ImageButton(getActivity());
                    if (j == 0) {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setPadding(25, 25, 25, 25);
                        tv.setText(Integer.toString(k));
                        k++;
                        row.addView(tv);
                    } else if (j == 1) {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setPadding(25, 25, 25, 25);
                        tv.setText(tgl_release[g]);
                        row.addView(tv);
                    } else if (j == 2) {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setPadding(25, 25, 25, 25);
                        tv.setText(ga_info[g]);
                        row.addView(tv);
                    } else if (j == 3) {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setPadding(25, 25, 25, 25);
                        tv.setText(subjek[g]);
                        tv.setEms(3);
                        row.addView(tv);
                    } else if (j == 4) {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setPadding(25, 25, 25, 25);
                        tv.setText(jml_download[g]);
                        row.addView(tv);
                    } else if (j == 5) {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setPadding(25, 25, 25, 25);
                        tv.setText(refer[g]);
                        tv.setEms(3);
                        row.addView(tv);
                    } else if (j == 6) {
                        ibk.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        ibk.setBackgroundResource(R.mipmap.btn_download);
                        ibk.getLayoutParams().width = 100;
                        ibk.getLayoutParams().height = 100;
                        row.addView(ibk);
                        final int finalU = g;
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
                        row.addView(ib);
                        ib.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {

                            }

                        });
                    }
                }
            }
            tl.addView(row);
        }
    }
}

