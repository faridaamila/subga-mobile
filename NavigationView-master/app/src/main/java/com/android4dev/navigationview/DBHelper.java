package com.android4dev.navigationview;

/**
 * Created by faridaamila on 14/02/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "subgamobi_db";
    public static final String MEMBER_TABLE_NAME = "table_member";
    public static final String MEMBER_COLUMN_ID = "_id";
    public static final String MEMBER_COLUMN_USERNAME = "username";
    public static final String MEMBER_COLUMN_NAMA_COMPANY = "nama_company";
    public static final String MEMBER_COLUMN_JENIS_MEMBER = "jenis_member";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table table_member " +
                        "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT,nama_company TEXT,jenis_member TEXT)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS table_member");
        onCreate(db);
    }

    public boolean insertMember  (String username, String jenis_member, String nama_company)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MEMBER_COLUMN_USERNAME, username);
        contentValues.put(MEMBER_COLUMN_JENIS_MEMBER, jenis_member);
        contentValues.put(MEMBER_COLUMN_NAMA_COMPANY, nama_company);
        db.insert(MEMBER_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getMember(String username){
        //SQLiteDatabase db = this.getReadableDatabase();
        /*Cursor res = db.query
                (
                        MEMBER_TABLE_NAME,
                        new String[] { MEMBER_COLUMN_ID, MEMBER_COLUMN_USERNAME, MEMBER_COLUMN_NAMA_COMPANY, MEMBER_COLUMN_JENIS_MEMBER },
                        MEMBER_COLUMN_USERNAME + "=" + username,
                        null, null, null, null, null
                );*/
        //Cursor res = db.query("table_member", columns, "username=?", new String[] { username }, null, null, null);
        //Cursor res =  db.rawQuery( "select * from table_member where username = '"+username+"'", null );
        Cursor res = getReadableDatabase().
                rawQuery("select * from table_member where username = '"+username+"'", null);

        return res;
    }

    /*public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MEMBER_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    */

    public ArrayList<String> getAllMember()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from table_member", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(MEMBER_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}