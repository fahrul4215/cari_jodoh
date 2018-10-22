package com.iqbalfahrul.kelompok10.carijodoh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailUser extends AppCompatActivity {
    private TextView txtUsername, txtNama, txtJkl, txtAlamat;
    private ImageView imagefoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        initComponents();

        Intent i = getIntent();
        String nama = i.getStringExtra("nama");
        String username = i.getStringExtra("username");
        String jkl = i.getStringExtra("jkl");
        String alamat = i.getStringExtra("alamat");
        int foto = i.getIntExtra("foto", 0);

        txtUsername.setText(username);
        txtNama.setText(nama);
        txtJkl.setText(jkl);
        txtAlamat.setText(alamat);
        imagefoto.setImageResource(foto);
    }

    private void initComponents(){
        txtUsername = findViewById(R.id.viewUsername);
        txtNama = findViewById(R.id.viewNamaLengkap);
        txtJkl = findViewById(R.id.viewJK);
        txtAlamat = findViewById(R.id.viewAlamat);
        imagefoto = findViewById(R.id.viewFoto);
    }
}