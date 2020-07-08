//package com.manappuram.ogl.util.exception;
//
//import android.arch.lifecycle.MutableLiveData;
//
//import com.mgc.ogl.base.Event;
//import com.mgc.ogl.modules.event.ErrorEvent;
//import com.mgc.ogl.network.Errors;
//import com.mgc.ogl.network.ResponseCodes;
//import com.mgc.ogl.util.datatype_utils.StringUtils;
//
//import java.io.IOException;
//
//import retrofit2.HttpException;
//
//public class ApiErrorHandler {
//
//    public Exception handleException(String status) {
//        return handleException(status, null);
//    }
//
//    public Exception handleException(String status, String message) {
//
//        String[] noDataFound = new String[]{"222", "333", "888", "777"};
//        String[] invalidSession = new String[]{"555", "444", "Session expired", "Invalid session"};
//        return handleException(invalidSession, noDataFound, status, message);
//    }
//
//    public Exception handleException(String[] invalidSession, String[] noDataFound, String status, String message) {
//
//
//        if (StringUtils.isEqual(status, noDataFound)) {
//            NoDataFoundException exception = new NoDataFoundException();
//            exception.message = message;
//            return exception;
//        } else if (StringUtils.isEqual(status, invalidSession)) {
//            return new InvalidSessionException(status);
//        } else {
//            return getDataFailureException(status, message);
//        }
//    }
//
//    public Exception handleException(String invalidRequestCode, String[] invalidSession, String[] noDataFound, String status, String message) {
//
//
//        if (StringUtils.isEqual(status, noDataFound)) {
//            NoDataFoundException exception = new NoDataFoundException();
//            exception.message = message;
//            return exception;
//        } else if(StringUtils.isEqual(status,invalidRequestCode)){
//            InvalidRequestIdException exception = new InvalidRequestIdException();
//            exception.message = message;
//            return exception;
//        }else if (StringUtils.isEqual(status, invalidSession)) {
//            return new InvalidSessionException(status);
//        } else {
//            return getDataFailureException(status, message);
//        }
//    }
//
//    public DataFailureException getDataFailureException(String status, String message) {
//        DataFailureException exception = new DataFailureException(status);
//        exception.message = message;
//        return exception;
//    }
//
//    public void handleError(MutableLiveData<Event> liveData, Throwable e) {
//
//        if (e instanceof InvalidSessionException) {
//            Errors.Error error = new Errors.Error(((InvalidSessionException) e).message, this.getClass().getName());
//            error.setStatusCode(ResponseCodes.SESSION_EXPIRED);
//            error.canRetry = false;
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
//            e.printStackTrace();
//        }
//        if (e instanceof NoLivePledgesException) {
//            Errors.Error error = new Errors.Error("", this.getClass().getName());
//            error.setStatusCode(ResponseCodes.NO_LIVE_PLEDGES);
//            error.canRetry = false;
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
//            e.printStackTrace();
//        } else if (e instanceof NoDataFoundException) {
//            Errors.Error error = new Errors.Error(((NoDataFoundException) e).message, this.getClass().getName());
//            error.setStatusCode(ResponseCodes.NO_DATA_FOUND);
//            error.canRetry = false;
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
//            e.printStackTrace();
//        } else if (e instanceof InvalidRequestIdException) {
//            Errors.Error error = new Errors.Error(((InvalidRequestIdException) e).message, this.getClass().getName());
//            error.setStatusCode(ResponseCodes.INVALID_REQUEST_ID);
//            error.canRetry = false;
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
//            e.printStackTrace();
//        } else if (e instanceof InvalidDataException) {
//            Errors.Error error = new Errors.Error(((InvalidDataException) e).message, this.getClass().getName());
//            error.setStatusCode(String.valueOf(((InvalidDataException) e).code));
//            error.canRetry = false;
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
//            e.printStackTrace();
//        } else if (e instanceof DataFailureException) {
//            Errors.Error error = new Errors.Error(((DataFailureException) e).message, this.getClass().getName());
//            error.setStatusCode(String.valueOf(((DataFailureException) e).code));
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
//            e.printStackTrace();
//        }else if (e instanceof IOException) {
//            Errors.Error error = new Errors.Error(this.getClass().getName());
//            error.setStatusCode(ResponseCodes.NETWORK_FAILURE);
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
//            e.printStackTrace();
//        } else if (e instanceof HttpException && ((HttpException) e).code() == 500) {
//            Errors.Error error = new Errors.Error(this.getClass().getName());
//            error.setStatusCode(ResponseCodes.NETWORK_FAILURE);
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
//            e.printStackTrace();
//        }
//        else {
//            Errors.Error error = new Errors.Error(this.getClass().getName());
//            error.setStatusCode(ResponseCodes.SERVER_NOT_REACHABLE);
//            ErrorEvent event = new ErrorEvent(error);
//            liveData.setValue(event);
////            e.printStackTrace();
//        }
//    }
//
//}
