package com.example.bb_8;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


public class ControlFragment extends Fragment{
    //----------------------------------------my code------------------------------------------------------------------

   BluetoothFragment blFrag = new BluetoothFragment();

    ImageButton forward_btn, left_btn, right_btn, reverse_btn;

    DefaultVariables defVar = new DefaultVariables();
    static String command; //string variable that will store value to be transmitted to the bluetooth module
//----------------------------------------my code------------------------------------------------------------------

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_control, container, false);
        //----------------------------------------my code------------------------------------------------------------------

        //declaration of button variables
        forward_btn =  rootView.findViewById(R.id.up_btn);
        left_btn = rootView.findViewById(R.id.left_btn);
        right_btn =  rootView.findViewById(R.id.right_btn);
        reverse_btn = rootView.findViewById(R.id.back_btn);

        //OnTouchListener code for the forward button (button long press)
        forward_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN is when you hold a button down
                {
                    command = defVar.getDefaultForward();

                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP is when you release a button
                {
                    command = defVar.getDefaultStopY();
                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes());
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }

                }

                return false;
            }

        });

        //OnTouchListener code for the reverse button (button long press)
        reverse_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = defVar.getDefaultBack();

                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    command = defVar.getDefaultStopY();
                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes());
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                return false;
            }
        });

        //OnTouchListener code for the forward left button (button long press)
        left_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = defVar.getDefaultLeft();

                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    command = defVar.getDefaultStopX();
                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes());
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                return false;
            }
        });

        //OnTouchListener code for the forward right button (button long press)
        right_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = defVar.getDefaultRight();

                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    command = defVar.getDefaultStopX();
                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes());
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                return false;
            }
        });

        //----------------------------------------my code------------------------------------------------------------------
        return rootView;
    }

}

