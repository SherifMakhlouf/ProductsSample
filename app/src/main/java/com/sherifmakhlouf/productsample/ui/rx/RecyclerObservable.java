package com.sherifmakhlouf.productsample.ui.rx;

import android.support.v7.widget.RecyclerView;

import rx.Observable;

public final class RecyclerObservable {

    private RecyclerObservable(){

    }

    public static Observable<OnScrollEvent> scrolling(final RecyclerView view) {
        return  Observable.create(new ScrollListener(view));
    }
}
