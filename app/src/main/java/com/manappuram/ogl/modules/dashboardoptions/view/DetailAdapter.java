package com.manappuram.ogl.modules.dashboardoptions.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.databinding.ItemDetailBinding;
import com.manappuram.ogl.modules.dashboardoptions.ItemClicked;
import com.manappuram.ogl.modules.dashboardoptions.model.DetailsModel;
import com.manappuram.ogl.session.UserSession;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    Context context;
    ArrayList<DetailsModel> detailList;
    LanguageInterface languageInterface;
    ItemClicked itemClicked;
    String s;

    public DetailAdapter(Context context, ArrayList<DetailsModel> detailList, LanguageInterface languageInterface, ItemClicked itemClicked, String s) {
        this.context = context;
        this.detailList = detailList;
        this.languageInterface = languageInterface;
        this.itemClicked = itemClicked;
        this.s=s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_detail, parent, false);

        return new DetailAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (detailList.get(position).getType()) {
            case "personal":
                holder.binding.itemPersonalDetails.setVisibility(View.VISIBLE);
                holder.binding.itemInventoryDetails.setVisibility(View.GONE);
                holder.binding.itemPledgeDetails.setVisibility(View.GONE);
                holder.binding.itemTerms.setVisibility(View.GONE);
                System.out.println("mani "+detailList.get(position).getRepledge());
                if (detailList.get(position).getApplicationFormResponse() != null||detailList.get(position).getRepledge() != null) {
                    if (s.equals("1")) {
                        holder.binding.tvCustomerId.setText(UserSession.getInstance().getCustomerId());
                        holder.binding.tvCustomerName.setText(detailList.get(position).getApplicationFormResponse().getCusName());
                        holder.binding.tvPostOffice.setText(detailList.get(position).getApplicationFormResponse().getCusPost());
                        holder.binding.tvDistrict.setText(detailList.get(position).getApplicationFormResponse().getCusDist());
                        holder.binding.tvState.setText(detailList.get(position).getApplicationFormResponse().getCusState());
                        holder.binding.tvCustomerPhone.setText(detailList.get(position).getApplicationFormResponse().getCusPhone());
                        holder.binding.tvCustomerMail.setText(detailList.get(position).getApplicationFormResponse().getCusEmail());
                    }else{
                        holder.binding.tvCustomerId.setText(UserSession.getInstance().getCustomerId());
                        holder.binding.tvCustomerName.setText(detailList.get(position).getRepledge().getName());
                        holder.binding.tvPostOffice.setText(detailList.get(position).getRepledge().getPostoffice());
                        holder.binding.tvDistrict.setText(detailList.get(position).getRepledge().getDistrict());
                        holder.binding.tvState.setText(detailList.get(position).getRepledge().getState());
                        holder.binding.tvAddress.setText(detailList.get(position).getRepledge().getAddress());
                        holder.binding.tvCustomerPhone.setText(detailList.get(position).getRepledge().getMobileno());
                        holder.binding.tvCustomerMail.setText(detailList.get(position).getRepledge().getEmailid());
                    }

                }
                break;
            case "inventory":
                holder.binding.itemPersonalDetails.setVisibility(View.GONE);
                holder.binding.itemInventoryDetails.setVisibility(View.VISIBLE);
                holder.binding.itemPledgeDetails.setVisibility(View.GONE);
                holder.binding.itemTerms.setVisibility(View.GONE);
                if (detailList.get(position).getApplicationFormResponse() != null) {
                    holder.binding.tvInventoryId.setText(detailList.get(position).getApplicationFormResponse().getInvID());
                    holder.binding.tvInventoryItemCount.setText(detailList.get(position).getApplicationFormResponse().getItmCount());
                    holder.binding.tvActualWeight.setText(detailList.get(position).getApplicationFormResponse().getTotGrossWeight());
                    holder.binding.tvStoneWeight.setText(detailList.get(position).getApplicationFormResponse().getTotStoneWeight());
                    holder.binding.tvNetWeight.setText(detailList.get(position).getApplicationFormResponse().getTotStdWeight());
                }
                break;
            case "pledge":
                holder.binding.itemPersonalDetails.setVisibility(View.GONE);
                holder.binding.itemInventoryDetails.setVisibility(View.GONE);

                holder.binding.itemTerms.setVisibility(View.GONE);
                if (s.equals("1")) {
                    holder.binding.itemPledgeDetails.setVisibility(View.VISIBLE);
                    holder.binding.tvScheme.setText(detailList.get(position).getSchemeResponse().getSchmName());
                    holder.binding.tvRateperGram.setText(detailList.get(position).getSchemeResponse().getLendRate());
                    holder.binding.tvPeriod.setText(detailList.get(position).getSchemeResponse().getDuration());
                    holder.binding.tvInterest.setText(detailList.get(position).getSchemeResponse().getInterest());
                    holder.binding.tvEligibleLoan.setText(detailList.get(position).getSchemeResponse().getLoanValue());
                    holder.binding.tvLoanAmt.setText(detailList.get(position).getAmount());
                }
                break;
            case "terms":
                holder.binding.itemPersonalDetails.setVisibility(View.GONE);
                holder.binding.itemInventoryDetails.setVisibility(View.GONE);
                holder.binding.itemPledgeDetails.setVisibility(View.GONE);
                holder.binding.itemTerms.setVisibility(View.VISIBLE);
                holder.binding.tvTerms.setText(detailList.get(position).getTerms());
                break;
        }


        holder.binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.radioEnglish) {
                languageInterface.languageSelected("EN");
            } else {
                languageInterface.languageSelected("MA");
            }

        });

        holder.binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.equals("1")){
                    itemClicked.itemClicked("click", detailList.get(position));
                }else{

                    itemClicked.itemClicked("click", detailList.get(position));
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        if (detailList != null)
            return detailList.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemDetailBinding binding;

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull ItemDetailBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.tvTerms.setOnTouchListener((v, event) -> {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            });
            binding.tvTerms.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}
