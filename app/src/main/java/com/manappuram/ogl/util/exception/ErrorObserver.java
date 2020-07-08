package com.manappuram.ogl.util.exception;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

abstract public class ErrorObserver<T> implements SingleObserver<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
