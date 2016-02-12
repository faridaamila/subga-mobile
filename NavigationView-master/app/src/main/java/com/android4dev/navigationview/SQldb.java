package com.android4dev.navigationview;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by faridaamila on 11/02/2016.
 */
public class SQldb extends Activity {
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void openDatabase() {
        try {
            db = SQLiteDatabase.openDatabase(
                    "data/data/com.android4dev.navigationview/subgamobi_db",
                    null,
                    SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (isTableExists()==false) {
            makeTable();
        }
    }

    private void makeTable() {
        //create table: table_member
        db.beginTransaction();
        try {
            db.execSQL("create table table_member("
                    + " id_member integer PRIMARY KEY autoincrement, "
                    + " username text, "
                    + " jenis_member text, "
                    + " nama_company text,"
                    + " rememberme_code integer); ");
            db.execSQL("create table table_bookmark("
                    + " id_bookmark integer PRIMARY KEY autoincrement, "
                    + " id_file integer, "
                    + " bookmark_code integer); ");
            db.execSQL("create table table_download("
                    + " id_download integer PRIMARY KEY autoincrement, "
                    + " id_file integer, "
                    + " download_code integer); ");
            db.execSQL("create table table_new("
                    + " id_new integer PRIMARY KEY autoincrement, "
                    + " id_file_lama integer); ");
            //commit your changes
            db.setTransactionSuccessful();
        } finally {
            //finish transaction processing
            db.endTransaction();
        }
    }

    private boolean isTableExists() {
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = 'table_member'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }


    public void insertMember(String username_get,String jenis_member_get, String nama_company_get,int remember_me_get) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("username", username_get);
        initialValues.put("jenis_member", jenis_member_get);
        initialValues.put("nama_company", nama_company_get);
        initialValues.put("rememberme_code", remember_me_get);
        db.insert("table_member", null, initialValues);
    }
}
