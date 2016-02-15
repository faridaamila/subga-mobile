package com.android4dev.navigationview;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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

public class MainActivity extends AppCompatActivity {
    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ProgressDialog loading;
    private TextView tv;

    ViewFlipper flip;
    int mFlipping = 0;

    Notification notification;
    private static final int NOTIFICATION_ID=1;
    DBHelper mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        TextView header_username = (TextView) findViewById(R.id.username_header);
        TextView company_header = (TextView) findViewById(R.id.company_header);
        Login loginku = new Login();
        Member memberku = mydb.getMember(loginku.username);
      //  header_username.setText(memberku.getUsername());
     //   company_header.setText(memberku.getNama_company());


        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        flip = (ViewFlipper) findViewById(R.id.viewFlipper1);
        if (mFlipping == 0) {
            flip.startFlipping();
            mFlipping = 1;
        } else {
            flip.stopFlipping();
            mFlipping = 0;
        }
        //////////NOTIICATION////////////



        //text marque
        tv = (TextView) findViewById(R.id.hot_news);
        // tv.setText("Mulai tanggal 12 Januari 2015 Garuda Indonesia membuka rute Surabaya ke Shanghai via Denpasar.");
        tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv.setSingleLine(true);
        tv.setMarqueeRepeatLimit(5);
        tv.setSelected(true);

        getData();

        //Image Button untuk navigasi
        ImageButton btn_new = (ImageButton) findViewById(R.id.btn_new);
        ImageButton btn_bookmark = (ImageButton) findViewById(R.id.btn_bookmark);
        ImageButton btn_download = (ImageButton) findViewById(R.id.btn_download);
        ImageButton btn_setting = (ImageButton) findViewById(R.id.btn_setting);

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nav_new fragment = new Nav_new();
                android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id.frame, fragment);
                ft1.addToBackStack(null);
                ft1.commit();
            }
        });
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Nav_bookmark fragment = new Nav_bookmark();
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        btn_download.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Nav_setting fragment = new Nav_setting();
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String title = getString(R.string.app_name);

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        Home fragment1 = new Home();
                        title = "SUBGA Mobile";
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, fragment1);
                        fragmentTransaction1.commit();
                        return true;
                    // For rest of the options we just show a toast on click
                    case R.id.add_on_yq:
                        Add_on fragment2 = new Add_on();
                        title = getString(R.string.add_on_yq);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, fragment2);
                        fragmentTransaction2.addToBackStack("Any name");
                        fragmentTransaction2.commit();
                        return true;
                    case R.id.cargo:
                        Cargo fragment3 = new Cargo();
                        title = getString(R.string.cargo);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fragment3);
                        fragmentTransaction3.addToBackStack("Any name");
                        fragmentTransaction3.commit();
                        return true;
                    case R.id.codeshare_and_spa:
                        Codeshare fragment4 = new Codeshare();
                        title = getString(R.string.codeshare_and_spa);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4);
                        fragmentTransaction4.addToBackStack("Any name");
                        fragmentTransaction4.commit();
                        return true;
                    case R.id.domestic:
                        Domestic fragment5 = new Domestic();
                        title = getString(R.string.domestic);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction5.replace(R.id.frame, fragment5);
                        fragmentTransaction5.addToBackStack("Any name");
                        fragmentTransaction5.commit();
                        return true;
                    case R.id.flight_schedule:
                        Flight_schedule fragment6 = new Flight_schedule();
                        title = getString(R.string.fligh_schedule);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction6 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction6.replace(R.id.frame, fragment6);
                        fragmentTransaction6.addToBackStack("Any name");
                        fragmentTransaction6.commit();
                        return true;
                    case R.id.internal_news:
                        Internal_news fragment7 = new Internal_news();
                        title = "Internal News";
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction7 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction7.replace(R.id.frame, fragment7);
                        fragmentTransaction7.addToBackStack("Any name");
                        fragmentTransaction7.commit();
                        return true;
                    case R.id.international:
                        International fragment8 = new International();
                        title = getString(R.string.international);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction8 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction8.replace(R.id.frame, fragment8);
                        fragmentTransaction8.addToBackStack("Any name");
                        fragmentTransaction8.commit();
                        return true;
                    case R.id.midle_east:
                        Middle_east fragment9 = new Middle_east();
                        title = getString(R.string.midle_east);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction9 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction9.replace(R.id.frame, fragment9);
                        fragmentTransaction9.addToBackStack("Any name");
                        fragmentTransaction9.commit();
                        return true;
                    case R.id.other:
                        Other fragment10 = new Other();
                        title = getString(R.string.other);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction10 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction10.replace(R.id.frame, fragment10);
                        fragmentTransaction10.addToBackStack("Any name");
                        fragmentTransaction10.commit();
                        return true;
                    case R.id.pin_hin:
                        Pin_hin_rin fragment11 = new Pin_hin_rin();
                        title = getString(R.string.pin_hin);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction11 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction11.replace(R.id.frame, fragment11);
                        fragmentTransaction11.addToBackStack("Any name");
                        fragmentTransaction11.commit();
                        return true;
                    case R.id.rsp_yearly:
                        Rsp_yearly fragment12 = new Rsp_yearly();
                        title = getString(R.string.rsp_yearly);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction12 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction12.replace(R.id.frame, fragment12);
                        fragmentTransaction12.addToBackStack("Any name");
                        fragmentTransaction12.commit();
                        return true;
                    case R.id.rules_and_procedure:
                        Rules_and_procedure fragment13 = new Rules_and_procedure();
                        title = getString(R.string.rules_and_procedure);
                        getSupportActionBar().setTitle(title);
                        android.support.v4.app.FragmentTransaction fragmentTransaction13 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction13.replace(R.id.frame, fragment13);
                        fragmentTransaction13.addToBackStack("Any name");
                        // fragmentTransaction13.addToBackStack(MainActivity.class);
                        fragmentTransaction13.commit();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

            // Initializing Drawer Layout and ActionBarToggle
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                    super.onDrawerOpened(drawerView);
                }
            };


        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            SharedPreferences sharedPrefs =getSharedPreferences(Login.PREFS_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.clear();
            editor.commit();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Anda Yakin Ingin Menutup Aplikasi?").
                    setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent exit = new Intent(
                            Intent.ACTION_MAIN);
                    exit.addCategory(Intent.CATEGORY_HOME);
                    exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(exit);
                }

            }).setNegativeButton("Tidak",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            // return true;
                        }
                    }).show();
        } else if (id == R.id.notifications){
            final NotificationManager mgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notification=new Notification(R.drawable.ic_home,"You've got a new notification", System.currentTimeMillis());
            //Intent to start new activity on click of expanded view
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
            notification.setLatestEventInfo(this, "You've got a new notification", "Check navigation New Document", pendingIntent);
            mgr.notify(NOTIFICATION_ID, notification);

        }


        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

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
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String isi_freetext = "";
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


    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            Home fragment1 = new Home();
            String title  = getString(R.string.app_name);
            getSupportActionBar().setTitle(title);
            android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.frame, fragment1);
            fragmentTransaction1.commit();

        } else {

        }
    }
}
