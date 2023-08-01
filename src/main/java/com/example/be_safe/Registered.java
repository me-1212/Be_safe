package com.example.be_safe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Registered extends AppCompatActivity {

    Cursor c;
    TextView txt;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        txt = findViewById(R.id.editTextTextMultiLine2);
        back = findViewById(R.id.button7);

        SQLiteDatabase db;
        db=openOrCreateDatabase("EmgProfile", Context.MODE_PRIVATE, null);

        c = db.rawQuery("SELECT * FROM info", null);
        if(c.getCount()==0)
        {
            showMessage("Error", "No records found.");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("Name: "+c.getString(0)+"\n");
            buffer.append("Phone: "+c.getString(1)+"\n");
        }

        txt.setText(buffer.toString());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registered.this, Start.class);
                startActivity(intent);
            }
        });
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}