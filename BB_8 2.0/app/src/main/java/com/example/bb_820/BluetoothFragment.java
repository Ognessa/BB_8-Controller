package com.example.bb_820;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


public class BluetoothFragment extends Fragment {

    private String DEVICE_ADDRESS = "98:D3:51:F5:E3:F8"; //MAC Address of Bluetooth Module
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    private ListView lstvw;
    TextView text;
    Button bluetooth_connect_btn, btn;
    private ArrayAdapter aAdapter;
    private BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

    public String getDEVICE_ADDRESS() {
        return DEVICE_ADDRESS;
    }

    public void setDEVICE_ADDRESS(String DEVICE_ADDRESS) {
        this.DEVICE_ADDRESS = DEVICE_ADDRESS;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3bluetooth, container, false);
//----------------------------------------my code------------------------------------------------------------------
        btn = rootView.findViewById(R.id.btn_getBluetooth);
        lstvw = rootView.findViewById(R.id.deviceList);
        text = rootView.findViewById(R.id.textView);
        bluetooth_connect_btn = rootView.findViewById(R.id.connect_button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter==null){
                    Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else{
                    Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
                    final ArrayList<String> list = new ArrayList();
                    if(pairedDevices.size()>0){
                        for(BluetoothDevice device: pairedDevices){
                            String devicename = device.getName();
                            String macAddress = device.getAddress();
                            list.add(devicename + "\n" +"|"+ macAddress);
                        }
                        aAdapter = new ArrayAdapter(Objects.requireNonNull(getActivity()).getApplicationContext(), android.R.layout.simple_list_item_1, list);
                        lstvw.setAdapter(aAdapter);

                        lstvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String selectedItem = list.get(position);
                                int num = selectedItem.indexOf("|")+1;
                                String parts = selectedItem.substring(num, num+17);
                                setDEVICE_ADDRESS(parts);
                                text.setText(getDEVICE_ADDRESS());
                              }
                          }
                        );
                    }

                }
            }
        });

        //Button that connects the device to the bluetooth module when pressed
        bluetooth_connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(BTinit())
                {
                    BTconnect();
                }

            }
        });

        //----------------------------------------my code------------------------------------------------------------------
        return rootView;
    }

    //Initializes bluetooth module
    public boolean BTinit()
    {
        boolean found = false;

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null) //Checks if the device supports bluetooth
        {
            Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        }

        assert bluetoothAdapter != null;
        if(!bluetoothAdapter.isEnabled()) //Checks if bluetooth is enabled. If not, the program will ask permission from the user to enable it
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        if(bondedDevices.isEmpty()) //Checks for paired bluetooth devices
        {
            Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(BluetoothDevice iterator : bondedDevices)
            {
                if(iterator.getAddress().equals(getDEVICE_ADDRESS()))
                {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public boolean BTconnect()
    {
        boolean connected = true;

        try
        {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); //Creates a socket to handle the outgoing connection
            socket.connect();

            Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                    "Connection to bluetooth device successful", Toast.LENGTH_LONG).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            connected = false;
        }

        if(connected)
        {
            try
            {
                outputStream = socket.getOutputStream(); //gets the output stream of the socket
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        return connected;
    }
    @Override
    public void onStart()
    {
        super.onStart();
    }
}
