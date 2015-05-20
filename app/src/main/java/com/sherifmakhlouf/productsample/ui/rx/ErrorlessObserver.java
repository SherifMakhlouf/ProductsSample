package com.sherifmakhlouf.productsample.ui.rx;

import rx.Observer;

/**
 * This is a default observer that has an empty OnError implementation to clean up the code
 */
public abstract class ErrorlessObserver<T> implements Observer<T>{

    @Override
    public void onError(Throwable e) {

    }
}
