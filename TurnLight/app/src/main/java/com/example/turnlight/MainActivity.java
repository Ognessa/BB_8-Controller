package com.example.turnlight;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final String DEVICE_ADDRESS = "00:21:13:00:22:9A"; //MAC Address of Bluetooth Module
    BluetoothThread bt;
    Button turnOn, turnOff, connect, discon;
    String message;
    Handler writeHandler;

    @SuppressLint({"ClickableViewAccessibility", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnOn = findViewById(R.id.turnOnBtn);
        turnOff = findViewById(R.id.turnOffBtn);
        connect = findViewById(R.id.connectBtn);
        discon = findViewById(R.id.disconnectBtn);

        //Bluetooth create
        bt = new BluetoothThread(DEVICE_ADDRESS, new Handler() {
            @Override
            public void handleMessage(Message message) {
                String msg = (String) message.obj;

                // Do something with the message
                if (msg.equals("CONNECTED")) {
                    turnOn.setEnabled(true);
                    turnOff.setEnabled(true);
                    connect.setEnabled(false);
                    discon.setEnabled(true);
                } else if (msg.equals("DISCONNECTED")) {
                    turnOn.setEnabled(false);
                    turnOff.setEnabled(false);
                    connect.setEnabled(true);
                    discon.setEnabled(false);
                } else if (msg.equals("CONNECTION FAILED")) {
                    bt = null;
                } else {
                    bt.write(msg);}
            }
        });

        writeHandler = bt.getWriteHandler();

        turnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "1";

                Message msg = Message.obtain();
                msg.obj = message;
                writeHandler.sendMessage(msg);
            }
        });

        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "2";

                Message msg = Message.obtain();
                msg.obj = message;
                writeHandler.sendMessage(msg);

            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.start();
            }
        });


        discon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Disconnect button pressed.");

                if(bt != null) {
                    bt.interrupt();
                    bt = null;
                }
            }
        });


    }
}