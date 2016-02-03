package com.android4dev.navigationview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
    public static final String DATA_MEMBER_COLUMN_MERK_BRAND = "merk_brand_alias";
    public static final String DATA_MEMBER_COLUMN_ALAMAT_KANTOR = "alamat_kantor";
    public static final String DATA_MEMBER_COLUMN_KOTA = "kota";
    public static final String DATA_MEMBER_COLUMN_KODEPOS =  "kode_pos";
    public static final String DATA_MEMBER_COLUMN_JENIS_KANTOR = "jenis_kantor";
    public static final String DATA_MEMBER_COLUMN_TELEPHONE_KANTOR = "no_telepon";
    public static final String DATA_MEMBER_COLUMN_EMAIL_COMPANY = "email_kantor";
    public static final String DATA_MEMBER_COLUMN_NAMA_PIMPINAN = "nama_pimpinan";
    public static final String DATA_MEMBER_COLUMN_JABATAN_PIMPINAN =  "jabatan";
    public static final String DATA_MEMBER_COLUMN_NO_HP_PIM = "nomor_hp_pimpinan_kantor";
    public static final String DATA_MEMBER_COLUMN_USERNAME = "username";
    public static final String DATA_MEMBER_COLUMN_PASSWORD = "password";
    public static final String DATA_MEMBER_COLUMN_NAMA_LENGKAP_USER = "nama_lengkap_member";
    public static final String DATA_MEMBER_COLUMN_JABATAN_USER = "jabatan_member";
    public static final String DATA_MEMBER_COLUMN_EMAIL_USER = "email_member";
    public static final String DATA_MEMBER_COLUMN_NO_HP_USER = "nomor_hp_member";
    public static final String DATA_MEMBER_COLUMN_NPWP = "no_npwp";
    public static final String DATA_MEMBER_COLUMN_IJIN_USAHA = "ijin_usaha_kantor";
    public static final String DATA_MEMBER_COLUMN_AKTA_PENDIRIAN = "akta_pendirian_kantor";
    public static final String DATA_MEMBER_COLUMN_FAX_KANTOR =  "fax_kantor";
    public static final String DATA_MEMBER_COLUMN_JENIS_MEMBER = "jenis_member";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table data_member " +
                        "(id_member,nama_perusahaan,merk_brand_alias,alamat_kantor,kota,kode_pos,jenis_kantor,no_telepon,fax_kantor,email_kantor,nama_pimpinan,jabatan,nomor_hp_pimpinan_kantor,username, password, nama_lengkap_member,jabatan_member,email_member,nomor_hp_member,akta_pendirian_kantor,ijin_usaha_kantor,no_npwp,jenis_member)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS data_member");
        onCreate(db);
    }

    public boolean insertMember(String id_member,String nama_perusahaan,String merk_brand_alias,String alamat_kantor,String kota,String kode_pos,String jenis_kantor,String no_telepon,String fax_kantor,String email_kantor,String nama_pimpinan,String jabatan,String nomor_hp_pimpinan_kantor,String username, String password, String nama_lengkap_member,String jabatan_member,String email_member,String nomor_hp_member,String akta_pendirian_kantor,String ijin_usaha_kantor,String no_npwp,String jenis_member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_member", id_member);
        contentValues.put("nama_perusahaan", nama_perusahaan);
        contentValues.put("merk_brand_alias", merk_brand_alias);
        contentValues.put("alamat_kantor", alamat_kantor);
        contentValues.put("kota", kota);
        contentValues.put("kode_pos", kode_pos);
        contentValues.put("id_member", id_member);
        contentValues.put("jenis_kantor", jenis_kantor);
        contentValues.put("no_telepon", no_telepon);
        contentValues.put("fax_kantor", fax_kantor);
        contentValues.put("email_kantor", email_kantor);
        contentValues.put("nama_pimpinan", nama_pimpinan);
        contentValues.put("jabatan", jabatan);
        contentValues.put("id_member", id_member);
        contentValues.put("nomor_hp_pimpinan_kantor",nomor_hp_pimpinan_kantor);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("nama_lengkap_member", nama_lengkap_member);
        contentValues.put("jabatan_member", jabatan_member);
        contentValues.put("email_member", email_member);
        contentValues.put("nomor_hp_member", nomor_hp_member);
        contentValues.put("no_npwp", no_npwp);
        contentValues.put("ijin_usaha_kantor", ijin_usaha_kantor);
        contentValues.put("akta_pendirian_kantor", akta_pendirian_kantor);
        contentValues.put("jenis_member", jenis_member);

        db.insert("data_member", null, contentValues);
        return true;
    }

    public Cursor getData(String id_member) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id_member=" + id_member + "", null);
        return res;
    }

    public Cursor getDataLogin(String username) {
        Cursor res = null;
        SQLiteDatabase db = null;
        try {
            db = this.getReadableDatabase();
            res = db.rawQuery("select * from contacts where username=" + username + "", null);
        }catch (Exception e){
            return res;
        }

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