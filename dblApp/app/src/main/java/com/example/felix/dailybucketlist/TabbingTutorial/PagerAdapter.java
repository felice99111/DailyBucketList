package com.example.felix.dailybucketlist.TabbingTutorial;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.R;

// Adpater gibt die Fragements des Tutorials mit Bild und Text zurück.
public class PagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public PagerAdapter (FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        int pageNumber = position + 1;
        Fragment pageFragment = new FragmentPage();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber", pageNumber);

        // Holt Tutorial Bild und Text basierend auf Seitenzahl.
        switch(pageNumber){
            case 1:
                bundle.putString("imagePath", Config.TUTORIAL_PICTURE_01);
                bundle.putString("tutText", context.getResources().getString(R.string.tutorial_text_1));
                break;
            case 2:
                bundle.putString("imagePath", Config.TUTORIAL_PICTURE_02);
                bundle.putString("tutText", context.getResources().getString(R.string.tutorial_text_2));
                break;
            case 3:
                bundle.putString("imagePath", Config.TUTORIAL_PICTURE_03);
                bundle.putString("tutText", context.getResources().getString(R.string.tutorial_text_3));
                break;
            case 4:
                bundle.putString("imagePath", Config.TUTORIAL_PICTURE_04);
                bundle.putString("tutText", context.getResources().getString(R.string.tutorial_text_4));
                break;
        }

        pageFragment.setArguments(bundle);

        return pageFragment;

    }

    @Override
    public int getCount() {
        return 4;
    }
}
