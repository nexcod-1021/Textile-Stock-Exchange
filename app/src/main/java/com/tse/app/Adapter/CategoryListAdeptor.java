package com.tse.app.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tse.app.R;

import java.util.List;

public class CategoryListAdeptor extends RecyclerView.Adapter<CategoryListAdeptor.MyViewHolder> {

    private Context context;
    private List<String> list;

    public CategoryListAdeptor(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorylist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TxtProfileSelect_Category,TxtProfileSub_Category,TxtProfileQty;

        public MyViewHolder(View view) {

            super(view);
            TxtProfileSelect_Category = (TextView) view.findViewById(R.id.TxtProfileSelect_Category);
            TxtProfileSub_Category = (TextView) view.findViewById(R.id.TxtProfileSub_Category);
            TxtProfileQty = (TextView) view.findViewById(R.id.TxtProfileQty);

        }
    }
}