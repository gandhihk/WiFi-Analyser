package com.example.wifi;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wifi.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WifiDetails extends AppCompatActivity {

    TextView mac_text,strength_text,link_speed_text,frequency_text,netid_text;
    String bssid,mac,strength,link_speed,frequency,netid;
    WifiInfo wifiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_details);

        mac_text = findViewById(R.id.mac);
        strength_text = findViewById(R.id.strength);
        link_speed_text = findViewById(R.id.link_speed);
        frequency_text = findViewById(R.id.frequency);
        netid_text = findViewById(R.id.net_id);
        wifiInfo = MainActivity.wifiInfo;

        strength_text.setText("Strength : \t"+wifiInfo.getRssi()+" dbm");
        mac_text.setText("MAC : \t"+wifiInfo.getMacAddress());
        link_speed_text.setText("Link Speed : \t"+wifiInfo.getLinkSpeed()+" Mbps");
        frequency_text.setText("Frequency : \t"+wifiInfo.getFrequency()+"MHz");
        netid_text.setText("Net ID : \t"+wifiInfo.getNetworkId());

    }

    public void saveDetails(View view)
    {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);

        }


        String text = "WiFi Details :\n"+strength_text.getText().toString()+"\n"+mac_text.getText().toString()
                +"\n"+link_speed_text.getText().toString()+"\n"+frequency_text.getText().toString()
                +"\n"+netid_text.getText().toString();
//            FileOutputStream fos = null;
        //File filename = new File(Environment.getExternalStorageDirectory().toString());
//            Toast.makeText(getApplicationContext(), filename.toString(), Toast.LENGTH_LONG).show();
        /*ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("media", Context.MODE_PRIVATE);
        File path = new File(directory, "wifi_data.txt");
        Log.e("DEBUG", path.getAbsolutePath());
        Log.e("DEBUG", text);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(stream != null){
//                    System.out.println(text.toString().getBytes());
                stream.write(text.getBytes());
                Log.e("DEBUG", "File write complete");
                Toast.makeText(getApplicationContext(), "File saved successfully!", Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(stream != null){
                    stream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getBaseContext().openFileOutput("wifi_data.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(text);
            outputStreamWriter.close();

        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }*/

        File filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//            Toast.makeText(getApplicationContext(), filename.toString(), Toast.LENGTH_LONG).show();
        File path = new File(filename, "wifi_data.txt");
        Log.e("DEBUG", path.getAbsolutePath());
        Log.e("DEBUG", text);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(stream != null){
//                    System.out.println(text.toString().getBytes());
                stream.write(text.getBytes());
                Log.e("DEBUG", "File write complete");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(stream != null){
                    stream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
