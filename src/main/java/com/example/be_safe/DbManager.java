package com.example.be_safe;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationListener;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class DbManager extends SQLiteOpenHelper implements LocationListener {


    public String[] phone;

    Context context;

    public DbManager(Context context) {
        super(context, "EmgProfile", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean remove(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from info where name=?", new String[]{name});
        if (c.getCount() > 0) {
            int result = db.delete("info", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean search(String a) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM info", null);
        String buffer = new String();

        Boolean test = false;
        if (c.getCount() == 0) {
            Toast.makeText(context, "No record!", Toast.LENGTH_SHORT).show();
        } else {
            c.moveToFirst();
            for(int i=0; i<2; i++) {
                buffer = c.getString(1);
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(buffer, null, "I am in trouble!! Help me!!" + a, null, null);
                    test = true;
                    // Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                c.moveToNext();
            }
        }
        return test;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        
    }
}



