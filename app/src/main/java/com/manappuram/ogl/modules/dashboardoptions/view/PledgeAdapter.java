package com.manappuram.ogl.modules.dashboardoptions.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.ItemPledgeBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeResponse;
import com.manappuram.ogl.util.Constants;
import java.util.ArrayList;
class PledgeAdapter extends RecyclerView.Adapter<PledgeAdapter.ViewHolder> {

    Context context;
    ArrayList<PledgeResponse> pledgeList;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public PledgeAdapter(Context context, ArrayList<PledgeResponse> pledgeList) {
        this.context = context;
        this.pledgeList = pledgeList;
        sharedPreferences = context.getSharedPreferences("ogl-app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPledgeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_pledge, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.invId.setText(pledgeList.get(position).getInvID());
        holder.binding.pledgeDate.setText(pledgeList.get(position).getInvDate());
        holder.binding.pledgeWeight.setText(pledgeList.get(position).getTotGrossWeight());
        holder.binding.pledgeAccNum.setText(pledgeList.get(position).getPledgeNo());
        holder.binding.pledgeValue.setText("₹" + pledgeList.get(position).getPledgeVal());
        holder.binding.pledgeEligibleAmt.setText("₹" + pledgeList.get(position).getEligibleloanamt());
        holder.binding.pledgeBalance.setText("₹" + pledgeList.get(position).getEligiblebalance());

        holder.binding.itemDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentActivity activity = (FragmentActivity) (context);
                FragmentManager fm = activity.getSupportFragmentManager();

                ItemDetailsFragment detailsFragment = new ItemDetailsFragment(pledgeList.get(position).getPledgeNo());
                detailsFragment.show(fm, "details");
            }
        });

        holder.itemView.setOnClickListener(v -> {
            editor.putString(Constants.SAVED_INVISIBLE_ID, pledgeList.get(position).getInvID());
            editor.putString(Constants.SAVED_PLEDGE_NO, pledgeList.get(position).getPledgeNo());
            editor.putString(Constants.SAVED_PLEDGE_AMOUNT, pledgeList.get(position).getPledgeVal());
            editor.putString(Constants.SAVED_settlement_AMOUNT, pledgeList.get(position).getSettlementVal());
            editor.apply();

            Intent intent = new Intent(context, SchemeActivity.class);
            intent.putExtra("pledgeNum", pledgeList.get(position).getPledgeNo());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (pledgeList != null)
            return pledgeList.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemPledgeBinding binding;

        public ViewHolder(@NonNull ItemPledgeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
