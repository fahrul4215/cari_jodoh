package com.iqbalfahrul.kelompok10.carijodoh;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private void tampilRecyclerView() {
        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        isiDataUser();
        rv.setAdapter(new MyDataAdapter(dataUsers));

        rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                final DataUser du = dataUsers.get(position);
                final CharSequence[] dialogitem = {"Lihat User", "Update User", "Hapus User"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboardActivity.this);

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 :
//                                Intent intent0 = new Intent(getApplicationContext(),
//                                        ViewMovie.class);
//                                intent0.putExtra("judul", selection);
//                                startActivity(intent0);
                                break;
                            case 1 :
//                                Intent intent1 = new Intent(getApplicationContext(),
//                                        UpdateMovie.class);
//                                intent1.putExtra("judul", selection);
//                                startActivity(intent1);
                                break;
                            case 2 :
                                SQLiteDatabase db = dataHelper.getWritableDatabase();
                                db.delete("user","_id = '"+ du.getId() +"'",
                                        null);
                                Toast.makeText(getApplicationContext(),
                                        "User Berhasil Dihapus", Toast.LENGTH_LONG).show();
                                tampilRecyclerView();
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
        DataUser dataUser = new DataUser();
        dataHelper = new MyDataHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM user WHERE level != 1", null);
        cursor.moveToFirst();

        for (int cc=0;cc < cursor.getCount();cc++){
            cursor.moveToPosition(cc);
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
}
