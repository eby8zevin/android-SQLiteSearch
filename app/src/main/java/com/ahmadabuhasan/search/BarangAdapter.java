package com.ahmadabuhasan.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ahmad Abu Hasan on 30/11/2020
 */

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {

    public Context context;
    public List<HashMap<String, String>> barangData;

    public BarangAdapter(Context context1, List<HashMap<String, String>> barangData1) {
        this.context = context1;
        this.barangData = barangData1;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.barang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final DBAccess dbAccess = DBAccess.getInstance(this.context);
        dbAccess.open();

        dbAccess.open();


        holder.tvNamaBarang.setText(this.barangData.get(position).get(DBHelper.BARANG_NAMA));

        TextView textView = holder.tvBeli;

        textView.setText(this.context.getString(R.string.beli) + this.barangData.get(position).get(DBHelper.BARANG_BELI));

        TextView textView2 = holder.tvStock;
        textView2.setText(this.context.getString(R.string.stock) + this.barangData.get(position).get("Stock"));

        TextView textView3 = holder.tvHarga;
        textView3.setText(this.context.getString(R.string.harga) + this.barangData.get(position).get(DBHelper.BARANG_HARGA));

    }

    public int getItemCount() {
        return this.barangData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNamaBarang;
        TextView tvBeli;
        TextView tvStock;
        TextView tvHarga;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvNamaBarang = itemView.findViewById(R.id.tvNamaBarang);
            this.tvBeli = itemView.findViewById(R.id.tvBeli);
            this.tvStock = itemView.findViewById(R.id.tvStock);
            this.tvHarga = itemView.findViewById(R.id.tvHarga);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
