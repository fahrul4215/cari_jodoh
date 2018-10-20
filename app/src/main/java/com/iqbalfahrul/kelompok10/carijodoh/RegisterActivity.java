package com.iqbalfahrul.kelompok10.carijodoh;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1;
    RadioGroup r1;
    RadioButton rb;
    EditText t1,t2,t3,t5;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new MyDataHelper(this);
        t1 = (EditText) findViewById(R.id.txt_nama);
        t2 = (EditText) findViewById(R.id.txt_username);
        t3 = (EditText) findViewById(R.id.txt_password);
        r1 = (RadioGroup) findViewById(R.id.r_jkl);

        t5 = (EditText) findViewById(R.id.txt_alamat);

        b1 = (Button) findViewById(R.id.btn_register);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = r1.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selectedId);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO user(nama,username,password,jkl,alamat,foto) values('" + t1.getText().toString() + "','" + t2.getText().toString() + "','" + t3.getText().toString() + "','" + t5.getText().toString() + "','" + rb.getText().toString() + "','NULL')");
                Toast.makeText(getApplicationContext(), " Berhasil Register", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
