package com.manappuram.ogl.network;
import com.manappuram.ogl.base.BaseResponse;

import okhttp3.Headers;


public interface ResponseListener<T> {

    void onResponse(T response, Headers headers);

    void onError(int status, BaseResponse errors);

    void onFailure(Throwable throwable);
}
