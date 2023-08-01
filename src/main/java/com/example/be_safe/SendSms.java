package com.example.be_safe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SendSms extends AppCompatActivity {


    protected void onCreate() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                sendSms();
            }else{
                requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
            }
        }
    }

    public void sendSms(){
        String phone = "+251941610973";
        String sms = "I am in trouble";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, sms, null, null);
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Message is not sent", Toast.LENGTH_SHORT).show();
        }

    }
}
