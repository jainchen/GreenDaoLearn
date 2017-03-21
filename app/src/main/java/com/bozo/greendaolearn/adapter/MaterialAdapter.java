package com.bozo.greendaolearn.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bozo.greendaolearn.R;
import com.bozo.greendaolearn.entities.MaterialEntity;

import java.util.List;

/**
 * Author: ChenJing
 * Date: 2017-03-20 下午 5:47
 * Version: 1.0
 */

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder>{

    List<MaterialEntity> list;

    public MaterialAdapter(List<MaterialEntity> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MaterialEntity materialEntity = list.get(holder.getAdapterPosition());
        holder.tvName.setText(materialEntity.getName());
        holder.tvPrice.setText(String.format("%1$.2f 元", materialEntity.getPrice()));
        holder.tvNumber.setText(String.valueOf(materialEntity.getNumber()));
        holder.tvStore.setText(materialEntity.getStore());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvPrice, tvNumber, tvStore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
            tvStore = (TextView) itemView.findViewById(R.id.tv_store);
        }
    }
}
