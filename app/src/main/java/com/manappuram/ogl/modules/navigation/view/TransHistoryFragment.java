package com.manappuram.ogl.modules.navigation.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseFragment;
import com.manappuram.ogl.databinding.FragmentOglHistoryBinding;
import com.manappuram.ogl.databinding.FragmentTransHistoryBinding;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.model.OglHistoryRequest;
import com.manappuram.ogl.modules.navigation.model.OglHistoryresponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TransHistoryFragment extends BaseFragment {

    FragmentTransHistoryBinding binding;

    OglHistoryAdapter adapter;
    ArrayList<OglHistoryresponse> historyList = new ArrayList<>();
    DashboardViewModel viewModel;
    ArrayList<AccDetailsResponse> accountList = new ArrayList<>();
    final Calendar myCalendar = Calendar.getInstance();
    public TransHistoryFragment() {
        // Required empty public constructor
    }
    public static TransHistoryFragment newInstance() {
        
        Bundle args = new Bundle();
        TransHistoryFragment fragment = new TransHistoryFragment();
        fragment.setArguments(args);





        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTransHistoryBinding.inflate(inflater, container, false);
        mActivity = getActivity();
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        setupRecyclerView();

        binding.selectPledge.setOnClickListener(v -> {
            showAccountDialog();
        });


        binding.selectDate1.setOnClickListener(v -> {
            datePicker1();
        });

        binding.selectDate2.setOnClickListener(v -> {
            datePicker2();
        });

        binding.btnSubmit.setOnClickListener(v -> {
            String date1= binding.selectDate1.getText().toString();
            String date2= binding.selectDate2.getText().toString();
            if(!date1.equals("")&&!date2.equals("")) {

                String plno = binding.selectPledge.getText().toString();
                String fromdate = binding.selectDate1.getText().toString();
                String todate = binding.selectDate2.getText().toString();
                OglHistoryRequest oglHistoryRequest = new OglHistoryRequest(plno, plno, fromdate, todate, "");
                viewModel.getOglHistory(oglHistoryRequest, mActivity, "2");
            }else{
                Utility.showSnackbar(binding.getRoot(),"Fill the forms");
            }
        });

        viewModel.getOglHistory().observe(this, accDetailsResponses -> {

            updateRequestId(accDetailsResponses.get(0).getRequestid());
            Log.d("accDetailsResponses", accDetailsResponses.size() + "");
            historyList.clear();
            historyList.addAll(accDetailsResponses);
            adapter.notifyDataSetChanged();
        });

//        viewModel.getOglHistory();


        viewModel.customerDetails(mActivity);

        viewModel.getAccDetailsMutableLiveData().observe(this, accDetailsResponses -> {

            updateRequestId(accDetailsResponses.get(0).getRequestid());
            Log.d("accDetailsResponses", accDetailsResponses.size() + "");
            accountList.clear();
            accountList.addAll(accDetailsResponses);

        });


        return binding.getRoot();


        //FAQ
        //About
        //Contact
        //Locate Us

        //Scheme List
        //FAQ
        //Contact
        //Demo Video
        //Locate Us
    }

    private DatePickerDialog.OnDateSetListener date1 = (view, year, monthOfYear, dayOfMonth) -> {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel(binding.selectDate1);
    };

    private DatePickerDialog.OnDateSetListener date2 = (view, year, monthOfYear, dayOfMonth) -> {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel(binding.selectDate2);
    };


    private void datePicker1() {
        new DatePickerDialog(mActivity, date1, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void datePicker2() {
        new DatePickerDialog(mActivity, date2, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel(TextView tvDate) {
        String myFormat = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tvDate.setText(sdf.format(myCalendar.getTime()));
    }


    private void setupRecyclerView() {

        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new OglHistoryAdapter(mActivity, historyList);
        binding.recycler.setAdapter(adapter);

    }

    private void showAccountDialog() {

        Dialog accountDialog;
        if (!mActivity.isFinishing()) {


            accountDialog = new Dialog(mActivity, R.style.CustomDialogTheme);
            accountDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            accountDialog.setContentView(R.layout.custom_account_selctor);
            accountDialog.setCanceledOnTouchOutside(true);
            accountDialog.setCancelable(true);


            Objects.requireNonNull(accountDialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
            accountDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogTheme;

            Window window = accountDialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            window.setAttributes(wlp);


            List<String> accounts = new ArrayList<>();
            for (int i = 0; i < accountList.size(); i++) {
                accounts.add(accountList.get(i).getPledgeNo());
            }

            WheelPicker wheelPicker = accountDialog.findViewById(R.id.picker_ui_view);
            TextView btnSelect = accountDialog.findViewById(R.id.btnSelect);
            wheelPicker.setData(accounts);

            btnSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("wheelPicker-->", accounts.get(wheelPicker.getSelectedItemPosition()));
                    accountDialog.dismiss();
                    binding.selectPledge.setText(accounts.get(wheelPicker.getSelectedItemPosition()));

                }
            });

            accountDialog.show();
        }


    }
}