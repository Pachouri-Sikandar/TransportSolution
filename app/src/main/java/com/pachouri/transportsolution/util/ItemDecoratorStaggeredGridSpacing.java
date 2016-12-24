package com.pachouri.transportsolution.util;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ankit on 12/25/16.
 */

public class ItemDecoratorStaggeredGridSpacing extends RecyclerView.ItemDecoration {

    private int mSizeGridSpacingPx;
    private int mPaddingPx;

    public ItemDecoratorStaggeredGridSpacing(int gridSpacingPx, int paddingPx) {
        mSizeGridSpacingPx = gridSpacingPx;
        mPaddingPx = paddingPx;
    }

    public ItemDecoratorStaggeredGridSpacing(int gridSpacingPx) {
        mSizeGridSpacingPx = gridSpacingPx;
        mPaddingPx = 0;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int itemPosition = parent.getChildAdapterPosition(view);

        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanIndex = lp.getSpanIndex();
        outRect.bottom = mSizeGridSpacingPx;
        if (itemPosition < 2) {
            outRect.top = mPaddingPx;
        } else {
            outRect.top = mSizeGridSpacingPx;
        }
        if (spanIndex == 0) {
            outRect.left = 0;
            outRect.right = mSizeGridSpacingPx;
        } else {
            outRect.left = mSizeGridSpacingPx;
            outRect.right = 0;
        }
    }
}
