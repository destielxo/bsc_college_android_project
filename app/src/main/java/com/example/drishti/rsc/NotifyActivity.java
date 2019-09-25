package com.example.drishti.rsc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotifyActivity extends AppCompatActivity {
    EditText e;
    Button b1, b2, b3, b4;
    dBHelper dB = new dBHelper(this);
    String from1;
    String email_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        e = (EditText) findViewById(R.id.editText7);
        b1 = (Button) findViewById(R.id.button9);
        b2 = (Button) findViewById(R.id.button10);
        b3 = (Button) findViewById(R.id.button11);
        b4 = (Button) findViewById(R.id.button12);

        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mySharedPreferences = getSharedPreferences(email_ID, mode);

        from1 = mySharedPreferences.getString("email", " ");

    }

    public void del(View v)
    {
        dB.delData();
    }

    public void br_9(View v) {
        String msg = e.getText().toString();
        Cursor cursor = dB.get_email9();
        while (cursor.moveToNext()) {
            String to1 = cursor.getString(0);
            dB.insertMsg(to1, from1, msg);
        }
        Toast t = new Toast(getApplicationContext());
        t.makeText(getApplicationContext(),"Deleted", Toast.LENGTH_LONG).show();

    }

    public void br_10(View v)
    {
        String msg = e.getText().toString();
        Cursor cursor = dB.get_email10();
        while (cursor.moveToNext()) {
            String to1 = cursor.getString(0);
            dB.insertMsg(to1, from1, msg);
        }
        Toast t = new Toast(getApplicationContext());
        t.makeText(getApplicationContext(),"Message Sent", Toast.LENGTH_LONG).show();
    }

    public void br_11(View v)
    {
        String msg = e.getText().toString();
        Cursor cursor = dB.get_email11();
        while (cursor.moveToNext()) {
            String to1 = cursor.getString(0);
            dB.insertMsg(to1, from1, msg);
        }
        Toast t = new Toast(getApplicationContext());
        t.makeText(getApplicationContext(),"Message Sent", Toast.LENGTH_LONG).show();
    }

    public void br_12(View v)
    {
        String msg = e.getText().toString();
        Cursor cursor = dB.get_email12();
        while (cursor.moveToNext()) {
            String to1 = cursor.getString(0);
            dB.insertMsg(to1, from1, msg);
        }
        Toast t = new Toast(getApplicationContext());
        t.makeText(getApplicationContext(),"Message Sent", Toast.LENGTH_LONG).show();
    }

}
