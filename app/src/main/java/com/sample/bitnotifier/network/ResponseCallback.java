package com.sample.bitnotifier.network;

import io.reactivex.observers.DisposableObserver;

public abstract class ResponseCallback<T> extends DisposableObserver<T> {

    public abstract void onResponse(T t);

    public abstract void onFailure(Throwable t);

    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onComplete() {
        dispose();
    }
}

