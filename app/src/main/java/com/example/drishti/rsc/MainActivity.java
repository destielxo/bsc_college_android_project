package com.example.drishti.rsc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.mode;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    public static final String emailId = "mySharedPreferences";
    Button b;
    dBHelper dB = new dBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        b = (Button)findViewById(R.id.button);
    }

    public void login(View v) {
        String email = e1.getText().toString();
        String password = e2.getText().toString();

        SharedPreferences mySharedPreferences = getSharedPreferences(emailId,mode);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("email",e1.getText().toString());
        editor.commit();

       Cursor cursor = dB.getData1(email);

        while (cursor.moveToNext()) {
            //  Toast.makeText(this, "Enter", Toast.LENGTH_LONG).show();
            if ((password.equals(cursor.getString(3)) && cursor.getString(4).equals("Yes")) || (email.equals("soumya") && password.equals("admin")) || (email.equals("drishti") && password.equals("admin"))) {
                Intent i1 = new Intent(this, AdminActivity.class);
                startActivity(i1);
                break;
            } else if (password.equals(cursor.getString(3)) && cursor.getString(4).equals("No")) {
                Intent i2 = new Intent(this, StudentActivity.class);
                startActivity(i2);
                break;
            } else {
                Toast.makeText(this, "Enter your details", Toast.LENGTH_LONG).show();
            }
        }
    }


}
