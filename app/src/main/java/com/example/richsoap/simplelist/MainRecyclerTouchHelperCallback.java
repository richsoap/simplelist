package com.example.richsoap.simplelist;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by richsoap on 18-3-15.
 */

public class MainRecyclerTouchHelperCallback extends ItemTouchHelper.Callback {
    private OnItemTouchCallbackListener onItemTouchCallbackListenner;
    private boolean canDrag = true;
    private boolean canSwipe = true;

    public MainRecyclerTouchHelperCallback(OnItemTouchCallbackListener listener) {
        onItemTouchCallbackListenner = listener;
    }

    public void setOnItemTouchCallbackListenner(OnItemTouchCallbackListener listener) {
        this.onItemTouchCallbackListenner = listener;
    }

    public void setDragEnable(boolean _canDrag) {
        canDrag = _canDrag;
    }

    public void setSwipeEnable(boolean _canSwiper) {
        canSwipe = _canSwiper;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return canDrag;
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        return canSwipe;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = 0;
        int swipeFlag = 0;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
            swipeFlag = 0;

            return makeMovementFlags(dragFlag, swipeFlag);
        }
        else if(manager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) manager;
            int orientation = linearManager.getOrientation();

            if (orientation == LinearLayoutManager.HORIZONTAL) {
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            else {
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(dragFlag, swipeFlag);
        }
        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder srcViewHolder, RecyclerView.ViewHolder targetViewHolder) {
        if(onItemTouchCallbackListenner != null) {
            return onItemTouchCallbackListenner.onMove(srcViewHolder.getAdapterPosition(), targetViewHolder.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(onItemTouchCallbackListenner != null) {
            onItemTouchCallbackListenner.onSwipe(viewHolder.getAdapterPosition(), direction);
        }
    }

    public interface OnItemTouchCallbackListener {
        void onSwipe(int adapterPosition, int direction);
        boolean onMove(int srcPosition, int targetPosition);
    }
}
