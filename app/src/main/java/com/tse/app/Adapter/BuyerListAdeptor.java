package com.tse.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tse.app.Activity.BuyerDetailActivity;
import com.tse.app.R;

import java.util.List;

public class BuyerListAdeptor extends RecyclerView.Adapter<BuyerListAdeptor.MyViewHolder> {

    private Context context;
    private List<String> list;

    public BuyerListAdeptor(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buyer_item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       /* Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());*/
       holder.btnViewDetail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context.getApplicationContext(), BuyerDetailActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

               context.getApplicationContext().startActivity(intent);


           }
       });
    }

   @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button btnViewDetail;

        public MyViewHolder(View view) {
            super(view);
            btnViewDetail = (Button) view.findViewById(R.id.btnViewDetail);

        }
    }
}