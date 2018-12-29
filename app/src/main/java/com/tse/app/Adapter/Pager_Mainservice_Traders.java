package com.tse.app.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tse.app.Fragment.BuyerFragment;
import com.tse.app.Fragment.SellerFragment;
import com.tse.app.Fragment.ServicesFragment;

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
        bundle.putInt("flag",1);
        switch (position) {
            case 0:
                BuyerFragment tab1 = new BuyerFragment();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                SellerFragment tab2 = new SellerFragment();
                tab2.setArguments(bundle);
                return tab2;
            case 2:
                ServicesFragment tab3 = new ServicesFragment();
                tab3.setArguments(bundle);
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
