package com.manappuram.ogl.modules.dashboardoptions.repository;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.manappuram.ogl.base.BaseRepository;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.login.model.LoginRequest;
import com.manappuram.ogl.modules.login.model.LoginResponse;
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
import com.manappuram.ogl.network.ResponseListener;
import com.manappuram.ogl.network.RetrofitClient;
import com.manappuram.ogl.network.RetrofitRequest;
import com.manappuram.ogl.session.UserSession;

import okhttp3.Headers;
import retrofit2.Call;

public class PaymentRepository extends BaseRepository {

    public void billrespose(String total , String customerId, String uniqueSessionId, String requestId, String langId, String pledgeNo, SuccessResponse<Billdeskresponse> successResponse, Context mActivity) {
        Call<Billdeskresponse> call = RetrofitClient.getDashboardApiInterface().GetBilldeskRequest(
                customerId,uniqueSessionId,pledgeNo,total,requestId,langId);

        new RetrofitRequest<>(call, new ResponseListener<Billdeskresponse>() {
            @Override
            public void onResponse(Billdeskresponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(mActivity);
    }


    public void mPledgeDetails(String pledgeNo, SuccessResponse successResponse, Context context) {
        Call<PledgeDetailResponse> call = RetrofitClient.getDashboardApiInterface().mPledgeDetails(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(), pledgeNo,
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<PledgeDetailResponse>() {
            @Override
            public void onResponse(PledgeDetailResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(context);
    }


    public void loanDetails(String plno, SuccessResponse successResponse, Context context) {
        Call<LoanResponse> call = RetrofitClient.getDashboardApiInterface().mLoanDetails(plno,
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<LoanResponse>() {
            @Override
            public void onResponse(LoanResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                Log.d("error-->", errors.getResult());
                errorsMutable.postValue(new Event<>(errors));
            }


            @Override
            public void onFailure(Throwable throwable) {
                Log.d("error-->", throwable.getMessage());
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(context);
    }


    public void mGetServiceCharge(ServiceChargeRequest request, SuccessResponse successResponse, Context context) {
        Call<ServiceChrgeResonse> call = RetrofitClient.getDashboardApiInterface().mGetServiceCharge(
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getCustomerId(),
                request.getPaymethod(), request.getAmount(), request.getPlno(), request.getPaymenttype(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<ServiceChrgeResonse>() {
            @Override
            public void onResponse(ServiceChrgeResonse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(context);
    }

    public void payuinputdata(PayuInputRequest request, SuccessResponse successResponse, Context context) {
        Call<PayuInputResponse> call = RetrofitClient.getDashboardApiInterface().payuinputdata(
                request.getAmount(),
                UserSession.getInstance().getCustomerId(),
                request.getPlno(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        System.out.println( "Amount:"+request.getAmount()+"/"+
                        "CustomerId:"+UserSession.getInstance().getCustomerId()+"/"+
                        "Plno:"+ request.getPlno()+"/"+
                "UniqueSessionId:"+ UserSession.getInstance().getUniqueSessionId()+"/"+
                "RequestId:"+   UserSession.getInstance().getRequestId()+"/"+
                "LangId:"+  UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<PayuInputResponse>() {
            @Override
            public void onResponse(PayuInputResponse response, Headers headers) {
                if (null != successResponse) {
                    System.out.println("payu result"+successResponse);
                    successResponse.onResponse(response);

                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(context);
    }

    public void finalinput(FinalVerificationRequest request, SuccessResponse successResponse, Context context) {
        Call<FinalCheckResponse> call = RetrofitClient.getDashboardApiInterface().finalCheck(request.getKey(),request.getTxnid(),request.getAmount(),
                request.getProductinfo(),request.getFirstname(),request.getEmail(),"","","","",
                "","","","","","",
                UserSession.getInstance().getLangId(),UserSession.getInstance().getRequestId(),UserSession.getInstance().getUniqueSessionId(),"success");
System.out.println("final");
        System.out.println("Key:"+request.getKey()+"/"+"Txnid:"+request.getTxnid()+"/"+"Amount:"+request.getAmount()+"/"+
                "Productinfo:"+request.getProductinfo()+"/"+"Firstname:"+request.getFirstname()+"/"+"Email:"+request.getEmail()+"/"+"udf1:"+"/"+"udf2:"+"/"+"udf3:"+"/"+"udf4"+"/"+
                "udf5:"+"/"+"udf6:"+"/"+"udf7:"+"/"+"udf8:"+"/"+"udf9:"+"/"+"udf10:"+"/"+
                "LangId:"+ UserSession.getInstance().getLangId()+"/"+"RequestId:"+UserSession.getInstance().getRequestId()+"/"+"UniqueSessionId:"+UserSession.getInstance().getUniqueSessionId()+"test"+request.getStatus());
//
        new RetrofitRequest<>(call, new ResponseListener<FinalCheckResponse>() {
            @Override
            public void onResponse(FinalCheckResponse response, Headers headers) {
                if (null != successResponse) {
                    System.out.println("final result"+successResponse);
                    successResponse.onResponse(response);
                }
            }
            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }
            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(context);
    }
    public void partpayment(String pledgeNo, String amount, SuccessResponse<BaseResponse> successResponse, Activity mActivity) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().GetmPartPayAmountRequest(
                pledgeNo,amount, UserSession.getInstance().getCustomerId(), UserSession.getInstance().getUniqueSessionId(),  UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }
            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(mActivity);
    }

    public void FailedTransaction(PayuInputRequest request, String txnId, SuccessResponse<BaseResponse> successResponse, Activity mActivity) {


        System.out.println("failed"+request);

        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().failedTransaction(request.getPlno(),
                txnId , UserSession.getInstance().getRequestId(),UserSession.getInstance().getUniqueSessionId(), UserSession.getInstance().getLangId()
                );


//        pledgeNo,amount, UserSession.getInstance().getCustomerId(), UserSession.getInstance().getUniqueSessionId(),  UserSession.getInstance().getRequestId(),
//                UserSession.getInstance().getLangId()

        new RetrofitRequest<>(call, new ResponseListener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response, Headers headers) {
                if (null != successResponse) {
                    System.out.println(successResponse);
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(mActivity);


    }


    public void SuccessTransaction(PayuInputRequest request, String txnId, SuccessResponse<TransSuccessResponse> successResponse, Activity mActivity) {

        Call<TransSuccessResponse> call = RetrofitClient.getDashboardApiInterface().transaction(request.getAmount(),UserSession.getInstance().getLangId(),request.getPaymentMethod(),txnId,request.getPlno(),UserSession.getInstance().getRequestId(),UserSession.getInstance().getUniqueSessionId());

        new RetrofitRequest<>(call, new ResponseListener<TransSuccessResponse>() {
            @Override
            public void onResponse(TransSuccessResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }

            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(mActivity);


    }

    public void Repledge(String s, SuccessResponse<repledge> successResponse, Context context) {

        Call<repledge> call = RetrofitClient.getDashboardApiInterface().repledge1(s,
                "1",UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());


        System.out.println("pledgeno:"+s+"/"+"sendOTP:"+
                "1"+"cusid:"+UserSession.getInstance().getCustomerId()+"/"+
                "uniquesessionId:"+ UserSession.getInstance().getUniqueSessionId()+"/"+
                "requestid:"+ UserSession.getInstance().getRequestId()+"/"+
                "langId:"+ UserSession.getInstance().getLangId());
        new RetrofitRequest<>(call, new ResponseListener<repledge>() {
            @Override
            public void onResponse(repledge response, Headers headers) {
                System.out.println("test");
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));
            }

            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue(context);
    }
}
