package com.example.felix.dailybucketlist.TabbingTutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.felix.dailybucketlist.Config;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter (FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int pageNumber = position + 1;
        Fragment pageFragment = new FragmentPage();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber", pageNumber);

        switch(pageNumber){
            case 1:
                bundle.putString("imagePath", Config.TUTORIAL_PICTURE_01);
                bundle.putString("tutText", Config.TUTORIAL_TEXT_01);
                break;
            case 2:
                bundle.putString("imagePath", Config.TUTORIAL_PICTURE_02);
                bundle.putString("tutText", Config.TUTORIAL_TEXT_02);
                break;
            case 3:
                bundle.putString("imagePath", Config.TUTORIAL_PICTURE_03);
                bundle.putString("tutText", Config.TUTORIAL_TEXT_03);
                break;
        }

        pageFragment.setArguments(bundle);

        return pageFragment;

    }

    @Override
    public int getCount() {
        return 3;
    }
}
