package com.tse.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tse.app.Activity.HomeDetailActivity;
import com.tse.app.Activity.TraderDetailActivity;
import com.tse.app.Model.Order_Fatch;
import com.tse.app.R;

import java.util.List;

public class TraderHistoryAdeptor extends RecyclerView.Adapter<TraderHistoryAdeptor.MyViewHolder> {

    private Context context;
    private List<Order_Fatch> list;

    public TraderHistoryAdeptor(Context context, List<Order_Fatch> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trader_item_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Order_Fatch order_fatch = list.get(position);
        holder.TxtOrderCode.setText(order_fatch.getOrdercode());
        holder.TxtOrderProduct.setText(order_fatch.getProduct());
        holder.TxtOrderCategory.setText(order_fatch.getCategory());
        holder.TxtOrderSubCategory.setText(order_fatch.getSubcategory());
        holder.TxtOrderStatus.setText(order_fatch.getStatus());

        if (holder.TxtOrderStatus.getText().toString().equals("Pending")){
            holder.TxtOrderStatus.setBackgroundColor(Color.RED);
        }


        holder.btnViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), TraderDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("ordercode",(String.valueOf(order_fatch.getOrdercode())));
                intent.putExtra("product",(String.valueOf(order_fatch.getProduct())));
                intent.putExtra("category",(String.valueOf(order_fatch.getCategory())));
                intent.putExtra("subcategory",(String.valueOf(order_fatch.getSubcategory())));
                intent.putExtra("area1",(String.valueOf(order_fatch.getArea1())));
                intent.putExtra("Dimension",(String.valueOf(order_fatch.getDimension())));
                intent.putExtra("Gsm",(String.valueOf(order_fatch.getGsm())));
                intent.putExtra("Colour",(String.valueOf(order_fatch.getColour())));
                intent.putExtra("Remark",(String.valueOf(order_fatch.getRemark())));
                intent.putExtra("Qualitiy",(String.valueOf(order_fatch.getQualitiy())));
                intent.putExtra("Paymentterms",(String.valueOf(order_fatch.getPaymentterms())));
                intent.putExtra("rate",(String.valueOf(order_fatch.getRate())));
                //intent.putExtra("status",(String.valueOf(order_fatch.getStatus())));

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
        public TextView TxtOrderCode, TxtOrderProduct, TxtOrderCategory, TxtOrderSubCategory,TxtOrderStatus;

        public MyViewHolder(View view) {
            super(view);

            btnViewDetail = (Button) view.findViewById(R.id.btnViewDetail);
            TxtOrderCode = (TextView) view.findViewById(R.id.TxtOrderCode);
            TxtOrderStatus = (TextView) view.findViewById(R.id.TxtOrderStatus);
            TxtOrderProduct = (TextView) view.findViewById(R.id.TxtOrderProduct);
            TxtOrderCategory = (TextView) view.findViewById(R.id.TxtOrderCategory);
            TxtOrderSubCategory = (TextView) view.findViewById(R.id.TxtOrderSubCategory);

        }
    }
}