package com.example.bkashsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String requiredPermission = Manifest.permission.RECEIVE_SMS;
//        int checkVal = this.checkCallingOrSelfPermission(requiredPermission);
//        if (checkVal == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
//        } else {
//            ActivityCompat.requestPermissions(MainActivity.this,
//                    new String[]{Manifest.permission.RECEIVE_SMS},
//                    1);
//        }

        ParseObject obj = new ParseObject("Test");

        final String time = "12:00";

        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            final Date dateObj = sdf.parse(time);
            Log.d("hello",dateObj+"");
            System.out.println(dateObj);
            System.out.println();
            Log.d("hello",new SimpleDateFormat("hh:mm aa").format(dateObj)+"");

        } catch (final java.text.ParseException e) {
            e.printStackTrace();
        }
//        obj.put("date",cal.getTime());
//
//        obj.saveInBackground(e -> {
//            if (e==null){
//                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}