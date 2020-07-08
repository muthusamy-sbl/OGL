package com.manappuram.ogl.modules.dashboardoptions.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityPledgeBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;

import java.util.ArrayList;
import java.util.List;

public class PledgeActivity extends BaseActivity {

    ActivityPledgeBinding binding;
    DashboardViewModel viewModel;
    PledgeAdapter adapter;
    ArrayList<PledgeResponse> pledgeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pledge);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = this;
        binding.back.setOnClickListener(v -> finish());
        setupRecycler();
        getPledges();


    }

    private void setupRecycler() {
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new PledgeAdapter(mActivity, pledgeList);
        binding.recycler.setAdapter(adapter);
    }

    private void getPledges() {

        viewModel.getPledges(mActivity);

        viewModel.getPledgeLiveData().observe(this, new Observer<ArrayList<PledgeResponse>>() {
            @Override
            public void onChanged(ArrayList<PledgeResponse> responseList) {

                updateRequestId(responseList.get(0).getRequestid());
                pledgeList.clear();
                pledgeList.addAll(responseList);
                adapter.notifyDataSetChanged();

            }
        });


    }
}
