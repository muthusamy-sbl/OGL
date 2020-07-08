package com.manappuram.ogl.modules.dashboardoptions.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.google.gson.Gson;
import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityCalculatorBinding;
import com.manappuram.ogl.modules.dashboardoptions.model.CalcRequest;
import com.manappuram.ogl.modules.dashboardoptions.model.CalculatorResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeResponse;
import com.manappuram.ogl.modules.navigation.viewmodel.DashboardViewModel;
import com.manappuram.ogl.util.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalculatorActivity extends BaseActivity {

    ActivityCalculatorBinding binding;
    CalculatorAdapter adapter;
    ArrayList<CalculatorResponse> calcList = new ArrayList<>();
    final Calendar myCalendar = Calendar.getInstance();

    String response;
    SchemeResponse schemeResponse;

    DashboardViewModel viewModel;

    String branchId;
    String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator);
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        response = getIntent().getStringExtra("response");
        branchId = getIntent().getStringExtra("branchId");
        amount = getIntent().getStringExtra("amount");
        Gson gson = new Gson();
        schemeResponse = gson.fromJson(response, SchemeResponse.class);

        mActivity = this;


        binding.back.setOnClickListener(v -> finish());

        binding.tvScheme.setText("Selected Scheme : " + schemeResponse.getSchmName());
        binding.tvPledgeAmt.setText(amount);


        setupRecyclerView();

    }

    private void setupRecyclerView() {
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CalculatorAdapter(mActivity, calcList);
        binding.recycler.setAdapter(adapter);
    }

    DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    };


    public void selectDate(View view) {
        new DatePickerDialog(mActivity, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void calculate(View view) {

        if(!binding.tvSelectDate.getText().toString().equals("")) {

            CalcRequest calcRequest = new CalcRequest(amount, binding.tvSelectDate.getText().toString(), schemeResponse.getSchmName(), branchId);


            Log.d("calcRequest", calcRequest.toString());

            viewModel.getCalculator(calcRequest, mActivity);

            viewModel.getCalcLiveData().observe(this, calculatorResponses -> {

                if (calculatorResponses.get(0).getStatus().equals("111")) {
                    Log.d("calculatorResponses", calculatorResponses.size() + "");
                    binding.resultLayout.setVisibility(View.VISIBLE);
                    updateRequestId(calculatorResponses.get(0).getRequestid());
                    calcList.clear();
                    calcList.addAll(calculatorResponses.subList(0, calculatorResponses.size() - 1));
                    adapter.notifyDataSetChanged();

                    binding.tvTotal.setText(getTotal(calculatorResponses.get(calculatorResponses.size() - 1)));
                }

            });
        }else{
            Utility.showCustomErrorDialog(mActivity, "","Select Date", false, v -> {
                Utility.closeErrorDialog();

            });

        }
    }

    private String getTotal(CalculatorResponse calculatorResponse) {

        return "Days : " + calculatorResponse.getMinday() + " - " + calculatorResponse.getMaxday() + "    Amount : " +
                calculatorResponse.getIntmt();
    }

    private void updateLabel() {
        String myFormat = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        binding.tvSelectDate.setText(sdf.format(myCalendar.getTime()));
    }
}
