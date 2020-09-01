package com.example.bb_8;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new ControlFragment();
            case 1:
                return new MusicFragment();
            case 2:
                return new BluetoothFragment();
            case 3:
                return new SetupFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() { return numOfTabs; }

}
