package com.example.mon_pc.adapterviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by MON-PC on 26/01/2017.
 */
public class SpacingDecoration extends RecyclerView.ItemDecoration {
    public static final int POLICY_INCLUDE_EDGES_X = 1;
    public static final int POLICY_INCLUDE_EDGES_Y = 2;
    public static final int POLICY_INCLUDE_EDGES_ALL = POLICY_INCLUDE_EDGES_X | POLICY_INCLUDE_EDGES_Y;

    protected int spacingPolicy;
    protected int spacingL;
    protected int spacingR;
    protected int spacingT;
    protected int spacingB;

    public SpacingDecoration(Context context, int spacing, int policy) {
        this(context, spacing, spacing, policy);
    }

    public SpacingDecoration(Context context, int spacingX, int spacingY, int policy) {
        this(context, spacingX, spacingX, spacingY, spacingY, policy);
    }

    public SpacingDecoration(Context context, int spacingL, int spacingR, int spacingT, int spacingB, int policy) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        this.spacingPolicy = policy;
        this.spacingL = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacingL, metrics);
        this.spacingR = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacingR, metrics);
        this.spacingT = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacingT, metrics);
        this.spacingB = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacingB, metrics);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layout = parent.getLayoutManager();

        outRect.left = this.spacingL;
        outRect.right = this.spacingR;
        outRect.top = this.spacingT;
        outRect.bottom = this.spacingB;

        // does not support `layout instanceof StaggeredGridLayoutManager` yet
        if (layout instanceof GridLayoutManager)
            this.offsetGrid(outRect, view, parent);
        else if (layout instanceof LinearLayoutManager)
            this.offsetLinear(outRect, view, parent);
    }

    protected void offsetLinear(Rect outRect, View view, RecyclerView parent) {
        LinearLayoutManager layout = (LinearLayoutManager)parent.getLayoutManager();

        int orientation = layout.getOrientation(),
                position = parent.getChildAdapterPosition(view);

        boolean includeEdgesX = (this.spacingPolicy & SpacingDecoration.POLICY_INCLUDE_EDGES_X) == SpacingDecoration.POLICY_INCLUDE_EDGES_X,
                includeEdgesY = (this.spacingPolicy & SpacingDecoration.POLICY_INCLUDE_EDGES_Y) == SpacingDecoration.POLICY_INCLUDE_EDGES_Y;

        if (orientation == LinearLayoutManager.VERTICAL) {
            if (!includeEdgesX)
                outRect.left = outRect.right = 0;

            if (position == 0 && !includeEdgesY)
                outRect.top = 0;

            if (position == parent.getAdapter().getItemCount() - 1 && !includeEdgesY)
                outRect.bottom = 0;
        }
        else {
            if (!includeEdgesY)
                outRect.top = outRect.bottom = 0;

            if (position == 0 && !includeEdgesX)
                outRect.left = 0;

            if (position == parent.getAdapter().getItemCount() - 1 && !includeEdgesX)
                outRect.right = 0;
        }
    }

    protected void offsetGrid(Rect outRect, View view, RecyclerView parent) {
        GridLayoutManager layout = (GridLayoutManager)parent.getLayoutManager();

        int orientation = layout.getOrientation(),
                spanCount = layout.getSpanCount(),
                rowsCount = parent.getAdapter().getItemCount() / spanCount,
                position = parent.getChildAdapterPosition(view);

        boolean includeEdgesX = (this.spacingPolicy & SpacingDecoration.POLICY_INCLUDE_EDGES_X) == SpacingDecoration.POLICY_INCLUDE_EDGES_X,
                includeEdgesY = (this.spacingPolicy & SpacingDecoration.POLICY_INCLUDE_EDGES_Y) == SpacingDecoration.POLICY_INCLUDE_EDGES_Y;

        if (orientation == GridLayoutManager.VERTICAL) {
            if (position % spanCount == 0 && !includeEdgesX) // "left" edge
                outRect.left = 0;

            if (position % spanCount == spanCount - 1 && !includeEdgesX) // "right" edge
                outRect.right = 0;

            if (position / spanCount == 0 && !includeEdgesY) // "top" edge
                outRect.top = 0;

            if (position / spanCount == rowsCount && !includeEdgesY) // "bottom" edge
                outRect.bottom = 0;
        }
        else {
            if (position % spanCount == 0 && !includeEdgesY) // "top" edge
                outRect.top = 0;

            if (position % spanCount == spanCount - 1 && !includeEdgesY) // "bottom" edge
                outRect.bottom = 0;

            if (position / spanCount == 0 && !includeEdgesX) // "left" edge
                outRect.left = 0;

            if (position / spanCount == rowsCount && !includeEdgesX) // "right" edge
                outRect.right = 0;
        }
    }
}
