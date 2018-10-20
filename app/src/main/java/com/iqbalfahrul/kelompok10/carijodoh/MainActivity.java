package com.iqbalfahrul.kelompok10.carijodoh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    protected Cursor cursor;
    MyDataHelper dbHelper;
    private CheckBox chkRemember;
    private Button btnLogin;
    private EditText txtUsername;
    private  EditText txtPassword;
    public int berhasil;
    public static MainActivity layarutama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initComponents();
        this.checkSavedCredentials();
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
                Toast.makeText(getApplicationContext(),"Login Admin",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this.getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initComponents(){
        dbHelper = new MyDataHelper(this);
        this.chkRemember = (CheckBox) findViewById(R.id.chk_remember);
        this.btnLogin = (Button) findViewById(R.id.btn_login);
        this.txtUsername = (EditText) findViewById(R.id.txt_username);
        this.txtPassword = (EditText) findViewById(R.id.txt_password);
    }

    public void button_login(View view){
        this.login();
    }

    public void button_register(View view){
        Intent intent = new Intent(this.getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }
    private void checkSavedCredentials(){
        SharedPreferences handler = this.getPreferences(Context.MODE_PRIVATE);

        String username= handler.getString("username","");
        String password= handler.getString("password","");
        boolean loginCorrect = this.checkCredentials(username,password);
        if(loginCorrect){
            this.openHome(username);
        }
    }

    private boolean checkCredentials(String username, String password) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("Select * from user WHERE username = '" + username + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            if (password.equals(cursor.getString(3).toString()))
              berhasil = 1;
            else
                berhasil = 0;
        }

    if(berhasil==1)
            return true;
        else
            return false;


    }

    private void login(){
        String username = this.txtUsername.getText().toString();
        String password = this.txtPassword.getText().toString();

        boolean loginCorrect = this.checkCredentials(username,password);
        if(loginCorrect){
            boolean remember = this.chkRemember.isChecked();
            if(remember){
                this.saveCredentials();
            }
            this.openHome(username);
        }else{
            Toast.makeText(this.getApplicationContext(), "Invalid username and/or password",Toast.LENGTH_SHORT).show();
        }
    }

    private void saveCredentials(){
        SharedPreferences handler = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = handler.edit();
        editor.putString("username",this.txtUsername.getText().toString());
        editor.putString("password",this.txtPassword.getText().toString());

        editor.apply();

    }
    private void openHome(String username){
        Intent i = new Intent(this.getApplicationContext(),UserDashboardActivity.class);
        i.putExtra("username",username);
        Toast.makeText(this.getApplicationContext(),"BERHASIL LOGIN",Toast.LENGTH_SHORT).show();
        this.startActivity(i);
    }
}