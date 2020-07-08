package com.manappuram.ogl.modules.dashboardoptions.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.base.BaseDialog;
import com.manappuram.ogl.modules.dashboardoptions.model.ItemResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemDetailsFragment extends BaseDialog {

    DashboardViewModel viewModel;
    String pledgeNum;
    ItemDetailAdapter adapter;
    ArrayList<ItemResponse> itemList;

    public ItemDetailsFragment(String pledgeNum) {
        // Required empty public constructor
        this.pledgeNum = pledgeNum;
    }


    OptionListener optionListener;

    public void setOptionListener(OptionListener optionListener) {
        this.optionListener = optionListener;
    }

    public interface OptionListener {
        void optionSelected(String item);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NotNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.fragment_item_details, null);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        dialog.setContentView(contentView);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setGravity(Gravity.TOP);

            WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
            p.width = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(p);
        }

        mActivity = getActivity();

        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));


        RecyclerView recyclerView = contentView.findViewById(R.id.recycler);

        TextView textView=contentView.findViewById(R.id.terms);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new ItemDetailAdapter(mActivity, itemList);
        recyclerView.setAdapter(adapter);

        viewModel.getItemDetails(pledgeNum,mActivity);

        viewModel.getItemLiveData().observe(this, new Observer<ArrayList<ItemResponse>>() {
            @Override
            public void onChanged(ArrayList<ItemResponse> itemResponses) {

                updateRequestId(itemResponses.get(0).getRequestid());

                Log.d("itemResponses", itemResponses.size() + "");

                adapter.itemList = itemResponses;
                adapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);

    }


}
