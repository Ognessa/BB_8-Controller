package com.example.bb_820.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bb_820.BluetoothFragment;
import com.example.bb_820.ControlFragment;
import com.example.bb_820.MusicFragment;
import com.example.bb_820.R;
import com.example.bb_820.SetupFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ControlFragment tab1 = new ControlFragment();
                return tab1;
            case 1:
                MusicFragment tab2 = new MusicFragment();
                return tab2;
            case 2:
                BluetoothFragment tab3 = new BluetoothFragment();
                return tab3;
            case 3:
                SetupFragment tab4 = new SetupFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}