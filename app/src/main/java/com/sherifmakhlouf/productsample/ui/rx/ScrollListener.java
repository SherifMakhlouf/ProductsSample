package com.sherifmakhlouf.productsample.ui.rx;


import android.support.v7.widget.RecyclerView;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.internal.Assertions;

public class ScrollListener implements Observable.OnSubscribe<OnScrollEvent> {
    private final RecyclerView view;

    public ScrollListener(RecyclerView view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super OnScrollEvent> observer) {
        Assertions.assertUiThread();
        final RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    observer.onCompleted();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.v("Rx", "onScrolled");
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();

                observer.onNext(new OnScrollEvent(view));
            }
        };
    //TODO consider using addScrollListener instead
        view.setOnScrollListener(listener);

    }
}
