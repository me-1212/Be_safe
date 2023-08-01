package com.example.be_safe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements DeleteDialog.DeleteDialogListener {

    EditText name, phone;
    Button finish, back, del;
    DbManager dbm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        finish = findViewById(R.id.button);
        back = findViewById(R.id.but2);
        del = findViewById(R.id.b2);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeInDb(v);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Start.class) ;
                startActivity(intent);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void storeInDb(View v) {
        String str_name = " ";
        String str_phone =" ";

        Toast.makeText(getApplicationContext(), "Saving info.....", Toast.LENGTH_LONG).show();
        name = findViewById(R.id.editTextTextPersonName3);
        phone = findViewById(R.id.editTextPhone);

        if (name.getText().toString().equals(null) || phone.getText().toString().equals(null)){
            Toast.makeText(getApplicationContext(), "Empty field is not valid1", Toast.LENGTH_SHORT).show();
        }
        else{

            str_name = name.getText().toString();
            str_phone = phone.getText().toString();

            SQLiteDatabase db;
            db = openOrCreateDatabase("EmgProfile", Context.MODE_PRIVATE, null);
            //db created

            db.execSQL("CREATE TABLE IF NOT EXISTS info(name VARCHAR,phone VARCHAR);");
            //table created

            Cursor c = db.rawQuery("SELECT * FROM info", null);
            if (c.getCount() < 2) {
                db.execSQL("INSERT INTO info VALUES('" + str_name + "','" + str_phone + "');");
                Toast.makeText(getApplicationContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Maximum Numbers limited reached.", Toast.LENGTH_SHORT).show();
            }

            db.close();
        }
    }

    public void openDialog() {
        DeleteDialog deleteDialog = new DeleteDialog();
        deleteDialog.show(getSupportFragmentManager(), "delete box");
    }

    @Override
    public void applyTexts(String name) {
        dbm = new DbManager(this);
        Boolean a = dbm.remove(name);
        if(a==true){
            Toast.makeText(getApplicationContext(), "Deleted successfully!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Error occurred!", Toast.LENGTH_SHORT).show();
        }
    }
}