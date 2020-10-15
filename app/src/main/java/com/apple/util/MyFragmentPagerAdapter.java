package com.apple.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

//This class is used to adapt to the display page of the notes in the home page (as a fragment)

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> data;
    List<String> list;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> data, List<String> list) {
        super(fm);
        this.data = data;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void addFragment(Fragment fragment, String string) {
        data.add(fragment);
        list.add(string);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
