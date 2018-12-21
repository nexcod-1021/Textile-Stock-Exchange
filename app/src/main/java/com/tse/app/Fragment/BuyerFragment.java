package com.tse.app.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tse.app.Adapter.BuyerListAdeptor;
import com.tse.app.R;

import java.util.ArrayList;


public class BuyerFragment extends android.support.v4.app.Fragment {

    RecyclerView RecyclerViewDeatailItem;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<String> list;
    private BuyerListAdeptor buyerListAdeptor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buyer, container, false);
        list = new ArrayList<String>();
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewDeatailItem = view.findViewById(R.id.RecyclerViewDeatailItem);
        buyerListAdeptor = new BuyerListAdeptor(getContext(), list);
        RecyclerViewDeatailItem.setHasFixedSize(true);
        RecyclerViewDeatailItem.setLayoutManager(linearLayoutManager);

        RecyclerViewDeatailItem.setAdapter(buyerListAdeptor);


        dataview();
        return view;
    }

    private void dataview() {

        list.add("code");
        list.add("code");
        list.add("code");
        list.add("code");
        list.add("code");
        list.add("code");
        list.add("code");
        list.add("code");
        buyerListAdeptor.notifyDataSetChanged();
    }

}
