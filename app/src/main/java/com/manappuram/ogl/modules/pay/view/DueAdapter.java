package com.manappuram.ogl.modules.pay.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.ItemDueAccountsBinding;
import com.manappuram.ogl.modules.dashboardoptions.ItemClicked;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;

import java.util.ArrayList;

class DueAdapter extends RecyclerView.Adapter<DueAdapter.ViewHolder> {

    Context context;
    ArrayList<AccDetailsResponse> accountList;
    ItemClicked itemClicked;

    public DueAdapter(Context context, ArrayList<AccDetailsResponse> accountList, ItemClicked itemClicked) {
        this.context = context;
        this.accountList = accountList;
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDueAccountsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_due_accounts, parent, false);

        return new DueAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.accountTitle.setText(accountList.get(position).getPledgeNo());
        holder.binding.accountAmt.setText("₹ " + accountList.get(position).getPledgeAmt());

        holder.itemView.setOnClickListener(v -> {
            itemClicked.itemClicked("", accountList.get(position));

        });

    }

    @Override
    public int getItemCount() {
        if (accountList != null)
            return accountList.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemDueAccountsBinding binding;

        public ViewHolder(@NonNull ItemDueAccountsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
