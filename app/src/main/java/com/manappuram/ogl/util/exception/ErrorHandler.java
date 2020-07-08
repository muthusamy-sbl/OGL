//package com.manappuram.ogl.util.exception;
//
//import android.content.Context;
//import android.view.View;
//
//
//import io.reactivex.Single;
//import io.reactivex.SingleObserver;
//
//public class ErrorHandler {
//
//    public static Integer RE_LOGIN = 1;
//    public static Integer INVALID_REQUEST_ID = 2;
//    public static Integer RETRY = 2;
//    public static Integer DISMISS = 3;
//
//    Context context;
//
//    public ErrorHandler(Context context) {
//        this.context = context;
//    }
//
//    public Single<Integer> handle(Event event, View rootView) {
//
//        Single single = new Single<Integer>() {
//            @Override
//            protected void subscribeActual(SingleObserver observer) {
//
//                Errors.Error error = (Errors.Error) event.getContent();
//                String errorMsg = error.getMessage();//getMessage(error.getStatusCode());
//
//                if(StringUtils.isNullOrEmpty(errorMsg))
//                    errorMsg = getMessage(error.getStatusCode());
//
//                int action;
//                if (error.getMessage() != null) {
//                    errorMsg = error.getMessage();
//                }
//                if (ResponseCodes.isSessionExpired(error.getStatusCode())) {
//                    action = R.string.login;
//                } else if (ResponseCodes.isInvalidRequestId(error.getStatusCode())) {
//                    action = R.string.invalid_request_id;
//                } else {
//                    if (error.canRetry)
//                        action = R.string.retry;
//                    else
//                        action = R.string.dismiss;
//                }
//
//                if (action == R.string.login ) {
//                    observer.onSuccess(RE_LOGIN);
//                } else if(action == R.string.invalid_request_id){
//                    observer.onSuccess(INVALID_REQUEST_ID);
//                }else {
//                    Utility.showSnackbarWithAction(action, rootView, errorMsg, v -> {
//                        if (action == R.string.login) {
//                            observer.onSuccess(RE_LOGIN);
//                        } else if (action == R.string.dismiss) {
//
//                        } else {
//                            observer.onSuccess(RETRY);
//                        }
//                    });
//                }
//            }
//        };
//        return single.subscribeOn(SchedulersUtil.ui());
//    }
//
//
//    public String getMessage(String errorCode) {
//        String errorMsg = context.getString(R.string.something_went_wrong);
//        switch (errorCode) {
////            case ResponseCodes.SESSION_EXPIRED:
////                errorMsg = context.getString(R.string.invalid_session);
////                break;
////            case ResponseCodes.NO_DATA_FOUND:
////                errorMsg = context.getString(R.string.no_data);
////                break;
//            case ResponseCodes.NETWORK_FAILURE:
//                errorMsg = context.getString(R.string.network_failure);
//                break;
//            case ResponseCodes.SERVER_NOT_REACHABLE:
//                errorMsg = context.getString(R.string.network_failure);
//                break;
//        }
//        return errorMsg;
//    }
//
//
//}
