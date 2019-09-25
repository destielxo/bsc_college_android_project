package com.example.drishti.rsc;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

public class RegisterActivity extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2,e3,e4;
    CheckBox ch;
    dBHelper dB = new dBHelper(this);
    ImageView i1;
    ImageButton ib;
    private static final int CAMERA_PIC_REQUEST = 1337;

    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b1 = (Button)findViewById(R.id.button2);
        b2 = (Button)findViewById(R.id.button3);
        e1 = (EditText)findViewById(R.id.editText3);
        e2 = (EditText)findViewById(R.id.editText4);
        e3 = (EditText)findViewById(R.id.editText5);
        e4 = (EditText)findViewById(R.id.editText6);
        ch = (CheckBox)findViewById(R.id.checkBox);
        ib = (ImageButton)findViewById(R.id.imageButton3);
        i1 = (ImageView)findViewById(R.id.imageView);
        photo = BitmapFactory.decodeResource(this.getResources(), R.drawable.def_img);
    }

    public void save_data(View v) {
        String id = e1.getText().toString();
        String username = e2.getText().toString();
        String email = e3.getText().toString();
        String pass = e4.getText().toString();
        String is_admin = "";
        if(ch.isChecked())
            is_admin = "Yes";
        else
            is_admin = "No";
        byte[] encoded_photo =Utility.getBytes(photo);


        if (username.equals("") || email.equals("") || pass.equals("") || id.equals("")) {
            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
            return;
        }

        dB.insertContact(id,username, email, pass,is_admin,encoded_photo);

        Toast t = new Toast(getApplicationContext());
        t.makeText(getApplicationContext(),"Data Saved", Toast.LENGTH_LONG).show();
    }

    public void retrieve_data(View v)
    {
        String a = e1.getText().toString();
        Cursor cursor = dB.getData(a);
        while(cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("image"));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            //Toast.makeText(this, cursor.getString(1) + "  " + cursor.getString(2), Toast.LENGTH_LONG).show();
            e1.setText("" + cursor.getString(0));
            e2.setText("" + cursor.getString(1));
            e3.setText("" + cursor.getString(2));
            e4.setText("" + cursor.getString(3));
            i1.setImageBitmap(bitmap);
        }
    }

    public void update(View v)
    {
        String id = e1.getText().toString();
        String username = e2.getText().toString();
        String email = e3.getText().toString();
        String pass = e4.getText().toString();

        dB.updateContact(id,username,email,pass);
    }

    public void openCam(View v) {

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PIC_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView) findViewById(R.id.imageView);
            imageview.setImageBitmap(photo);
        }
    }

    public void del_data(View v)
    {
        String id = e1.getText().toString();
        try {
            dB.del_record(id);
        }catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

        Toast t = new Toast(getApplicationContext());
        t.makeText(getApplicationContext(),"Record Deleted", Toast.LENGTH_LONG).show();
    }
}
