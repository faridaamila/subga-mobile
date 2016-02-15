package com.android4dev.navigationview;

import android.util.Log;

/**
 * Created by faridaamila on 12/02/2016.
 */
public class Member {
    private String username;
    private String jenis_member;
    private String nama_company;

    public Member(String username, String jenis_member, String nama_company) {
        this.username = username;
        this.jenis_member = jenis_member;
        this.nama_company = nama_company;
        Log.d("username : ",username);
        Log.d("jenis_member : ",jenis_member);
        Log.d("nama_company : ",nama_company);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJenis_member(String jenis_member) {
        this.jenis_member = jenis_member;
    }

    public void setNama_company(String nama_company) {
        this.nama_company = nama_company;
    }

    public String getUsername() {
        return username;
    }

    public String getJenis_member() {
        return jenis_member;
    }

    public String getNama_company() {
        return nama_company;
    }
}