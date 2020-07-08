package com.manappuram.ogl.modules.pay;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.dashboardoptions.repository.PaymentRepository;
import com.manappuram.ogl.modules.navigation.repository.DashboardRepository;
import com.manappuram.ogl.modules.pay.model.Billdeskresponse;
import com.manappuram.ogl.modules.pay.model.FinalCheckResponse;
import com.manappuram.ogl.modules.pay.model.FinalVerificationRequest;
import com.manappuram.ogl.modules.pay.model.PayuInputRequest;
import com.manappuram.ogl.modules.pay.model.PayuInputResponse;
import com.manappuram.ogl.modules.pay.model.PledgeDetailResponse;
import com.manappuram.ogl.modules.pay.model.ServiceChargeRequest;
import com.manappuram.ogl.modules.pay.model.ServiceChrgeResonse;
import com.manappuram.ogl.modules.pay.model.TransSuccessResponse;
import com.manappuram.ogl.modules.pay.model.repledge;

public class PaymentViewModel extends ViewModel {
    protected PaymentRepository paymentRepository;
    private MutableLiveData<PledgeDetailResponse> pledgeDetailResponseMutableLiveData;
    private MutableLiveData<ServiceChrgeResonse> serviceChrgeResonseMutableLiveData;
    MutableLiveData<LoanResponse> loanMutableLiveData;
    MutableLiveData<PayuInputResponse> payuResponse;
    MutableLiveData<BaseResponse> partpayment;
    MutableLiveData<Billdeskresponse> billdeskresponseMutableLiveData;
    private FinalVerificationRequest finalCheck;
    MutableLiveData<BaseResponse> Failed;
    MutableLiveData<repledge> repledge;
    MutableLiveData<FinalCheckResponse>finalCheckResponseMutableLiveData;
    MutableLiveData<TransSuccessResponse> TransSuccessResponse;
    MutableLiveData<TransSuccessResponse> responseMutable = new MutableLiveData<>();
//    MutableLiveData<Billdeskresponse>billdeskresponseMutableLiveData=new MutableLiveData<>();


    public PaymentViewModel() {

        paymentRepository = new PaymentRepository();
        pledgeDetailResponseMutableLiveData = new MutableLiveData<>();
        serviceChrgeResonseMutableLiveData = new MutableLiveData<>();
        loanMutableLiveData = new MutableLiveData<>();
        TransSuccessResponse= new MutableLiveData<>();
        Failed= new MutableLiveData<>();
        repledge= new MutableLiveData<>();
        finalCheckResponseMutableLiveData=new MutableLiveData<>();
        payuResponse = new MutableLiveData<>();
        billdeskresponseMutableLiveData=new MutableLiveData<>();
        partpayment=new MutableLiveData<>();

    }

    public void mPledgeDetails(String pledgeNo, Context context) {
        paymentRepository.mPledgeDetails(pledgeNo,
                (PaymentRepository.SuccessResponse<PledgeDetailResponse>) pledgeDetailResponse -> {
                    pledgeDetailResponseMutableLiveData.setValue(pledgeDetailResponse);
                }, context);
    }

    public void loanDetails(String plno, Context context) {
        paymentRepository.loanDetails(plno,
                (DashboardRepository.SuccessResponse<LoanResponse>) loanResponse -> {
                    loanMutableLiveData.setValue(loanResponse);
                }, context);
    }

    public void mGetServiceCharge(ServiceChargeRequest request, Context context) {
        paymentRepository.mGetServiceCharge(request,
                (PaymentRepository.SuccessResponse<ServiceChrgeResonse>) serviceChrgeResonse -> {
                    serviceChrgeResonseMutableLiveData.setValue(serviceChrgeResonse);
                }, context);
    }

    public void payuinput(PayuInputRequest request, Context context) {
        paymentRepository.payuinputdata(request,
                (PaymentRepository.SuccessResponse<PayuInputResponse>) payuInputResponse -> {
                    if (payuInputResponse.getResult() != null) {
                        Log.d("result_payuinputdata", payuInputResponse.getResult());
                    }
                    if (payuInputResponse.getKey() != null) {
                        Log.d("hash_payuinputdata", payuInputResponse.getKey());
                    }
                    if (payuInputResponse.getAmount() != null) {
                        Log.d("amount_payuinputdata", payuInputResponse.getAmount());
                    }
                    if (payuInputResponse.getEmail() != null) {
                        Log.d("email_payuinputdata", payuInputResponse.getEmail());
                    }
                    if (payuInputResponse.getPhone() != null) {
                        Log.d("phone_payuinputdata", payuInputResponse.getPhone());
                    }
                    if (payuInputResponse.getTxnid() != null) {
                        Log.d("txnid_payuinputdata", payuInputResponse.getTxnid());
                    }
                    if (payuInputResponse.getStatus() != null) {
                        Log.d("status_payuinputdata", payuInputResponse.getStatus());
                    }
                    payuResponse.setValue(payuInputResponse);




                }, context);
    }

