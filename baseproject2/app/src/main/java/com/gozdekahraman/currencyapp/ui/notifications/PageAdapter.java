package com.gozdekahraman.currencyapp.ui.notifications;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numTabs;

    public PageAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AlarmFragment af = new AlarmFragment();
                return af;
            case 1:
                NewsFragment nf = new NewsFragment();
                return nf;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
