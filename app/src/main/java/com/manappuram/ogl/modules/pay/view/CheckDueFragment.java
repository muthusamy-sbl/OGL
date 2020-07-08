package com.manappuram.ogl.modules.pay.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.databinding.FragmentCheckDueBinding;
import com.manappuram.ogl.modules.dashboardoptions.ItemClicked;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckDueFragment extends BaseFragment implements ItemClicked {

    private FragmentCheckDueBinding binding;
    private DashboardViewModel viewModel;
    private DueAdapter adapter;
    private ArrayList<AccDetailsResponse> accountList = new ArrayList<>();

    public static CheckDueFragment newInstance() {
        Bundle args = new Bundle();
        CheckDueFragment fragment = new CheckDueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CheckDueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCheckDueBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();

        setupRecyclerView();

        getAccounts();

        serviceResponses();

        return binding.getRoot();
    }

    private void serviceResponses() {
        viewModel.getAccDetailsMutableLiveData().observe(this, this::accDetailSuccess);
    }


    private void setupRecyclerView() {
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new DueAdapter(mActivity, accountList, this);
        binding.recycler.setAdapter(adapter);
    }

    private void getAccounts() {
        viewModel.customerDetails(mActivity);
    }

    private void accDetailSuccess(ArrayList<AccDetailsResponse> accDetailsResponses) {
        updateRequestId(accDetailsResponses.get(0).getRequestid());
        accountList.clear();
        accountList.addAll(accDetailsResponses);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void itemClicked(String type, Object object) {
        AccDetailsResponse response = (AccDetailsResponse) object;
        Intent intent = new Intent(mActivity, SelectPayActivity.class);
        intent.putExtra("pledgeNo", response.getPledgeNo());
        intent.putExtra("pledgeAmt", response.getPledgeAmt());
        startActivity(intent);
    }
}
