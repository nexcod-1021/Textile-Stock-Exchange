package com.tse.app.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tse.app.Fragment.EditProfileProfileFragment;
import com.tse.app.Fragment.EditProfileCategoryFragment;

/**
 * Created by Dave on 03/12/2018.
 */

public class Pager_Editprofile extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;
    Context context;


    public Pager_Editprofile(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;

    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                EditProfileProfileFragment tab1 = new EditProfileProfileFragment();
                return tab1;
            case 1:
                EditProfileCategoryFragment tab2 = new EditProfileCategoryFragment();
                return tab2;
            default:
                return null;
        }

    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
