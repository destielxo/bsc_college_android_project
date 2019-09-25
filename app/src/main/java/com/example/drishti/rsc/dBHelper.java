package com.example.drishti.rsc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Drishti on 13-10-2017.
 */
public class dBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Login.db";
    public static final String CONTACTS1_TABLE_NAME = "STUDENT";
    public static final String CONTACTS1_COLUMN_ID = "id";
    public static final String CONTACTS1_COLUMN_USERNAME = "username";
    public static final String CONTACTS1_COLUMN_EMAIL = "email";
    public static final String CONTACTS1_COLUMN_PASSWORD = "password";

    public static final String TABLE_NAME = "MESSAGE";
    public static final String COLUMN_ID = "sno";
    public static final String CONTACTS1_COLUMN_TO = "to";
    public static final String CONTACTS1_COLUMN_FROM = "from";
    public static final String CONTACTS1_COLUMN_MSG = "msg";

    public dBHelper(Context context)

    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(

                "create table MESSAGE " +
                        "(sno integer primary key autoincrement, to1 text , from1 text , msg text)"
        );

        db.execSQL(
                "create table STUDENT " +
                        "(id text primary key , username text , email text , password text, is_admin text , image BLOB)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS STUDENT");
        db.execSQL("DROP TABLE IF EXISTS MESSAGE");
        onCreate(db);
    }

    public boolean insertContact (String id,String username , String email, String password, String is_admin, byte[] encoded_photo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("is_admin",is_admin);
        contentValues.put("image",encoded_photo);

        db.insert("STUDENT", null, contentValues);
        return true;
    }

    public boolean insertMsg  ( String to1, String from1, String msg)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("to1",to1);
        contentValues.put("from1",from1);
        contentValues.put("msg",msg);

        db.insert("MESSAGE", null, contentValues);
        return true;
    }

    public ArrayList getAllContacts()
    {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from STUDENT", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS1_COLUMN_USERNAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getAllContacts1()
    {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from STUDENT where id like 'N%'", null );
        return res;
    }

    public Cursor getAllContacts2()
    {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from STUDENT where id like 'T%'", null );
        return res;
    }

    public Cursor getAllContacts3()
    {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from STUDENT where id like 'E%'", null );
        return res;
    }

    public Cursor getAllContacts4()
    {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from STUDENT where id like 'TW%'", null );
        return res;
    }

    public Cursor getData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from STUDENT where id='"+id+"'", null );
        return res;
    }

    public Cursor getData1(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from STUDENT where email='"+email+"'", null );
        return res;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from STUDENT ", null );
        return res;
    }

    public Cursor getMsg(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select msg from MESSAGE where to1='"+email+"'", null );
        return res;
    }
    public Cursor getMsg()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from MESSAGE", null );
        return res;
    }
    public void delData (){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("MESSAGE",null,null);
    }

    public boolean updateContact (String id, String username , String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        db.update("STUDENT", contentValues, "id = ? ", new String[] { id } );

        return true;
    }

    public boolean del_record(String id)
    {
        SQLiteDatabase dB = this.getReadableDatabase();
        dB.delete("STUDENT", "id=?", new String[]{id});
        return true;
    }

    public Cursor get_email9()
    {
        SQLiteDatabase dB = this.getReadableDatabase();
        Cursor res = dB.rawQuery("select email from STUDENT where id like 'N%'",null);
        return res;
    }

    public Cursor get_email10()
    {
        SQLiteDatabase dB = this.getReadableDatabase();
        Cursor res = dB.rawQuery("select email from STUDENT where id like 'T%'",null);
        return res;
    }

    public Cursor get_email11()
    {
        SQLiteDatabase dB = this.getReadableDatabase();
        Cursor res = dB.rawQuery("select email from STUDENT where id like 'E%'",null);
        return res;
    }

    public Cursor get_email12()
    {
        SQLiteDatabase dB = this.getReadableDatabase();
        Cursor res = dB.rawQuery("select email from STUDENT where id like 'TW%'",null);
        return res;
    }

}
