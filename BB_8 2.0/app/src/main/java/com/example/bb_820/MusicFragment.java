package com.example.bb_820;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class MusicFragment extends Fragment {

    BluetoothFragment blFrag = new BluetoothFragment();
    DefaultVariables defVar = new DefaultVariables();

    ImageButton lastSong, nextSong, playPause;
    TextView songName;

    static String command; //string variable that will store value to be transmitted to the bluetooth module
    boolean flag = true;

    int num = 1;
    String songs = "Song №" + num;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2music, container, false);

        lastSong = rootView.findViewById(R.id.last_song);
        nextSong = rootView.findViewById(R.id.next_song);
        playPause = rootView.findViewById(R.id.play_pause);
        songName = rootView.findViewById(R.id.song_name);

        songName.setText(songs);

        lastSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                command = defVar.getDefaultLastSong();

                try
                {
                    blFrag.getOutputStream().write(command.getBytes()); //transmits the value of command to the bluetooth module
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        nextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                command = defVar.getDefaultNextSong();

                try
                {
                    blFrag.getOutputStream().write(command.getBytes()); //transmits the value of command to the bluetooth module
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(true){
                    playPause.setImageResource(R.drawable.ic_pause);
                    command = defVar.getDefaultPlay();

                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else{
                    playPause.setImageResource(R.drawable.ic_play);
                    flag = !flag;
                    command = defVar.getDefaultPause();

                    try
                    {
                        blFrag.getOutputStream().write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });


        return rootView;
    }
}