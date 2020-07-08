package com.manappuram.ogl.util;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulersUtil {

    public static Scheduler io() {
        return Schedulers.single();
    }

    public static Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
