package com.example.bb_8;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;

public class SetupFragment extends Fragment {
    //----------------------------------------my code------------------------------------------------------------------

  /*  DefaultVariables defVar = new DefaultVariables();

    EditText rightBtnEdit, leftBtnEdit, forwardBtnEdit, backBtnEdit, stopX, stopY;
    Button btnSetup;*/

    //----------------------------------------my code------------------------------------------------------------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setup, container, false);
        //----------------------------------------my code------------------------------------------------------------------

       /* rightBtnEdit = getActivity().findViewById(R.id.rightBtnEdit);
        leftBtnEdit = getActivity().findViewById(R.id.leftBtnEdit);
        forwardBtnEdit = getActivity().findViewById(R.id.forwardBtnEdit);
        backBtnEdit = getActivity().findViewById(R.id.backBtnEdit);
        btnSetup = getActivity().findViewById(R.id.btnGetSet);
        stopX  = getActivity().findViewById(R.id.stopBtnEditFB);;
        stopY  = getActivity().findViewById(R.id.stopBtnEditLR);;

        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 defVar.setDefaultRight(rightBtnEdit.getText().toString());
                 defVar.setDefaultLeft(leftBtnEdit.getText().toString());
                 defVar.setDefaultForward(forwardBtnEdit.getText().toString());
                 defVar.setDefaultBack(backBtnEdit.getText().toString());
                 defVar.setDefaultStopX(stopX.getText().toString());
                 defVar.setDefaultStopY(stopY.getText().toString());

                rightBtnEdit.setText(" ");
                leftBtnEdit.setText(" ");
                forwardBtnEdit.setText(" ");
                backBtnEdit.setText(" ");
                stopX.setText(" ");
                stopY.setText(" ");
            }
        });*/



        //----------------------------------------my code------------------------------------------------------------------
        return rootView;
    }
}
