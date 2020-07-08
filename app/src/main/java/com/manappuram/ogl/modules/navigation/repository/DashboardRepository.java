package com.manappuram.ogl.modules.navigation.repository;

import android.content.Context;
import android.util.Log;

import com.manappuram.ogl.base.BaseRepository;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.modules.dashboardoptions.model.ApplicationFormResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.CalcRequest;
import com.manappuram.ogl.modules.dashboardoptions.model.CalculatorResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.ConfirmationRequest;
import com.manappuram.ogl.modules.dashboardoptions.model.ConfirmationResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.CustAccDetailsResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.ItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanFinalResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.LoanResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeOnlineResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.PledgeResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeItemResponse;
import com.manappuram.ogl.modules.dashboardoptions.model.SchemeResponse;
import com.manappuram.ogl.modules.login.model.LoginRequest;
import com.manappuram.ogl.modules.login.model.LoginResponse;
import com.manappuram.ogl.modules.navigation.model.AccDetailsRequest;
import com.manappuram.ogl.modules.navigation.model.AccDetailsResponse;
import com.manappuram.ogl.modules.navigation.model.ChangePwdRequest;
import com.manappuram.ogl.modules.navigation.model.EditProfileRequest;
import com.manappuram.ogl.modules.navigation.model.OglHistoryRequest;
import com.manappuram.ogl.modules.navigation.model.OglHistoryresponse;
import com.manappuram.ogl.modules.navigation.model.PawnTicketResponse;
import com.manappuram.ogl.modules.navigation.model.ProfileResponse;
import com.manappuram.ogl.modules.navigation.model.ReqEditProfileRequest;
import com.manappuram.ogl.modules.navigation.model.ReqEditProfileResponse;
import com.manappuram.ogl.network.ResponseListener;
import com.manappuram.ogl.network.RetrofitClient;
import com.manappuram.ogl.network.RetrofitRequest;
import com.manappuram.ogl.session.UserSession;
import com.manappuram.ogl.util.Utility;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;

public class DashboardRepository extends BaseRepository {

