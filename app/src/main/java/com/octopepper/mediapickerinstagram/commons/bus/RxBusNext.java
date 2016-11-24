package com.octopepper.mediapickerinstagram.commons.bus;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBusNext {

    private static RxBusNext sInstance = null;

    private RxBusNext() {
    }

    public static RxBusNext getInstance() {
        if (sInstance == null) {
            synchronized (RxBusNext.class) {
                if (sInstance == null) {
                    sInstance = new RxBusNext();
                }
            }
        }
        return sInstance;
    }

    private Subject<Object, Object> _bus = new SerializedSubject<>(BehaviorSubject.create());

    public void send(@NonNull Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }

    public void reset() {
        _bus = new SerializedSubject<>(BehaviorSubject.create());
    }

}
