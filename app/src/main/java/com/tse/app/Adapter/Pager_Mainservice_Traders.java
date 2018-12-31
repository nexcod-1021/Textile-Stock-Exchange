package com.tse.app.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tse.app.Fragment.BuyerFragment;
import com.tse.app.Fragment.SellerFragment;
import com.tse.app.Fragment.ServicesFragment;
import com.tse.app.Fragment.TraderBuyerFragment;
import com.tse.app.Fragment.TraderServicesFragment;

/**
 * Created by Dave on 03/12/2018.
 */

public class Pager_Mainservice_Traders extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;
    Context context;


    public Pager_Mainservice_Traders(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;

    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                TraderBuyerFragment tab1 = new TraderBuyerFragment();

                return tab1;
            case 1:
                TraderServicesFragment tab2 = new TraderServicesFragment();

                return tab2;
            case 2:
                TraderServicesFragment tab3 = new TraderServicesFragment();

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
