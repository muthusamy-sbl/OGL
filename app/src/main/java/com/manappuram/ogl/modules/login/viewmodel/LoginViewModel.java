package com.manappuram.ogl.modules.login.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manappuram.ogl.base.BaseResponse;
import com.manappuram.ogl.modules.login.model.LoginRequest;
import com.manappuram.ogl.modules.login.model.LoginResponse;
import com.manappuram.ogl.modules.login.model.VersionResponse;
import com.manappuram.ogl.modules.login.repository.LoginRepository;
import com.manappuram.ogl.modules.navigation.model.MpinRequest;
import com.manappuram.ogl.modules.register.model.CustomerCheckResponse;
import com.manappuram.ogl.modules.register.model.RegisterRequest;

public class LoginViewModel extends ViewModel {
    protected LoginRepository loginRepository;
    private MutableLiveData<LoginResponse> loginResponseMutable;
    private MutableLiveData<BaseResponse> registerMutableLiveData;
    private MutableLiveData<BaseResponse> forgotMutableLiveData;
    private MutableLiveData<BaseResponse> otpVerifiedMutableLiveData;
    private MutableLiveData<VersionResponse> versionMutableLiveData;
    private MutableLiveData<CustomerCheckResponse> customerCheckMutableLiveData;
    private MutableLiveData<BaseResponse> registerOtpMutableLiveData;
    public LoginRequest loginRequest;

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public LoginViewModel() {
        loginRepository = new LoginRepository();
        loginResponseMutable = new MutableLiveData<>();
        registerMutableLiveData = new MutableLiveData<>();
        loginRequest = new LoginRequest();
        forgotMutableLiveData = new MutableLiveData<>();
        otpVerifiedMutableLiveData = new MutableLiveData<>();
        versionMutableLiveData = new MutableLiveData<>();
        customerCheckMutableLiveData = new MutableLiveData<>();
        registerOtpMutableLiveData = new MutableLiveData<>();
    }


    public void login(LoginRequest loginRequest, Context context) {
        loginRepository.loginUser(loginRequest,
                (LoginRepository.SuccessResponse<LoginResponse>) loginResponse -> {
                    loginResponseMutable.setValue(loginResponse);
                }, context);
    }

    public void versionCheck(Context context) {
        loginRepository.versionCheck(
                (LoginRepository.SuccessResponse<VersionResponse>) versionResponse -> {
                    versionMutableLiveData.setValue(versionResponse);
                }, context);
    }


    public void registerUser(RegisterRequest request, Context context) {
        loginRepository.regiterUser(request,
                (LoginRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    registerMutableLiveData.setValue(baseResponse);
                }, context);
    }


    public void customerCheck(String cusid, Context context) {
        loginRepository.customerContactDetails(cusid,
                (LoginRepository.SuccessResponse<CustomerCheckResponse>) customerCheckResponse -> {
                    customerCheckMutableLiveData.setValue(customerCheckResponse);
                }, context);
    }


    public void mSearchCustomerandSendOTP(RegisterRequest request, Context context) {
        loginRepository.mSearchCustomerandSendOTP(request,
                (LoginRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    registerOtpMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void loginWithMPIN(String mpin, String deviceid,Context context) {

        Log.d("deviceid-->", mpin);
        Log.d("deviceid-->", deviceid);

        loginRepository.loginWithMPIN(mpin, deviceid,
                (LoginRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    registerMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void forgetPassword(String cusId,Context context) {
        loginRepository.forgetPassword(cusId,
                (LoginRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    forgotMutableLiveData.setValue(baseResponse);
                }, context);
    }

    public void otpVerification(String cusId, String otp,Context context) {
        loginRepository.otpVerification(cusId, otp,
                (LoginRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    otpVerifiedMutableLiveData.setValue(baseResponse);
                }, context);
    }


    public LoginRepository getLoginRepository() {
        return loginRepository;
    }

    public MutableLiveData<LoginResponse> getLoginResponseMutable() {
        return loginResponseMutable;
    }

    public MutableLiveData<BaseResponse> getRegisterMutableLiveData() {
        return registerMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getForgotMutableLiveData() {
        return forgotMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getOtpVerifiedMutableLiveData() {
        return otpVerifiedMutableLiveData;
    }

    public MutableLiveData<VersionResponse> getVersionMutableLiveData() {
        return versionMutableLiveData;
    }

    public MutableLiveData<CustomerCheckResponse> getCustomerCheckMutableLiveData() {
        return customerCheckMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getRegisterOtpMutableLiveData() {
        return registerOtpMutableLiveData;
    }
}
