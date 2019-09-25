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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

public class StudentActivity extends AppCompatActivity {

    ImageButton ib;
    String email_ID;
    dBHelper dB;
    TextView t1,t2;
    ImageView iv;
    Bitmap photo;
    Cursor cursor;

    @Override
    protected void onStart()
    {
        super.onStart();
        Cursor  cursor = dB.getData();
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
        setContentView(R.layout.activity_student);
        ib = (ImageButton)findViewById(R.id.imageButton2);
        t1 = (TextView)findViewById(R.id.textView11);
        t2 = (TextView)findViewById(R.id.textView13);
        iv = (ImageView)findViewById(R.id.imageView4);

        dB = new dBHelper(this);

        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mySharedPreferences = getSharedPreferences(email_ID,mode);
        email_ID = mySharedPreferences.getString("email"," ");
        Toast.makeText(this,email_ID,Toast.LENGTH_LONG).show();
        photo = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img);

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {

            case R.id.menu_coff:
                Intent i4 = new Intent(this,CoursesActivity.class);
                startActivity(i4);
                return true;
            case R.id.menu_logout:
                finish();

                Toast myToast = new Toast((getApplicationContext()));
                myToast.makeText(getApplicationContext(), "Successfully Logged Off", Toast.LENGTH_LONG).show();
        }
        return true;


    }


    public void notify(View v) {
        Intent i = new Intent(this, NotificationActivity.class);
        startActivity(i);
    }
}
