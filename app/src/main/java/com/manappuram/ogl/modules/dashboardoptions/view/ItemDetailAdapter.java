package com.manappuram.ogl.modules.dashboardoptions.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.ItemPledgeBinding;
import com.manappuram.ogl.databinding.ItemPledgeDetailsBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.ItemResponse;

import java.util.ArrayList;

class ItemDetailAdapter extends RecyclerView.Adapter<ItemDetailAdapter.ViewHolder> {

    Context context;
    public ArrayList<ItemResponse> itemList;

    public ItemDetailAdapter(Context context, ArrayList<ItemResponse> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPledgeDetailsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_pledge_details, parent, false);

        return new ItemDetailAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.plusIcon.setOnClickListener(v -> holder.binding.itemDetails.setVisibility(holder.binding.itemDetails.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));


        holder.binding.itemTitle.setText(itemList.get(position).getItmName());
        holder.binding.itemCount.setText(itemList.get(position).getItmCount());
        holder.binding.itemWeight.setText(itemList.get(position).getNetWt());
        holder.binding.desuctionName.setText(itemList.get(position).getDdnm());
        holder.binding.accWeight.setText(itemList.get(position).getNewActWt());
        holder.binding.stoneWeight.setText(itemList.get(position).getStoneWt());
        holder.binding.netWeight.setText(itemList.get(position).getNetWt());
        holder.binding.remarks.setText(itemList.get(position).getRemark());

    }

    @Override
    public int getItemCount() {
        if (itemList != null)
            return itemList.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemPledgeDetailsBinding binding;

        public ViewHolder(@NonNull ItemPledgeDetailsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
