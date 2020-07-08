package com.manappuram.ogl.modules.pay.view;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityPaymentDetailBinding;
import com.manappuram.ogl.modules.pay.PaymentViewModel;
import com.manappuram.ogl.modules.pay.model.ServiceChargeRequest;
import com.manappuram.ogl.modules.pay.model.ServiceChrgeResonse;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.Utility;

import java.util.Objects;

public class PaymentDetailActivity extends BaseActivity {

    PaymentViewModel viewModel;
    ActivityPaymentDetailBinding binding;
    String pledgeNo;
    String pledgeAmt,payMethod;
    String termsError = "Please Accept Terms & Conditions";
    int ptype = 0;
    String paytype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_detail);
        viewModel = ViewModelProviders.of(this).get(PaymentViewModel.class);
        mActivity = this;
        pledgeNo = getIntent().getStringExtra("pledgeNo");
        pledgeAmt = getIntent().getStringExtra("pledgeAmt");
        payMethod = getIntent().getStringExtra("payMethod");

        System.out.println("payMethod:"+payMethod);

        binding.back.setOnClickListener(v -> finish());
        binding.loanAccountNum.setText(pledgeNo);
        binding.loanAmount.setText(pledgeAmt);
        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.radioDebit){
                paytype= String.valueOf(1);
                getServiceCharge(paytype);
            } else if (checkedId == R.id.radioNetBank) {
                paytype= String.valueOf(2);
                getServiceCharge(paytype);
            } else {
                paytype= String.valueOf(3);
                getServiceCharge(paytype);
            }
        });

        binding.selectPayType.setOnClickListener(v -> {
            showtype(mActivity);
        });

        viewModel.getServiceChrgeResonseMutableLiveData().observe(this, serviceChrgeResonse -> {
            updateRequestId(serviceChrgeResonse.getRequestid());
            Log.d("serviceChrgeResonse", serviceChrgeResonse.toString());

            binding.tvAmount.setText(serviceChrgeResonse.getTotalAmount());
            binding.tvCharge.setText(serviceChrgeResonse.getServiceCharge());
            pledgeAmt=serviceChrgeResonse.getTotalAmount();
        });

        binding.btnSubmit.setOnClickListener(v -> {
        //   Float total= Float.valueOf(binding.tvAmount.getText().toString())+Float.valueOf(binding.tvCharge.getText().toString());
            if (binding.checkbox.isChecked()) {
                System.out.println("test type1"+ptype);
//                binding.selectPayType.setText("PayU");
               if(binding.selectPayType.getText().toString().equals("PayU")){
                   Intent intent = new Intent(mActivity, PayConfirmActivity.class);
                   intent.putExtra("payMethod", payMethod);
                   intent.putExtra("pledgeNo", pledgeNo);
                   intent.putExtra("pledgeAmt", pledgeAmt);
                   intent.putExtra("paytype",paytype);
                   intent.putExtra("payu","PayU");
                   startActivity(intent);
               }else{
                   Intent intent = new Intent(mActivity, PayConfirmActivity.class);
                   intent.putExtra("payMethod", payMethod);
                   intent.putExtra("pledgeNo", pledgeNo);
                   intent.putExtra("pledgeAmt", pledgeAmt);
                   intent.putExtra("paytype",paytype);
                   intent.putExtra("payu","Bill Desk");
                   startActivity(intent);
               }

//if(binding.selectPayType.getText().toString().equals("Bill Desk")){
//    System.out.println(UserSession.getInstance().getCustomerId()+"~"+
//
//            UserSession.getInstance().getUniqueSessionId()+"~"+
//            UserSession.getInstance().getRequestId()+"~"+
//            UserSession.getInstance().getLangId()+"~"+pledgeNo);
////                      viewModel.billrequest(UserSession.getInstance().getCustomerId(),
////                              UserSession.getInstance().getUniqueSessionId(),
////                              UserSession.getInstance().getRequestId(),
////                              UserSession.getInstance().getLangId(),pledgeNo,mActivity,total.toString());
//
//    viewModel.getBilldeskresponseMutableLiveData().observe(this, billdeskresponse -> {
//
//        Log.d("serviceChrgeResonse", billdeskresponse.getAmount());
//
//
//    });
//
//
//}else{
//
//
//}

            } else {
                Utility.showCustomErrorDialog(mActivity, "", termsError, true, v1 -> Utility.closeErrorDialog());
            }
        });
    }

    private void getServiceCharge(String i) {

        System.out.println("payMethod:"+getPaymentMethod());

        System.out.println("ptype:"+getPaymentMethodType());

        ServiceChargeRequest request = new ServiceChargeRequest();
        request.setAmount(binding.loanAmount.getText().toString());
        request.setPaymenttype(getPaymentMethodType());
        request.setPaymethod(getPaymentMethod());
        request.setPlno(pledgeNo);
        viewModel.mGetServiceCharge(request, mActivity);

    }


    private void showtype(Context context) {

        Dialog payTypeDialog;
        TextView payU, billDesk;

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {

                payTypeDialog = new Dialog(context, R.style.CustomDialogTheme);
                payTypeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                payTypeDialog.setContentView(R.layout.custom_paytype_selctor);
                payTypeDialog.setCanceledOnTouchOutside(true);
                payTypeDialog.setCancelable(true);

                Objects.requireNonNull(payTypeDialog.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
                payTypeDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogTheme;

                Window window = payTypeDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM;
                window.setAttributes(wlp);

                payU = payTypeDialog.findViewById(R.id.payU);
                billDesk = payTypeDialog.findViewById(R.id.billDesk);

                payU.setOnClickListener(view -> {
                    binding.selectPayType.setText("PayU");
                    binding.radioUpi.setVisibility(View.VISIBLE);
                    payTypeDialog.dismiss();
                });
                billDesk.setOnClickListener(view -> {
                    binding.selectPayType.setText("Bill Desk");
                    binding.radioUpi.setVisibility(View.GONE);
                    payTypeDialog.dismiss();
                });

                payTypeDialog.show();
            }
        }

    }


    public String getPaymentMethodType() {

        if( binding.selectPayType.getText().toString().equals("PayU")) {
            if (paytype.equals("1")) {
                return Constants.PAYU_DEBIT_CARD;
            } else if (paytype.equals("3")) {
                return Constants.PAYU_UPI;
            } else if (paytype.equals("2")) {
                return Constants.PAYU_NETBANKING;
            }
        }else{
            if (paytype.equals("1")) {
                return Constants.BILLDESK_DEBIT_CARD;
            } else if (paytype.equals("3")) {
                return Constants.PAYU_UPI;
            } else if (paytype.equals("2")) {
                return Constants.BILLDESK_NETBANKING;
            }
        }


        return "";
    }

    public String getPaymentMethod() {
        if (payMethod.equals("1")) {
            return Constants.FULL_INTEREST_PAYMENT;
        } else if (payMethod.equals("3")) {
            return Constants.FULL_SETTLEMENT_PAYMENT;
        } else if (payMethod.equals("2")) {
            return Constants.PART_PAYMENT;
        }
        return "";
    }
}
