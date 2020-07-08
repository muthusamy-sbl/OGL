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
import com.manappuram.ogl.databinding.ItemCalcBinding;
import com.manappuram.ogl.databinding.ItemPledgeDetailsBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.CalculatorResponse;

import java.util.ArrayList;

class CalculatorAdapter extends RecyclerView.Adapter<CalculatorAdapter.ViewHolder> {

    Context context;
    ArrayList<CalculatorResponse> calcList;

    public CalculatorAdapter(Context context, ArrayList<CalculatorResponse> calcList) {
        this.context = context;
        this.calcList = calcList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCalcBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_calc, parent, false);

        return new CalculatorAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.tvDateRange.setText(calcList.get(position).getMinday() + " - " + calcList.get(position).getMaxday());
        holder.binding.tvInterest.setText(calcList.get(position).getIntrt());
        holder.binding.tvAmount.setText(calcList.get(position).getIntmt());

    }

    @Override
    public int getItemCount() {
        if (calcList != null)
            return calcList.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemCalcBinding binding;

        public ViewHolder(@NonNull ItemCalcBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
