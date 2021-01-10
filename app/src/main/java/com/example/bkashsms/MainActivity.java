package com.example.bkashsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Calendar;
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

//        ParseObject obj = new ParseObject("Test");
////
////        Calendar cal = Calendar.getInstance();
////        cal.set(Calendar.HOUR_OF_DAY,21);
////        cal.set(Calendar.MINUTE,21);
////        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
////        obj.put("date",cal.getTime());
////
////        obj.saveInBackground(e -> {
////            if (e==null){
////                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
////            }else{
////                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////        });
    }
}