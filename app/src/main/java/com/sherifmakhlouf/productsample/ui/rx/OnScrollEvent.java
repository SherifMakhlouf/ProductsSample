package com.sherifmakhlouf.productsample.ui.rx;

import android.support.v7.widget.RecyclerView;


public class OnScrollEvent {
    private final RecyclerView view;

    OnScrollEvent(RecyclerView view){
        this.view = view;
    }

    public RecyclerView getView() {
        return view;
    }
}
