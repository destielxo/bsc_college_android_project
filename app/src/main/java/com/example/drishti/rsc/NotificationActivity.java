package com.example.drishti.rsc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    String email_ID;
    dBHelper dB;
    ListView l1;
    ArrayList<String> todoItems ;
    ArrayAdapter<String> aa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        l1 = (ListView)findViewById(R.id.listView2);

        dB = new dBHelper(this);

        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mySharedPreferences = getSharedPreferences(email_ID,mode);
        email_ID = mySharedPreferences.getString("email"," ");
    }

    public void refresh(View v)
    {
        int a=0;
        todoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);


        Cursor res = dB.getMsg();
        while(res.moveToNext()){
            Toast.makeText(this,email_ID +" "+res.getString(1)+" "+res.getString(3),Toast.LENGTH_LONG).show();

            //a++;
            if(res.getString(1).trim().equals(email_ID.trim()))
            {
                todoItems.add(0, res.getString(3)+" "+res.getString(2)+" "+res.getString(1));
                l1.setAdapter(aa);
                aa.notifyDataSetChanged();
            }
        }

    }

}
