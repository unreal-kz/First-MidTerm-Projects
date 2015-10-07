package com.example.unreal_kz.midtermone.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.unreal_kz.midtermone.fragment.TabFragmentViewer_One;
import com.example.unreal_kz.midtermone.fragment.TabFragmentViewer_Three;
import com.example.unreal_kz.midtermone.fragment.TabFragmentViewer_Two;

/**
 * Created by Unreal_KZ on 07.10.2015.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{"Tab1", "Tab2", "Tab3"};
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TabFragmentViewer_One.getFragment();
            case 1:
                return TabFragmentViewer_Two.getFragment();
            case 2:
                return TabFragmentViewer_Three.getFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
