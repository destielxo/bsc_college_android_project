package com.example.drishti.rsc;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {  Button b1,b2,b3,b4;
    ListView l;
    ArrayList<String> todoItems ;
    ArrayAdapter<String> aa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        b1 = (Button)findViewById(R.id.button5);
        b2 = (Button)findViewById(R.id.button6);
        b3 = (Button)findViewById(R.id.button7);
        b4 = (Button)findViewById(R.id.button8);
        l = (ListView)findViewById(R.id.listView);


    }

    public void cl_9(View v)
    {
        todoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
        dBHelper db=new dBHelper(this);
        Cursor res=db.getAllContacts1();
        while(res.moveToNext()) {
            todoItems.add(0, res.getString(0)+"  "+res.getString(1)+"  "+res.getString(2)+"  "+res.getString(3)+" "+res.getString(4));
            l.setAdapter(aa);
            aa.notifyDataSetChanged();
        }
    }

    public void cl_10(View v)
    {
        todoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
        dBHelper db=new dBHelper(this);
        Cursor res=db.getAllContacts2();
        while(res.moveToNext()) {
            todoItems.add(0, res.getString(0)+"  "+res.getString(1)+"  "+res.getString(2)+"  "+res.getString(3)+" "+res.getString(4));
            l.setAdapter(aa);
            aa.notifyDataSetChanged();

        }
    }

    public void cl_11(View v)
    {
        todoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
        dBHelper db=new dBHelper(this);
        Cursor res=db.getAllContacts3();
        while(res.moveToNext()) {
            todoItems.add(0, res.getString(0)+"  "+res.getString(1)+"  "+res.getString(2)+"  "+res.getString(3)+" "+res.getString(4));
            l.setAdapter(aa);
            aa.notifyDataSetChanged();
        }
    }

    public void cl_12(View v)
    {
        todoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
        dBHelper db=new dBHelper(this);
        Cursor res=db.getAllContacts4();
        while(res.moveToNext()) {
            todoItems.add(0, res.getString(0)+"  "+res.getString(1)+"  "+res.getString(2)+"  "+res.getString(3)+" "+res.getString(4));
            l.setAdapter(aa);
            aa.notifyDataSetChanged();
        }
    }

}