    public void  FinalCheck(PayuInputResponse response2, Context context) {
        finalCheck = new FinalVerificationRequest(response2);
        paymentRepository.finalinput(finalCheck,
                (PaymentRepository.SuccessResponse<FinalCheckResponse>) payuInputResponse -> {
                    finalCheckResponseMutableLiveData.setValue(payuInputResponse);
                }, context);
    }


    public void getPayuData(PayuInputRequest request, Context context) {
        paymentRepository.payuinputdata(request, (PaymentRepository.SuccessResponse<PayuInputResponse>) payuInputResponse -> {
            if (payuInputResponse.getResult() != null) {
                Log.d("result_payuinputdata", payuInputResponse.getResult());
            }
            if (payuInputResponse.getKey() != null) {
                Log.d("hash_payuinputdata", payuInputResponse.getKey());
            }
            if (payuInputResponse.getAmount() != null) {
                Log.d("amount_payuinputdata", payuInputResponse.getAmount());
            }
            if (payuInputResponse.getEmail() != null) {
                Log.d("email_payuinputdata", payuInputResponse.getEmail());
            }
            if (payuInputResponse.getPhone() != null) {
                Log.d("phone_payuinputdata", payuInputResponse.getPhone());
            }
            if (payuInputResponse.getTxnid() != null) {
                Log.d("txnid_payuinputdata", payuInputResponse.getTxnid());
            }
            if (payuInputResponse.getStatus() != null) {
                Log.d("status_payuinputdata", payuInputResponse.getStatus());
            }
            payuResponse.setValue(payuInputResponse);
            finalCheck = new FinalVerificationRequest(payuInputResponse);
        }, context);
    }


    public PaymentRepository getPaymentRepository() {
        return paymentRepository;
    }

    public MutableLiveData<PledgeDetailResponse> getPledgeDetailResponseMutableLiveData() {
        return pledgeDetailResponseMutableLiveData;
    }
    public MutableLiveData<FinalCheckResponse> getFinalCheckResponseMutableLiveData() {
        return finalCheckResponseMutableLiveData;
    }

    public MutableLiveData<repledge>getRepledge(){
        return  repledge;
    }
    public MutableLiveData<BaseResponse> getPartpayment() {
        return partpayment;
    }
    public MutableLiveData<ServiceChrgeResonse> getServiceChrgeResonseMutableLiveData() {
        return serviceChrgeResonseMutableLiveData;
    }

    public MutableLiveData<LoanResponse> getLoanMutableLiveData() {
        return loanMutableLiveData;
    }

    public MutableLiveData<PayuInputResponse> getUrl() {
        return payuResponse;
    }

    public MutableLiveData<Billdeskresponse> getBilldeskresponseMutableLiveData() {
        return billdeskresponseMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getfilled() {
        return Failed ;
    }
    public MutableLiveData<TransSuccessResponse> getsuccess() {
        return TransSuccessResponse;
    }

    public void billrequest(String customerId, String uniqueSessionId, String requestId, String langId, String pledgeNo,Context context, String total) {
        paymentRepository.billrespose(total,customerId,uniqueSessionId,requestId,langId,pledgeNo,
                (PaymentRepository.SuccessResponse<Billdeskresponse>) billdeskresponse -> {
                    billdeskresponseMutableLiveData.setValue(billdeskresponse);
                },context);
    }


    public void Partpayment(String pledgeNo, String amount, Activity mActivity) {
        paymentRepository.partpayment(pledgeNo,amount,
                (PaymentRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    partpayment.setValue(baseResponse);
                },mActivity);

    }


    public void logFailedTransaction(PayuInputRequest request, Activity mActivity, String txnId) {
        paymentRepository.FailedTransaction(request,txnId,
                (PaymentRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    Failed.setValue(baseResponse);
                },mActivity);
    }


    public void performFinalCheck(PayuInputRequest request, Activity mActivity, String txnId) {
        paymentRepository.SuccessTransaction(request,txnId,
                (PaymentRepository.SuccessResponse<TransSuccessResponse>) baseResponse -> {
                    TransSuccessResponse.setValue(baseResponse);
                },mActivity);
    }

    public void checkrepldge(String s, Context context) {
        paymentRepository.Repledge(s,
                (PaymentRepository.SuccessResponse<repledge>) baseResponse -> {
                    repledge.setValue(baseResponse);
               },context);
    }
}
