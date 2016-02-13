package com.android4dev.navigationview;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by S. Harsono on 1/27/2016.
 */
public class Nav_setting extends Fragment {
    private static final int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;
    private final String myBlog = "http://android-er.blogspot.com/";



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nav_setting,container,false);

        ImageButton btn_edit_profile = (ImageButton)v.findViewById(R.id.edit_profile);
        ImageButton btn_notificatios = (ImageButton)v.findViewById(R.id.notifications);
        ImageButton btn_help_center = (ImageButton)v.findViewById(R.id.help_center);
        ImageButton btn_about = (ImageButton)v.findViewById(R.id.about);

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_profile fragment = new Edit_profile();
                android.support.v4.app.FragmentManager fm1 = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.frame, fragment);
                ft1.addToBackStack(null);
                ft1.commit();
            }
        });
        btn_notificatios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btn_help_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Help_center fragment = new Help_center();
                android.support.v4.app.FragmentManager fm3 = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft3 = fm3.beginTransaction();
                ft3.replace(R.id.frame, fragment);
                ft3.addToBackStack(null);
                ft3.commit();
            }
        });
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                About fragment = new About();
                android.support.v4.app.FragmentManager fm4 = getFragmentManager();
                android.support.v4.app.FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.frame, fragment);
                ft4.addToBackStack(null);
                ft4.commit();
            }
        });

        return v;
    }
}

