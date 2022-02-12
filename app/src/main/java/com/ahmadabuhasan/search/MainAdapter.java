package com.ahmadabuhasan.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ahmad Abu Hasan on 30/11/2020
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    public Context context;
    public List<HashMap<String, String>> dataSearch;

    public MainAdapter(Context context1, List<HashMap<String, String>> dataSearch1) {
        this.context = context1;
        this.dataSearch = dataSearch1;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final DBAccess dbAccess = DBAccess.getInstance(this.context);
        dbAccess.open();

        holder.tvNameProduct.setText(this.dataSearch.get(position).get(DBHelper.DATA_NAME));

        TextView textView = holder.tvBuy;
        textView.setText(String.format("%s = %s", this.context.getString(R.string.buy), this.dataSearch.get(position).get(DBHelper.DATA_BUY)));

        TextView textView2 = holder.tvStock;
        textView2.setText(String.format("%s = %s", this.context.getString(R.string.stock), this.dataSearch.get(position).get(DBHelper.DATA_STOCK)));

        TextView textView3 = holder.tvPrice;
        textView3.setText(String.format("%s = %s", this.context.getString(R.string.price), this.dataSearch.get(position).get(DBHelper.DATA_PRICE)));
    }

    public int getItemCount() {
        return this.dataSearch.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNameProduct;
        TextView tvBuy;
        TextView tvStock;
        TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            this.tvBuy = itemView.findViewById(R.id.tvBuy);
            this.tvStock = itemView.findViewById(R.id.tvStock);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show();
        }
    }
}