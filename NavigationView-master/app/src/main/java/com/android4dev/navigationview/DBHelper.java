package com.android4dev.navigationview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by faridaamila on 27/01/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_subgamobi";
    public static final String DATA_MEMBER_TABLE = "data_member";
    public static final String DATA_MEMBER_COLUMN_ID_MEMBER = "id_member";
    public static final String DATA_MEMBER_COLUMN_NAMA_PERUSAHAAN = "nama_perusahaan";
    public static final String DATA_MEMBER_COLUMN_MERK_BRAND = "merk_brand";
    public static final String DATA_MEMBER_COLUMN_ALAMAT_KANTOR = "alamat_kantor";
    public static final String DATA_MEMBER_COLUMN_KOTA = "kota";
    public static final String DATA_MEMBER_COLUMN_KODEPOS = "kode_pos";
    public static final String DATA_MEMBER_COLUMN_JENIS_KANTOR = "jenis_kantor";
    public static final String DATA_MEMBER_COLUMN_TELEPHONE_KANTOR = "telp_kantor";
    public static final String DATA_MEMBER_COLUMN_EMAIL_COMPANY = "email_company";
    public static final String DATA_MEMBER_COLUMN_NAMA_PIMPINAN = "nama_pimpinan";
    public static final String DATA_MEMBER_COLUMN_JABATAN_PIMPINAN = "jabatan_pim";
    public static final String DATA_MEMBER_COLUMN_NO_HP_PIM = "no_hp_pim";
    public static final String DATA_MEMBER_COLUMN_NAMA_USER = "username";
    public static final String DATA_MEMBER_COLUMN_NAMA_LENGKAP = "nama_lengkap";
    public static final String DATA_MEMBER_COLUMN_JABATAN_USER = "jabatan_user";
    public static final String DATA_MEMBER_COLUMN_EMAIL_USER = "email_user";
    public static final String DATA_MEMBER_COLUMN_NO_HP_USER = "no_hp_user";
    public static final String DATA_MEMBER_COLUMN_NPWP = "npwp";
    public static final String DATA_MEMBER_COLUMN_IJIN_USAHA = "ijin_usaha";
    public static final String DATA_MEMBER_COLUMN_AKTA_PENDIRIAN = "akta_pendirian";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table data_member " +
                        "(id_member,nama_perusahaan,merk_brand,alamat_kantor,kota,kode_pos" +
                        "jenis_kantor,telp_kantor,email_company,nama_pimpinan,jabatan_pim,no_hp_pim,username" +
                        "nama_lengkap,jabatan_user,email_user,no_hp_user,npwp,ijin_usaha,akta_pendirian)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertMember(String id_member, String nama_perusahaan, String merk_brand, String alamat_kantor, String kota, String kode_pos,
                                String jenis_kantor, String telp_kantor, String email_company, String nama_pimpinan, String jabatan_pim, String no_hp_pim, String username,
                                String nama_lengkap, String jabatan_user, String email_user, String no_hp_user, String npwp, String ijin_usaha, String akta_pendirian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_member", id_member);
        contentValues.put("nama_perusahaan", nama_perusahaan);
        contentValues.put("merk_brand", merk_brand);
        contentValues.put("alamat_kantor", alamat_kantor);
        contentValues.put("kota", kota);
        contentValues.put("kode_pos", kode_pos);
        contentValues.put("id_member", id_member);
        contentValues.put("jenis_kantor", jenis_kantor);
        contentValues.put("telp_kantor", telp_kantor);
        contentValues.put("email_company", email_company);
        contentValues.put("nama_pimpinan", nama_pimpinan);
        contentValues.put("jabatan_pim", jabatan_pim);
        contentValues.put("id_member", id_member);
        contentValues.put("no_hp_pim", no_hp_pim);
        contentValues.put("username", username);
        contentValues.put("nama_lengkap", nama_lengkap);
        contentValues.put("jabatan_user", jabatan_user);
        contentValues.put("email_user", email_user);
        contentValues.put("no_hp_user", no_hp_user);
        contentValues.put("npwp", npwp);
        contentValues.put("ijin_usaha", ijin_usaha);
        contentValues.put("akta_pendirian", akta_pendirian);

        db.insert("data_member", null, contentValues);
        return true;
    }

    public Cursor getData(int id_member) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id_member=" + id_member + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DATA_MEMBER_TABLE);
        return numRows;
    }

    public boolean updateMember(String id_member, String nama_perusahaan, String merk_brand, String alamat_kantor, String kota, String kode_pos,
                                String jenis_kantor, String telp_kantor, String email_company, String nama_pimpinan, String jabatan_pim, String no_hp_pim, String username,
                                String nama_lengkap, String jabatan_user, String email_user, String no_hp_user, String npwp, String ijin_usaha, String akta_pendirian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_member", id_member);
        contentValues.put("nama_perusahaan", nama_perusahaan);
        contentValues.put("merk_brand", merk_brand);
        contentValues.put("alamat_kantor", alamat_kantor);
        contentValues.put("kota", kota);
        contentValues.put("kode_pos", kode_pos);
        contentValues.put("id_member", id_member);
        contentValues.put("jenis_kantor", jenis_kantor);
        contentValues.put("telp_kantor", telp_kantor);
        contentValues.put("email_company", email_company);
        contentValues.put("nama_pimpinan", nama_pimpinan);
        contentValues.put("jabatan_pim", jabatan_pim);
        contentValues.put("id_member", id_member);
        contentValues.put("no_hp_pim", no_hp_pim);
        contentValues.put("username", username);
        contentValues.put("nama_lengkap", nama_lengkap);
        contentValues.put("jabatan_user", jabatan_user);
        contentValues.put("email_user", email_user);
        contentValues.put("no_hp_user", no_hp_user);
        contentValues.put("npwp", npwp);
        contentValues.put("ijin_usaha", ijin_usaha);
        contentValues.put("akta_pendirian", akta_pendirian);
        db.update("data_member", contentValues, "id = ? ", new String[]{id_member});
        return true;
    }

    public Integer deleteMember(String id_member) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[]{id_member});
    }
}