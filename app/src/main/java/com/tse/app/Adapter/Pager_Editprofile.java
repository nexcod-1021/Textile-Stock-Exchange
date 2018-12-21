package com.tse.app.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tse.app.Fragment.BuyerFragment;
import com.tse.app.Fragment.EditProfileBuyerFragment;
import com.tse.app.Fragment.EditProfileSellerFragment;
import com.tse.app.Fragment.SellerFragment;
import com.tse.app.Fragment.ServicesFragment;

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
                EditProfileBuyerFragment tab1 = new EditProfileBuyerFragment();
                return tab1;
            case 1:
                EditProfileSellerFragment tab2 = new EditProfileSellerFragment();
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
