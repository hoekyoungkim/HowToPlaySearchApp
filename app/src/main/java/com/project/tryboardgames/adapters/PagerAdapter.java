package com.project.tryboardgames.adapters;

import android.support.v4.app.FragmentStatePagerAdapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.project.tryboardgames.fragments.WikipediaFragment;
import com.project.tryboardgames.fragments.YoutubeFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {


    int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs){
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }


    public int getNumberOfTabs() {
        return numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){

            case 0:
                WikipediaFragment tab1 = new WikipediaFragment();
                return tab1;

            case 1:
                YoutubeFragment tab2 = new YoutubeFragment();
                return tab2;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
