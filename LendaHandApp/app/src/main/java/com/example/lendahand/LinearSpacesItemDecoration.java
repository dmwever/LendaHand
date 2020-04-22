package com.example.lendahand;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//https://www.gsrikar.com/2019/03/add-spacing-to-recycler-view-linear.html
public class LinearSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public LinearSpacesItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter() != null && parent.getChildLayoutPosition(view) ==
                parent.getAdapter().getItemCount() - 1) {
            // Add bottom margin only for the last item
            outRect.bottom = spacing;
        }
    }
}