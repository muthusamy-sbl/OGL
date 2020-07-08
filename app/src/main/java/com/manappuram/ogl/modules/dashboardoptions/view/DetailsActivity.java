package com.manappuram.ogl.modules.dashboardoptions.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityDetailsBinding;
import com.manappuram.ogl.modules.dashboardoptions.ItemClicked;
import com.manappuram.ogl.modules.dashboardoptions.model.ApplicationFormResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.DetailsModel;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.Utility;

import java.util.ArrayList;

public class DetailsActivity extends BaseActivity implements LanguageInterface, ItemClicked {

    ActivityDetailsBinding binding;
    DetailAdapter adapter;
    ArrayList<DetailsModel> detailList = new ArrayList<>();

    String response;
    String amount;
    String pledgeNum;
    SchemeResponse schemeResponse;
    DashboardViewModel viewModel;

    String invId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        response = getIntent().getStringExtra("response");
        amount = getIntent().getStringExtra("amount");
        pledgeNum = getIntent().getStringExtra("pledgeNum");
        Gson gson = new Gson();
        schemeResponse = gson.fromJson(response, SchemeResponse.class);


        mActivity = this;

        invId = sharedPreferences.getString(Constants.SAVED_INVISIBLE_ID, "");

        setupRecyclerView();


        getApplicationformDetils();


        viewModel.getApplicationFormLiveData().observe(this, new Observer<ApplicationFormResponse>() {
            @Override
            public void onChanged(ApplicationFormResponse applicationFormResponse) {

                Log.d("applicationFormResponse", applicationFormResponse.toString());
                updateRequestId(applicationFormResponse.getRequestid());
                detailList.get(0).setApplicationFormResponse(applicationFormResponse);
                detailList.get(1).setApplicationFormResponse(applicationFormResponse);
                detailList.get(2).setSchemeResponse(schemeResponse);
                detailList.get(2).setAmount(amount);
                adapter.notifyDataSetChanged();

                getTerms("EN");
            }
        });

        viewModel.getTermsLiveData().observe(this, baseResponse -> {
            Utility.cancelProgressbar();
            updateRequestId(baseResponse.getRequestid());
            if (baseResponse.getStatus().equals("111")) {
                Log.d("baseResponse", baseResponse.getResult());
                detailList.get(3).setTerms(baseResponse.getResult());
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getDashboardRepository().getFailMessageMutable().observe(this, stringEvent -> Utility.cancelProgressbar());

        viewModel.getDashboardRepository().getErrorsMutable().observe(this, baseResponseEvent -> Utility.cancelProgressbar());

    }

    private void getApplicationformDetils() {
        viewModel.getApplicationFormDetails(invId, pledgeNum, mActivity);
    }

    private void setupRecyclerView() {
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new DetailAdapter(mActivity, detailList, this, this, "1");
        binding.recycler.setAdapter(adapter);

        detailList.add(new DetailsModel("personal", null, ""));
        detailList.add(new DetailsModel("inventory", null, ""));
        detailList.add(new DetailsModel("pledge", null, ""));
        detailList.add(new DetailsModel("terms", null, ""));
    }

    private void getTerms(String lang) {
//        Utility.setProgressbar(mActivity);
        viewModel.getTermsandConditionsDetails(lang, mActivity);
    }

    @Override
    public void languageSelected(String item) {
        getTerms(item);
    }

    @Override
    public void itemClicked(String type, Object object) {

        if (type.equals("click")) {

            Intent intent = new Intent(mActivity, SubmitActivity.class);
            intent.putExtra("amount", amount);
            startActivity(intent);
        }

    }
}
