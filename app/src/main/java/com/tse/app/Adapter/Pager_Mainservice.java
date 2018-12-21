package com.tse.app.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tse.app.Fragment.BuyerFragment;
import com.tse.app.Fragment.SellerFragment;
import com.tse.app.Fragment.ServicesFragment;

/**
 * Created by Dave on 03/12/2018.
 */

public class Pager_Mainservice extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;
    Context context;


    public Pager_Mainservice(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;

    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                BuyerFragment tab1 = new BuyerFragment();


                return tab1;
            case 1:
                SellerFragment tab2 = new SellerFragment();


                return tab2;
            case 2:
                ServicesFragment tab3 = new ServicesFragment();

                return tab3;
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
