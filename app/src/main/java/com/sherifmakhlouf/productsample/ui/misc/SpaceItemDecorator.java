package com.sherifmakhlouf.productsample.ui.misc;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class SpaceItemDecorator extends RecyclerView.ItemDecoration {

    private final int space;

    public SpaceItemDecorator(int space) {

        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.top = space;
        outRect.left = space ;
        outRect.right = space ;
        outRect.bottom = space ;

    }
}