    public void aboutManappuram(SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().aboutManppuram();

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

        }).enqueue(context);
    }

    public void customerDetails(SuccessResponse successResponse, Context context) {
        Call<ArrayList<AccDetailsResponse>> call = RetrofitClient.getDashboardApiInterface().mAccountDetails(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<ArrayList<AccDetailsResponse>>() {
            @Override
            public void onResponse(ArrayList<AccDetailsResponse> response, Headers headers) {
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

    public void profileDetails(AccDetailsRequest request, SuccessResponse successResponse, Context context) {
        Call<ProfileResponse> call = RetrofitClient.getDashboardApiInterface().mCustomerProfile(request.getCus_id(), request.getUniquesessionid(),
                request.getRequestid(), request.getLangId());

        new RetrofitRequest<>(call, new ResponseListener<ProfileResponse>() {
            @Override
            public void onResponse(ProfileResponse response, Headers headers) {
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

    public void editProfile(EditProfileRequest request, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().mCustomerEditProfile(request.getCusid(),
                request.getUniquesessionid(), request.getMobileno(), request.getEmailid(), request.getChangemode(),
                request.getRequestid(), request.getLangId());

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

        }).enqueue(context);
    }

    public void reqEditProfile(ReqEditProfileRequest request, SuccessResponse successResponse, Context context) {
        Call<ReqEditProfileResponse> call = RetrofitClient.getDashboardApiInterface().mCustomerReqEditProfile(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                request.getOldmobno(), request.getNewmobno(), request.getOldemailid(), request.getNewemailid(), request.getSendOTP(),
                UserSession.getInstance().getRequestId(), UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<ReqEditProfileResponse>() {
            @Override
            public void onResponse(ReqEditProfileResponse response, Headers headers) {
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

    public void profileOTPVerify(String mobile, String otp, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().mCustomerProfileOTPverifciation(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(), mobile, otp,
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        System.out.println(UserSession.getInstance().getCustomerId()+"~"+
                UserSession.getInstance().getUniqueSessionId()+"~"+ mobile+"~"+ otp+"~"+
                UserSession.getInstance().getRequestId()+"~"+
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

        }).enqueue(context);
    }

    public void changePassword(ChangePwdRequest request, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().mUpdatePassword(Utility.encodeString(request.getUid()),
                request.getNewpass(),
                request.getNewpass(), request.getLangId());

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

        }).enqueue(context);
    }

    public void checkOglCustomer(SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().checkOGLCustomer(UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId(),
                UserSession.getInstance().getCustomerId());

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

        }).enqueue(context);
    }


    public void logout(SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().mLogout(Utility.encodeString(UserSession.getInstance().getCustomerId()),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

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

        }).enqueue(context);
    }

    public void GetEligibilityDetails(SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().GetEligibilityDetails("3",
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

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

        }).enqueue(context);
    }

    public void GetCusStatus(SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().GetCusStatus(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

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

        }).enqueue(context);
    }

    public void GetCusAccntDetails(SuccessResponse successResponse, Context context) {
        Call<CustAccDetailsResponse> call = RetrofitClient.getDashboardApiInterface().GetCusAccntDetails(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

        new RetrofitRequest<>(call, new ResponseListener<CustAccDetailsResponse>() {
            @Override
            public void onResponse(CustAccDetailsResponse response, Headers headers) {
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

    public void GetPledeges(SuccessResponse successResponse, Context context) {
        Call<ArrayList<PledgeResponse>> call = RetrofitClient.getDashboardApiInterface().GetPledeges(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

        new RetrofitRequest<>(call, new ResponseListener<ArrayList<PledgeResponse>>() {
            @Override
            public void onResponse(ArrayList<PledgeResponse> response, Headers headers) {
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

    public void GetItemDetails(String pledgeNum, SuccessResponse successResponse, Context context) {
        Call<ArrayList<ItemResponse>> call = RetrofitClient.getDashboardApiInterface().GetItemDetails(pledgeNum,
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

        new RetrofitRequest<>(call, new ResponseListener<ArrayList<ItemResponse>>() {
            @Override
            public void onResponse(ArrayList<ItemResponse> response, Headers headers) {
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

    public void GetSchemeDetails(String pledgeNum, SuccessResponse successResponse, Context context) {
        Call<ArrayList<SchemeResponse>> call = RetrofitClient.getDashboardApiInterface().GetSchemeDetails(pledgeNum,
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

        new RetrofitRequest<>(call, new ResponseListener<ArrayList<SchemeResponse>>() {
            @Override
            public void onResponse(ArrayList<SchemeResponse> response, Headers headers) {
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

    public void GetPledgeDetailsOnline(String pledgeNum, SuccessResponse successResponse, Context context) {
        Call<PledgeOnlineResponse> call = RetrofitClient.getDashboardApiInterface().GetPledgeDetailsOnline(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                pledgeNum,
                UserSession.getInstance().getRequestId(), "EN");

        new RetrofitRequest<>(call, new ResponseListener<PledgeOnlineResponse>() {
            @Override
            public void onResponse(PledgeOnlineResponse response, Headers headers) {
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

    public void GetSelectedSchemeDetails(String schemerow, String pledgeNum, SuccessResponse successResponse, Context context) {
        Call<SchemeItemResponse> call = RetrofitClient.getDashboardApiInterface().GetSelectedSchemeDetails(schemerow,
                pledgeNum,
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

        new RetrofitRequest<>(call, new ResponseListener<SchemeItemResponse>() {
            @Override
            public void onResponse(SchemeItemResponse response, Headers headers) {
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

    public void GetIntrstCalcDetails(CalcRequest calcRequest, SuccessResponse successResponse, Context context) {
        Call<ArrayList<CalculatorResponse>> call = RetrofitClient.getDashboardApiInterface().GetIntrstCalcDetails(calcRequest.getPledgeValue(),
                calcRequest.getIntrstPayDate(),
                calcRequest.getSchemeName(),
                calcRequest.getBranchID(),
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

        new RetrofitRequest<>(call, new ResponseListener<ArrayList<CalculatorResponse>>() {
            @Override
            public void onResponse(ArrayList<CalculatorResponse> response, Headers headers) {
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


    public void getRequiredAmountDetails(String pledgeAmt, String pledgeNo, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().GetRequiredAmtDetails(
                UserSession.getInstance().getCustomerId(),
                pledgeAmt,
                pledgeNo,
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(), "EN");

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

        }).enqueue(context);
    }

    public void GetTermsandConditionsDetails(String lang, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().GetTermsandConditionsDetails(
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                lang);

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

        }).enqueue(context);
    }

    public void GetApplicationFormDetails(String invId, String plno, SuccessResponse successResponse, Context context) {
        Call<ApplicationFormResponse> call = RetrofitClient.getDashboardApiInterface().GetApplicationFormDetails(
                UserSession.getInstance().getCustomerId(),
                invId, plno,
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<ApplicationFormResponse>() {
            @Override
            public void onResponse(ApplicationFormResponse response, Headers headers) {
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

    public void getOglHistory(OglHistoryRequest request,String num, SuccessResponse successResponse, Context context) {
         if(num.equals("1")){
             Call<ArrayList<OglHistoryresponse>> call = RetrofitClient.getDashboardApiInterface().GetOglHistory(
                     UserSession.getInstance().getCustomerId(),
                     UserSession.getInstance().getUniqueSessionId(),
                     request.getPlno(),
                     request.getNewplno(),
                     request.getFromdate(),
                     request.getTodate(),
                     request.getTxnid(),
                     UserSession.getInstance().getRequestId(),
                     UserSession.getInstance().getLangId());

             new RetrofitRequest<>(call, new ResponseListener<ArrayList<OglHistoryresponse>>() {
                 @Override
                 public void onResponse(ArrayList<OglHistoryresponse> response, Headers headers) {
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
         }else{
             Call<ArrayList<OglHistoryresponse>> call = RetrofitClient.getDashboardApiInterface().GetOnlpaymentHistory(
                     UserSession.getInstance().getCustomerId(),
                     UserSession.getInstance().getUniqueSessionId(),
                     request.getPlno(),
                     request.getFromdate(),
                     request.getTodate(),
                     request.getTxnid(),"","",
                     UserSession.getInstance().getRequestId(),
                     UserSession.getInstance().getLangId());

             new RetrofitRequest<>(call, new ResponseListener<ArrayList<OglHistoryresponse>>() {
                 @Override
                 public void onResponse(ArrayList<OglHistoryresponse> response, Headers headers) {
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


    public void verifyOTPforPawnTicket(String plno, String otp, SuccessResponse successResponse, Context context) {
        Call<PawnTicketResponse> call = RetrofitClient.getDashboardApiInterface().verifyOTPforPawnTicket(
                plno,
                UserSession.getInstance().getCustomerId(), otp,
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<PawnTicketResponse>() {
            @Override
            public void onResponse(PawnTicketResponse response, Headers headers) {
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

    public void getPawnTicket(String plno, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().GetPawnTicket(
                plno,
                UserSession.getInstance().getCustomerId(), "1",
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
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

        }).enqueue(context);
    }


    public void MPINrequest(SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().mcustomerMPINrequest(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(), "1",
                UserSession.getInstance().getRequestId(),
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

        }).enqueue(context);
    }

    public void mpinOtpVerification(String otp, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().mcustomerMPINotpVerification(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(), otp,
                UserSession.getInstance().getRequestId(),
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

        }).enqueue(context);
    }

    public void mcustomerMPINgeneration(String deviceId, String mpin, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().mcustomerMPINgeneration(
                UserSession.getInstance().getCustomerId(),
                UserSession.getInstance().getUniqueSessionId(), deviceId, "Android", mpin, "1",
                UserSession.getInstance().getRequestId(),
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

        }).enqueue(context);
    }

    public void mcustomerChangeMPIN(String oldmpin, String newpin, String deviceId, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getDashboardApiInterface().mcustomerChangeMPIN(
                UserSession.getInstance().getCustomerId(),
                oldmpin, newpin, deviceId,
                "EN");

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

        }).enqueue(context);
    }

    public void OglConfirmation(ConfirmationRequest request, SuccessResponse successResponse, Context context) {
        Call<ConfirmationResponse> call = RetrofitClient.getDashboardApiInterface().OglConfirmation(
                request.getPaymethod(), request.getAmount(), request.getPlno(), request.getNewpledgeamnt(),
                UserSession.getInstance().getCustomerId(), "1",
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());

//        System.out.println(  request.getPaymethod(), request.getAmount(), request.getPlno(), request.getNewpledgeamnt(),
//                UserSession.getInstance().getCustomerId(), "1",
//                UserSession.getInstance().getUniqueSessionId(),
//                UserSession.getInstance().getRequestId(),
//                UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<ConfirmationResponse>() {
            @Override
            public void onResponse(ConfirmationResponse response, Headers headers) {
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

    public void GetNewloanDetails(String pledgeNo, String otp, SuccessResponse successResponse, Context context) {
        Call<LoanFinalResponse> call = RetrofitClient.getDashboardApiInterface().GetNewloanDetails(
                pledgeNo,
                UserSession.getInstance().getCustomerId(), otp,
                UserSession.getInstance().getUniqueSessionId(),
                UserSession.getInstance().getRequestId(),
                UserSession.getInstance().getLangId());


        System.out.println( "pledgeno:"+pledgeNo+"/"+
                "cusid:"+UserSession.getInstance().getCustomerId()+"/"+ "otp:"+ otp+"/"+
                "uniquesessionid:"+ UserSession.getInstance().getUniqueSessionId()+"/"+
                "requestid:"+ UserSession.getInstance().getRequestId()+"/"+
                "langId:"+UserSession.getInstance().getLangId());

        new RetrofitRequest<>(call, new ResponseListener<LoanFinalResponse>() {
            @Override
            public void onResponse(LoanFinalResponse response, Headers headers) {
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
