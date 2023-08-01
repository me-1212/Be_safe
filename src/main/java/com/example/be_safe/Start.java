package com.example.be_safe;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Start extends AppCompatActivity {

    Button start;
    DbManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start = findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                Locate loc = new Locate();
                loc.onCreate(savedInstanceState);
                String l = Locate.loc;
//                final String[] loc = new String[1];
//                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
//
//                    fusedLocationProviderClient.getLastLocation()
//                            .addOnSuccessListener(new OnSuccessListener<Location>() {
//                                @Override
//                                public void onSuccess(Location location) {
//
//                                    if (location != null) {
//                                        Geocoder geocoder = new Geocoder(Start.this, Locale.getDefault());
//                                        List<Address> addresses = null;
//                                        try {
//                                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                                            loc[0] = "Latitude " + addresses.get(0).getLatitude() + "Longitude " + addresses.get(0).getLongitude();
//                                        } catch (IOException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }
//                            });
//                }
//                else{
//                    ActivityCompat.requestPermissions(Start.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
//                }


                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        dbm = new DbManager(getApplicationContext());
                        Boolean d = dbm.search(l);
                        //String sms = "I am in trouble";

                        if(d==true){
                            Toast.makeText(getApplicationContext(), "Message sent ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Message is not sent", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
                    }
                }

//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                    if(checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
//                        dbm = new DbManager(getApplicationContext());
//                        String[] phone = dbm.search();
//
//                        if(phone.equals(null)){
//                            Toast.makeText(getApplicationContext(), "No record found.", Toast.LENGTH_SHORT).show();
//                        }else{
//                        String sms = "I am in trouble";
//
//                        for(int i=0; i<phone.length; i++) {
//                            try {
//                                SmsManager smsManager = SmsManager.getDefault();
//                                smsManager.sendTextMessage(phone[i], null, sms, null, null);
//                                Toast.makeText(getApplicationContext(), "Message sent to " + phone[i], Toast.LENGTH_SHORT).show();
//                            }
//                            catch (Exception e){
//                                e.printStackTrace();
//                                Toast.makeText(getApplicationContext(), "Message is not sent", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }}
//            } else{
//                    requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
//                }
                }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigate, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.register:
                Intent intent = new Intent(Start.this, Register.class);
                startActivity(intent);
                return true;
            case R.id.view:
                Intent intent1 = new Intent(Start.this, Registered.class);
                startActivity(intent1);
                return true;
            case R.id.help:
                Intent intent2 = new Intent(Start.this, Instruction.class);
                startActivity(intent2);
                return true;
            case R.id.about:
                Intent intent3 = new Intent(Start.this, About.class);
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}