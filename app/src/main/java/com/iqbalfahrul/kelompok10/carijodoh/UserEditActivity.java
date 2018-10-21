package com.iqbalfahrul.kelompok10.carijodoh;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class UserEditActivity extends AppCompatActivity {
    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1;
    EditText t1,t2,t3,t4;
    RadioGroup r1;
    RadioButton rb, rp,rw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);


        dbHelper = new MyDataHelper(this);
        t1 = (EditText) findViewById(R.id.Etxt_nama);
        t2 = (EditText) findViewById(R.id.Etxt_username);
        t3 = (EditText) findViewById(R.id.Etxt_password);
        t4 = (EditText) findViewById(R.id.Etxt_alamat);
        r1 = (RadioGroup) findViewById(R.id.Er_jkl);
        rp = (RadioButton) findViewById(R.id.Eradio_pria);
        rw = (RadioButton) findViewById(R.id.Eradio_wanita);
        btn1 = (Button) findViewById(R.id.btn_edit);



        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("Select * from user WHERE username = '"+getIntent().getStringExtra("username")+"'",null);
        cursor.moveToFirst();
        int id = r1.getCheckedRadioButtonId();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            if(cursor.getString(4).equals("Pria")) {
                rp.setChecked(true);
            }else {
                rw.setChecked(true);
            }
            rb = (RadioButton) findViewById(id);
            t1.setText(cursor.getString(1));
            t2.setText(cursor.getString(2));
            t3.setText(cursor.getString(3));
            t4.setText(cursor.getString(5));

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedId = r1.getCheckedRadioButtonId();
                    rb = (RadioButton) findViewById(selectedId);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL(" UPDATE user set nama = '" + t1.getText().toString() + "'," +
                            "username = '"+t2.getText().toString()+"'," +
                            "password = '"+t3.getText().toString()+"'," +
                            "jkl = '"+rb.getText().toString()+"'," +
                            "alamat = '"+t4.getText().toString()+"' where username = '"+getIntent().getStringExtra("username")+"'");
                    Toast.makeText(getApplicationContext(), " Berhasil Register", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),UserDashboardActivity.class);
                    i.putExtra("username",t2.getText().toString());
                    startActivity(i);
                }
            });

        }

    }
}
