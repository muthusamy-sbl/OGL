package com.manappuram.ogl.modules.navigation.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.ItemOglHistoryBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.OglHistoryResponse;
import com.manappuram.ogl.modules.navigation.model.OglHistoryresponse;

import java.util.ArrayList;

public class OglHistoryAdapter extends RecyclerView.Adapter<OglHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<OglHistoryresponse> historyList;

    public OglHistoryAdapter(Context context, ArrayList<OglHistoryresponse> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOglHistoryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_ogl_history, parent, false);

        return new OglHistoryAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.id.setText(historyList.get(position).getPledgeno());
        holder.binding.amount.setText("Rs."+historyList.get(position).getAmount());
        holder.binding.date.setText(historyList.get(position).getTxndate()+"/ ID"+historyList.get(position).getTxnid());
    }

    @Override
    public int getItemCount() {
        if (historyList != null)
            return historyList.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemOglHistoryBinding binding;

        public ViewHolder(@NonNull ItemOglHistoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
