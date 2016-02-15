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
    public static final String MEMBER_COLUMN_ID = "id";
    public static final String MEMBER_COLUMN_USERNAME = "username";
    public static final String MEMBER_COLUMN_NAMA_COMPANY = "nama_company";
    public static final String MEMBER_COLUMN_JENIS_MEMBER = "jenis_member";

    public static final String BOOKMARK_TABLE_NAME = "table_bookmark";
    public static final String BOOKMARK_COLUMN_ID = "id";
    public static final String BOOKMARK_COLUMN_ID_FILE = "id_file";

    public static final String NEW_TABLE_NAME = "table_new";
    public static final String NEW_COLUMN_ID = "id";
    public static final String NEW_COLUMN_ID_FILE_LAMA = "id_file_lama";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + MEMBER_TABLE_NAME + " ( " +
                        MEMBER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                        MEMBER_COLUMN_USERNAME + " TEXT NOT NULL , " +
                        MEMBER_COLUMN_JENIS_MEMBER + " TEXT NOT NULL , " +
                        MEMBER_COLUMN_NAMA_COMPANY + " TEXT NOT NULL );"
        );
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + BOOKMARK_TABLE_NAME + " ( " +
                        BOOKMARK_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                        BOOKMARK_COLUMN_ID_FILE + " INTEGER NOT NULL );"
        );
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + NEW_TABLE_NAME + " ( " +
                        NEW_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                        NEW_COLUMN_ID_FILE_LAMA + " INTEGER NOT NULL );"
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
        long lala = db.insert(MEMBER_TABLE_NAME, null, contentValues);
        Log.d("insert", String.valueOf(lala));
        return true;
    }

    public boolean insertBookmark  (int id_file)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOKMARK_COLUMN_ID_FILE, id_file);
        long lala = db.insert(BOOKMARK_TABLE_NAME, null, contentValues);
        Log.d("insert", String.valueOf(lala));
        return true;
    }

    public boolean insertNew  (int id_file)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NEW_COLUMN_ID_FILE_LAMA, id_file);
        long lala = db.insert(NEW_COLUMN_ID_FILE_LAMA, null, contentValues);
        Log.d("insert", String.valueOf(lala));
        return true;
    }

    public Member getMember(String username){
        Member memberku = null;
        Cursor res = getReadableDatabase().
                rawQuery("SELECT * FROM table_member WHERE " + MEMBER_COLUMN_USERNAME + "='" + username + "'", null);
        if (res != null && res.moveToFirst() ) {
                memberku = new Member(res.getString(res.getColumnIndex("username")),res.getString(res.getColumnIndex("jenis_member")),res.getString(res.getColumnIndex("nama_company")));
        }
        else
            Log.d("member error : ", " = null");

        return memberku;
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