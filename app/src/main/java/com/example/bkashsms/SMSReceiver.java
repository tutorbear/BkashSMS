package com.example.bkashsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

public class SMSReceiver extends BroadcastReceiver {
    public static final String pdu_type = "pdus";

    @Override
    public void onReceive(final Context context, Intent intent) {
        // Get the SMS message.
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;

        String format = bundle.getString("format");
        // Retrieve the SMS message received.
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null) {
            // Check the Android version.
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];

            String fullSms = "";

            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                // If Android version M or newer:
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                // Build the message to show.
                String address = msgs[i].getOriginatingAddress();

                Log.d("Hello", address);
                fullSms += msgs[i].getMessageBody();
            }

                String[] split = fullSms.split("\\s+");
                Log.d("Hello", Arrays.toString(split));

            if (split[0].equals("Cash") && split[1].equals("In") && split[2].equals("Tk")) {
                //From agent | save object
                String trxId = "";
                String dateStr = "";
                String timeStr = ""; // 4 spaces after trxid
                String amount = split[3].replace(",", "");

                //Finding TrxID
                for (int j = 0; j < split.length; j++) {
                    if (split[j].equals("TrxID")) {
                        trxId = split[j + 1];
                        dateStr = split[j + 3];
                        timeStr = split[j + 4];
                        break;
                    }
                }

                //Date
                Calendar cal = Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("GMT"));
                String[] date = dateStr.split("/");

                Log.d("Hello", "Date: "+date[0]+" "+date[1]+" "+date[2]);

                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0]));
                cal.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
                cal.set(Calendar.YEAR, Integer.parseInt(date[2]));

                //Time
                String timeStrNew = timeStr.replace(".", "");

                String[] time = timeStrNew.split(":");
                cal.set(Calendar.HOUR, Integer.parseInt(time[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                Log.d("Hello", "Time: "+time[0]+" "+time[1]);

                // Saves the new object.
                ParseObject bKashObj = new ParseObject("Bkash");
                bKashObj.put("trxId", trxId);
                bKashObj.put("paidAmount", Double.parseDouble(amount));
                bKashObj.put("paidDate", cal.getTime());
                bKashObj.saveEventually(e -> {
                    if (e == null) {
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Hello",e.getMessage()+"");
                        Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if(split[0].equals("You") && split[1].equals("have") && split[2].equals("received") && split[3].equals("Tk")){
                //From Personal | save object
                String trxId = "";
                String dateStr = "";
                String timeStr = ""; // 4 spaces after trxid
                String amount = split[4].replace(",", "");

                //Finding TrxID
                for (int j = 0; j < split.length; j++) {
                    if (split[j].equals("TrxID")) {
                        trxId = split[j + 1];
                        dateStr = split[j + 3];
                        timeStr = split[j + 4];
                        break;
                    }
                }

                //Date
                Calendar cal = Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("GMT"));
                String[] date = dateStr.split("/");


                cal.set(Calendar.DATE, Integer.parseInt(date[0]));
                cal.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
                cal.set(Calendar.YEAR, Integer.parseInt(date[2]));
                Log.d("Hello", "Date: "+date[0]+" "+date[1]+" "+date[2]);

                //Time
                String timeStrNew = timeStr.replace(".", "");

                String[] time = timeStrNew.split(":");
                cal.set(Calendar.HOUR, Integer.parseInt(time[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                Log.d("Hello", "Time: "+time[0]+" "+time[1]);

                // Saves the new object.
                ParseObject bKashObj = new ParseObject("Bkash");
                bKashObj.put("trxId", trxId);
                bKashObj.put("paidAmount", Double.parseDouble(amount));
                bKashObj.put("paidDate", cal.getTime());
                bKashObj.saveEventually(e -> {
                    if (e == null) {
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Hello",e.getMessage()+"");
                        Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
