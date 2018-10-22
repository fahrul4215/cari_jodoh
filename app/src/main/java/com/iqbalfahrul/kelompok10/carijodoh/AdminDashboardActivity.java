<<<<<<< HEAD
package com.iqbalfahrul.kelompok10.carijodoh;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.iqbalfahrul.kelompok10.carijodoh.models.DataUser;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<DataUser> dataUsers = new ArrayList<>();
    private MyDataHelper dataHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        tampilRecyclerView();
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
                Toast.makeText(getApplicationContext(),"Berhasil Log Out",Toast.LENGTH_SHORT).show();
                resetPreferences();
                Intent intent = new Intent(this.getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void tampilRecyclerView() {
        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        isiDataUser();
        rv.setAdapter(new MyDataAdapter(dataUsers));

        rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                final DataUser du = dataUsers.get(position);
                final CharSequence[] dialogitem = {"Lihat User", "Hapus User"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboardActivity.this);

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 :
                                Intent intent0 = new Intent(getApplicationContext(),
                                        DetailUser.class);
                                intent0.putExtra("nama", du.getNama());
                                intent0.putExtra("username", du.getUsername());
                                intent0.putExtra("jkl", du.getJkl());
                                intent0.putExtra("alamat", du.getAlamat());
                                intent0.putExtra("foto", du.getFoto());
                                startActivity(intent0);
                                break;
                            case 1 :
                                SQLiteDatabase db = dataHelper.getWritableDatabase();
                                db.delete("user","_id = '"+ du.getId() +"'",
                                        null);
                                Toast.makeText(getApplicationContext(),
                                        "User Berhasil Dihapus", Toast.LENGTH_LONG).show();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                                break;
                        }
                    }
                });
                builder.create().show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void isiDataUser(){
        dataHelper = new MyDataHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM user WHERE level != 1", null);
        cursor.moveToFirst();

        for (int cc=0;cc < cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            DataUser dataUser = new DataUser();
            dataUser.setId(cursor.getInt(0));
            dataUser.setNama(cursor.getString(1));
            dataUser.setUsername(cursor.getString(2));
            dataUser.setPassword(cursor.getString(3));
            dataUser.setJkl(cursor.getString(4));
            dataUser.setAlamat(cursor.getString(5));
            dataUser.setFoto(cursor.getInt(6));
            dataUser.setLevel(cursor.getInt(7));
            dataUsers.add(dataUser);
        }
    }

    private void resetPreferences(){
        SharedPreferences handler = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = handler.edit();
        editor.clear();
        editor.apply();
    }
}
=======
package com.iqbalfahrul.kelompok10.carijodoh;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.iqbalfahrul.kelompok10.carijodoh.models.DataUser;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<DataUser> dataUsers = new ArrayList<>();
    private MyDataHelper dataHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        tampilRecyclerView();
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
                Toast.makeText(getApplicationContext(),"Berhasil Log Out",Toast.LENGTH_SHORT).show();
                resetPreferences();
                Intent intent = new Intent(this.getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void tampilRecyclerView() {
        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        isiDataUser();
        rv.setAdapter(new MyDataAdapter(dataUsers));

        rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                final DataUser du = dataUsers.get(position);
                final CharSequence[] dialogitem = {"Lihat User", "Hapus User"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboardActivity.this);

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 :
                                Intent intent0 = new Intent(getApplicationContext(),
                                        DetailUser.class);
                                intent0.putExtra("nama", du.getNama());
                                intent0.putExtra("username", du.getUsername());
                                intent0.putExtra("jkl", du.getJkl());
                                intent0.putExtra("alamat", du.getAlamat());
                                intent0.putExtra("foto", du.getFoto());
                                startActivity(intent0);
                                break;
                            case 1 :
                                SQLiteDatabase db = dataHelper.getWritableDatabase();
                                db.delete("user","_id = '"+ du.getId() +"'",
                                        null);
                                Toast.makeText(getApplicationContext(),
                                        "User Berhasil Dihapus", Toast.LENGTH_LONG).show();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                                break;
                        }
                    }
                });
                builder.create().show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void isiDataUser(){
        dataHelper = new MyDataHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM user WHERE level != 1", null);
        cursor.moveToFirst();

        for (int cc=0;cc < cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            DataUser dataUser = new DataUser();
            dataUser.setId(cursor.getInt(0));
            dataUser.setNama(cursor.getString(1));
            dataUser.setUsername(cursor.getString(2));
            dataUser.setPassword(cursor.getString(3));
            dataUser.setJkl(cursor.getString(4));
            dataUser.setAlamat(cursor.getString(5));
            dataUser.setFoto(cursor.getInt(6));
            dataUser.setLevel(cursor.getInt(7));
            dataUsers.add(dataUser);
        }
    }

    private void resetPreferences(){
        SharedPreferences handler = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = handler.edit();
        editor.clear();
        editor.apply();
    }
}
>>>>>>> 2dd560258fe2ae046a40ea9b52785718402e020d
