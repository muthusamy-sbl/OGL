package com.manappuram.ogl.modules.dashboardoptions.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.ItemAccountBinding;
import com.manappuram.ogl.databinding.ItemPledgeDetailsBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.AccDetailModel;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;

import java.util.ArrayList;

class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    Context context;
    ArrayList<AccDetailModel> accountList;

    public AccountAdapter(Context context, ArrayList<AccDetailModel> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAccountBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_account, parent, false);

        return new AccountAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.plusIcon.setOnClickListener(v -> {
            holder.binding.itemDetails.setVisibility(holder.binding.itemDetails.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        });

        holder.binding.accountTitle.setText(accountList.get(position).getResponse().getPledgeNo());
        holder.binding.accountAmt.setText(accountList.get(position).getResponse().getPledgeAmt());
        if (accountList.get(position).getLoanResponse() != null) {
            holder.binding.accountNum.setText(accountList.get(position).getLoanResponse().getPledgeNo());
            holder.binding.inventoryDate.setText(accountList.get(position).getLoanResponse().getInvDate());
            holder.binding.disbursementDate.setText(accountList.get(position).getLoanResponse().getClosedate());
            holder.binding.loanAmount.setText(accountList.get(position).getLoanResponse().getPledgeAmt());
            holder.binding.maturityDate.setText(accountList.get(position).getLoanResponse().getMaturityDate());
            holder.binding.schemeName.setText(accountList.get(position).getLoanResponse().getSchemeName());
            holder.binding.tvPrincipal.setText(accountList.get(position).getLoanResponse().getBalanceAmt());
            holder.binding.tvInterest.setText(accountList.get(position).getLoanResponse().getLastTransactionDate());
            if (accountList.get(position).getLoanResponse().getStatus().equals("111")) {
                holder.binding.tvStatus.setText("Live");
            }

        }

    }

    @Override
    public int getItemCount() {
        if (accountList != null)
            return accountList.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemAccountBinding binding;

        public ViewHolder(@NonNull ItemAccountBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
