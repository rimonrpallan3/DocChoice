package com.glen.filetest.activity.Landing.adapter;

import android.app.Activity;
import android.app.Notification;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.glen.filetest.activity.Landing.fragment.DOCFragment.DOCFragment;
import com.glen.filetest.activity.Landing.fragment.PDFFragment.PDFFragment;


/**
 * Created by User on 14-Nov-18.
 */

public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    DOCFragment tab1;
    PDFFragment tab2;

    Activity activity;

    public TabViewPagerAdapter(Activity activity, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.activity = activity;
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new DOCFragment();
                return tab1;
            case 1:
                tab2 = new PDFFragment();
                //((MainActivity) activity).setLandingFragment(tab2);
                return tab2;

            default:
                tab1 = new DOCFragment();
                return tab1;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }



}
