package com.example.wifi;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wifi.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WifiManager wifiManager;
    TextView wifi_status;
    TextView strength_value_text;
    static WifiInfo wifiInfo;
    private int dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifi_status = findViewById(R.id.wifi_status);
        strength_value_text = findViewById(R.id.strength_value);

        if(!wifiManager.isWifiEnabled()) {
            wifi_status.setText("WiFi Status : \tOFF");
            //AlertDialog alertDialog = new AlertDialog()
            Toast.makeText(this, "Wifi disabled", Toast.LENGTH_LONG).show();
        }
        else
            wifi_status.setText("WiFi Status : \tON");
    }

    public void getStrength(View view)
    {
        if(!wifiManager.isWifiEnabled()) {
            wifi_status.setText("WiFi Status : \tOFF");
            //AlertDialog alertDialog = new AlertDialog()
            Toast.makeText(this, "Wifi is disabled. Please enable it.", Toast.LENGTH_LONG).show();
        }
        else{
            wifi_status.setText("WiFi Status : \tON");

            // Level of current connection
            int rssi = wifiManager.getConnectionInfo().getRssi();
            int level = WifiManager.calculateSignalLevel(rssi, 5);
            //strength_text.setText("Level is " + level + " out of 5");

            //Actual signal value in dbm
            wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo != null && !TextUtils.isEmpty(wifiInfo.getSSID())) {
                dbm = wifiInfo.getRssi();
                //strength_value_text.setText(wifiInfo.toString());
                strength_value_text.setText("Strength : \t"+String.valueOf(dbm)+" dbm");
            }

        }
    }

    public void getDetails(View view) {
        if(!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "Wifi is disabled. Please enable it.", Toast.LENGTH_LONG).show();
        }
        else{
            Intent i = new Intent(this, WifiDetails.class);
            //i.putExtra("details",wifiInfo);
            startActivity(i);
        }

    }
}
