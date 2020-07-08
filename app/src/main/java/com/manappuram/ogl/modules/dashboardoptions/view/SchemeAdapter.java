package com.manappuram.ogl.modules.dashboardoptions.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.ItemPledgeBinding;
import com.manappuram.ogl.databinding.ItemSchemeBinding;
import com.manappuram.ogl.modules.dashboardoptions.ItemClicked;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeResponse;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.Utility;

import java.util.ArrayList;

class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.ViewHolder> {

    Context context;
    ArrayList<SchemeResponse> schemeList;
    ItemClicked itemClicked;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SchemeAdapter(Context context, ArrayList<SchemeResponse> schemeList, ItemClicked itemClicked) {
        this.context = context;
        this.schemeList = schemeList;
        this.itemClicked = itemClicked;
        sharedPreferences = context.getSharedPreferences("ogl-app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSchemeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_scheme, parent, false);

        return new SchemeAdapter.ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.schemeName.setText(schemeList.get(position).getSchmName().trim());
        holder.binding.schemePeriod.setText(schemeList.get(position).getDuration().trim());
        holder.binding.schemeInterest.setText(schemeList.get(position).getInterest().trim());
        holder.binding.schemeEligibleAmt.setText( Constants.Amount_symbol+  schemeList.get(position).getLoanValue().trim());
//        schemeList.get(position).getLendRate().trim()

        String channel = (sharedPreferences.getString(Constants.SAVED_settlement_AMOUNT, ""));
        holder.binding.schemeTotal.setText(Constants.Amount_symbol +channel);
        int NetAmt= Math.round(Float.parseFloat(schemeList.get(position).getLoanValue().trim())-Float.parseFloat(channel));

        holder.binding.schemeNetAmt.setText(Constants.Amount_symbol + String.valueOf(NetAmt));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NetAmt>0) {
                    itemClicked.itemClicked("1", schemeList.get(position));
                }else{
                    Utility.showCustomErrorDialog(context, "Alert", "Your maximum eligibible loan amount is not sufficient to setting existing loan amount...", false, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Utility.closeErrorDialog();
                        }
                    });

                }
            }
        });

        holder.binding.itemDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClicked.itemClicked("2", schemeList.get(position));
            }
        });

    }
    @Override
    public int getItemCount() {
        if (schemeList != null)
            return schemeList.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemSchemeBinding binding;

        public ViewHolder(@NonNull ItemSchemeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
