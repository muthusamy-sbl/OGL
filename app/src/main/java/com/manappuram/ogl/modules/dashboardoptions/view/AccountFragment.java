package com.manappuram.ogl.modules.dashboardoptions.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.databinding.FragmentAccountBinding;
import com.manappuram.ogl.databinding.FragmentLoginBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.AccDetailModel;
import com.manappuram.ogl.modules.dashboardoptions.model.CustAccDetailsResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.login.viewmodel.LoginViewModel;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends BaseFragment {

    private FragmentAccountBinding binding;
    private DashboardViewModel viewModel;
    AccountAdapter adapter;
    int k=0;
    ArrayList<AccDetailModel> accountList = new ArrayList<>();

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        mActivity = getActivity();

        setupRecyclerView();

        callService();

        return binding.getRoot();

    }

    private void setupRecyclerView() {
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new AccountAdapter(mActivity, accountList);
        binding.recycler.setAdapter(adapter);

    }

    private void callService() {

        accountList.clear();
        viewModel.customerDetails(mActivity);
        viewModel.getAccDetailsMutableLiveData().observe(this, accDetailsResponses -> {

            updateRequestId(accDetailsResponses.get(0).getRequestid());

            Log.d("accDetailsResponses", accDetailsResponses.size() + "");

            accountList.clear();
            for (int i = 0; i < accDetailsResponses.size(); i++) {
                accountList.add(new AccDetailModel(accDetailsResponses.get(i), null));
            }

            adapter.notifyDataSetChanged();
            getLoanDetails(accDetailsResponses);

        });
    }

    private void getLoanDetails(ArrayList<AccDetailsResponse> accDetailsResponses) {

        for (int i = 0; i < accDetailsResponses.size(); i++) {
//           if(i==0){
               callLoanDetailService(i, accDetailsResponses.get(i));
//           }
        }

        adapter.notifyDataSetChanged();
    }

    private void callLoanDetailService(int i, AccDetailsResponse response) {

        System.out.println(response);
        System.out.println(response.getPledgeNo());
        System.out.println(i);

        viewModel.loanDetails(response.getPledgeNo(), getActivity());

        viewModel.getLoanMutableLiveData().observe(this, loanResponse -> {
            Log.d("loanResponse", loanResponse.toString());
            updateRequestId(loanResponse.getRequestid());
            accountList.get(i).setLoanResponse(loanResponse);
//
//            if(k==0){
//
//            }else{
//
//            }
            adapter.notifyDataSetChanged();
        });

    }


}
