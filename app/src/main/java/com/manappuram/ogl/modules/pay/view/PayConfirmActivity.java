package com.manappuram.ogl.modules.pay.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.manappuram.ogl.R;
import com.manappuram.ogl.base.BaseActivity;
import com.manappuram.ogl.databinding.ActivityPayConfirmBinding;
import com.manappuram.ogl.modules.pay.model.PayuInputRequest;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Constants;
import com.manappuram.ogl.util.Utility;

public class PayConfirmActivity extends BaseActivity {

    ActivityPayConfirmBinding binding;
    String termsError = "Please Accept Terms & Conditions";
    String pledgeAmt, pledgeNo, payMethod,paytype,PayU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pay_confirm);

        mActivity = this;

        pledgeNo = getIntent().getStringExtra("pledgeNo");
        pledgeAmt = getIntent().getStringExtra("pledgeAmt");
        payMethod = getIntent().getStringExtra("payMethod");
        paytype = getIntent().getStringExtra("paytype");
        PayU = getIntent().getStringExtra("payu");


        System.out.println("test type"+paytype);
        binding.back.setOnClickListener(v -> finish());

        binding.btnSubmit.setOnClickListener(v -> {

            PayuInputRequest request = new PayuInputRequest();
            Log.d("total amount", pledgeAmt);
            request.setAmount(pledgeAmt);
            request.setCusid(UserSession.getInstance().getCustomerId());
            request.setPlno(pledgeNo);
            request.setPaymentType(getPaymentMethodType());
            request.setPaymentMethod(getPaymentMethod());

            if (binding.checkbox.isChecked()) {
//
                if (binding.radioRepledge.isChecked()) {
                    ReviewFragment reviewFragment = new ReviewFragment(pledgeNo,request);
                    reviewFragment.show(getSupportFragmentManager(), "Review");

                }
                else{

System.out.println(request.getPaymentMethod());
                    System.out.println(request.getPaymentType());
                    System.out.println(pledgeAmt);
                    Intent intent = new Intent(mActivity, PaymentActivity.class);
                    intent.putExtra(Constants.PAY_REQUEST, request);
                    startActivity(intent);

                  }

            } else {
                Utility.showCustomErrorDialog(mActivity, "", termsError, true, v1 -> Utility.closeErrorDialog());
            }


        });

    }

    public String getPaymentMethodType() {
        if(PayU.equals("PayU")) {
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
