package com.manappuram.ogl.modules.dashboardoptions.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivitySchemeBinding;
import com.manappuram.ogl.modules.dashboardoptions.ItemClicked;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Utility;

import java.util.ArrayList;

public class SchemeActivity extends BaseActivity implements ItemClicked {

    ActivitySchemeBinding binding;
    DashboardViewModel viewModel;
    String pledgeNum;
    SchemeAdapter adapter;
    ArrayList<SchemeResponse> schemeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scheme);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        pledgeNum = getIntent().getStringExtra("pledgeNum");

        mActivity = this;

        binding.back.setOnClickListener(v -> finish());

        setupRecyclerView();

        callService();

    }

    private void callService() {
        viewModel.getSchemeDetails(pledgeNum, mActivity);

        viewModel.getSchemeLiveData().observe(this, schemeResponses -> {

            Log.d("schemeResponses", schemeResponses.size() + "");
            updateRequestId(schemeResponses.get(0).getRequestid());
            schemeList.clear();
            schemeList.addAll(schemeResponses);
            adapter.notifyDataSetChanged();

        });

    }

    private void setupRecyclerView() {
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new SchemeAdapter(mActivity, schemeList, this);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void itemClicked(String type, Object object) {

        SchemeResponse schemeResponse = (SchemeResponse) object;
        long max = Long.parseLong(schemeResponse.getMaxLoan());
        Gson gson = new Gson();

        if (type.equals("1")) {

            if (max < 1) {

                Utility.showCustomErrorDialog(mActivity, "Alert", "Your maximum eligible loan amount is not sufficient to setting existing loan amount...", false, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utility.closeErrorDialog();
                    }
                });

            } else {
                Intent intent = new Intent(mActivity, AmountActivity.class);
                intent.putExtra("response", gson.toJson(schemeResponse));
                intent.putExtra("pledgeNum", pledgeNum);
                startActivity(intent);
            }

        } else {

            SchemeDetailsFragment schemeDetailsFragment = new SchemeDetailsFragment(pledgeNum);
            schemeDetailsFragment.show(getSupportFragmentManager(), "Scheme");

        }

    }
}
