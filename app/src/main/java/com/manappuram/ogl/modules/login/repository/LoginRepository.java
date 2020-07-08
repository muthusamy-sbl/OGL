package com.manappuram.ogl.modules.login.repository;

import android.content.Context;

import com.manappuram.ogl.base.BaseRepository;
import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.base.Event;
import com.manappuram.ogl.modules.login.model.LoginRequest;
import com.manappuram.ogl.modules.login.model.LoginResponse;
import com.manappuram.ogl.modules.login.model.VersionResponse;
import com.manappuram.ogl.modules.navigation.model.MpinRequest;
import com.manappuram.ogl.modules.register.model.CustomerCheckResponse;
import com.manappuram.ogl.modules.register.model.RegisterRequest;
import com.manappuram.ogl.network.ResponseListener;
import com.manappuram.ogl.network.RetrofitClient;
import com.manappuram.ogl.network.RetrofitRequest;
import com.manappuram.ogl.session.UserSession;

import okhttp3.Headers;
import retrofit2.Call;

public class LoginRepository extends BaseRepository {

    public void loginUser(LoginRequest loginRequest, SuccessResponse successResponse, Context context) {
        Call<LoginResponse> call = RetrofitClient.getAPIInterface().mLogin(loginRequest.getUid(), loginRequest.getPass(), loginRequest.getLangId());

        new RetrofitRequest<>(call, new ResponseListener<LoginResponse>() {
            @Override
            public void onResponse(LoginResponse response, Headers headers) {
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

    public void versionCheck(SuccessResponse successResponse, Context context) {
        Call<VersionResponse> call = RetrofitClient.getAPIInterface().VersionCheck();

        new RetrofitRequest<>(call, new ResponseListener<VersionResponse>() {
            @Override
            public void onResponse(VersionResponse response, Headers headers) {
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

    public void regiterUser(RegisterRequest request, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getAPIInterface().mRegisterCustomer(request.getCustomerId(), request.getMobileNo(),
                request.getEmailId(), request.getPassword(), "EN");

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

    public void customerContactDetails(String custid, SuccessResponse successResponse, Context context) {
        Call<CustomerCheckResponse> call = RetrofitClient.getAPIInterface().mCustomerContactDetails(
                custid,
                "EN");

        new RetrofitRequest<>(call, new ResponseListener<CustomerCheckResponse>() {
            @Override
            public void onResponse(CustomerCheckResponse response, Headers headers) {
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

    public void mSearchCustomerandSendOTP(RegisterRequest request, SuccessResponse successResponse, Context context) {
        Call<CustomerCheckResponse> call = RetrofitClient.getAPIInterface().mSearchCustomerandSendOTP(
                request.getCustomerId(),
                request.getMobileNo(),
                request.getEmailId(),
                "1",
                "EN");

        new RetrofitRequest<>(call, new ResponseListener<CustomerCheckResponse>() {
            @Override
            public void onResponse(CustomerCheckResponse response, Headers headers) {
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

    public void loginWithMPIN(String mpin, String deviceid, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getAPIInterface().mcustomerMPINlogin(mpin,
                deviceid,
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

    public void forgetPassword(String cusId, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getAPIInterface().mChangePassword(cusId,
                "1",
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

    public void otpVerification(String cusId, String otp, SuccessResponse successResponse, Context context) {
        Call<BaseResponse> call = RetrofitClient.getAPIInterface().otpVerification(cusId,
                otp,
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

}
