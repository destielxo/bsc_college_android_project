package com.example.drishti.rsc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

public class AdminActivity extends AppCompatActivity {

    RelativeLayout rl1;
    TextView t,t1,t2,t3;
    dBHelper dB;
    ImageView iv;
    String email_ID;
    Bitmap photo;
    Cursor cursor;

    @Override
    protected void onStart()
    {
        super.onStart();
        Cursor cursor = dB.getData();
        // Toast.makeText(this,c.getColumnCount(),Toast.LENGTH_LONG).show();

        while(cursor.moveToNext()){
            if(cursor.getString(2).equals(email_ID)) {
                t1.setText("" + cursor.getString(1));
                t2.setText("" + cursor.getString(2));

                byte[] blob = cursor.getBlob(cursor.getColumnIndex("image"));
                ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                iv.setImageBitmap(bitmap);
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        t = (TextView)findViewById(R.id.textView2);
        t1 = (TextView)findViewById(R.id.textView15);
        t2 = (TextView)findViewById(R.id.textView16);
        t3 = (TextView)findViewById(R.id.textView);
        iv = (ImageView)findViewById(R.id.imageView3);
        rl1 = (RelativeLayout)findViewById(R.id.layout1);

        dB = new dBHelper(this);

        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mySharedPreferences = getSharedPreferences(email_ID,mode);
        email_ID = mySharedPreferences.getString("email"," ");
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.menu_register:
                Intent i3 = new Intent(this,RegisterActivity.class);
                startActivity(i3);
                return true;
            case R.id.menu_data:
                Intent i4 = new Intent(this,DataActivity.class);
                startActivity(i4);
                return true;
            case R.id.menu_logout:
                finish();

                Toast myToast = new Toast((getApplicationContext()));
                myToast.makeText(getApplicationContext(), "Successfully Logged Off", Toast.LENGTH_LONG).show();
        }
        return true;


    }

    public void msg(View v)
    {
        Intent i5 = new Intent(this,NotifyActivity.class);
        startActivity(i5);
    }
}

