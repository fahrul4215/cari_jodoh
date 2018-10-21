package com.iqbalfahrul.kelompok10.carijodoh;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserDashboardActivity extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    TextView t1,t2,t3,t4;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        dbHelper = new MyDataHelper(this);
       t1 = (TextView) findViewById(R.id.Uusername);
       t2 = (TextView) findViewById(R.id.Unama);
       t3 = (TextView) findViewById(R.id.Ujkl);
       t4 = (TextView) findViewById(R.id.Ualamat);
       i1 = (ImageView) findViewById(R.id.Uprofile);


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("Select * from user WHERE username = '"+getIntent().getStringExtra("username")+"'",null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            int post = cursor.getInt(6);
            i1.setImageResource(post);
            t1.setText(cursor.getString(2));
            t2.setText(cursor.getString(1));
            t3.setText(cursor.getString(4));
            t4.setText(cursor.getString(5));

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate dari menu; disini akan menambahkan item menu pada Actionbar
        getMenuInflater().inflate(R.menu.menu, menu);//Memanggil file bernama menu di folder menu
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
//                Toast.makeText(getApplicationContext(),"Login Admin",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(this.getApplicationContext(),LoginActivity.class);
//                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void button_edit(View view){

        Intent i = new Intent(this.getApplicationContext(), UserEditActivity.class);
        i.putExtra("username",t1.getText().toString());
        Toast.makeText(this.getApplicationContext(),
                "Edit Data",Toast.LENGTH_SHORT).show();
        this.startActivity(i);

    }
}
