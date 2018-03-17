package com.example.richsoap.simplelist;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by richsoap on 18-3-15.
 */

public class RecyclerTouchHelperCallback extends ItemTouchHelper.Callback {
    private OnItemTouchCallbackListener onItemTouchCallbackListenner;
    private boolean canDrag = true;
    private boolean canSwipe = true;
    private static final String TAG = "RecyclerTouchHelperCall";

    public RecyclerTouchHelperCallback(OnItemTouchCallbackListener listener) {
        onItemTouchCallbackListenner = listener;
    }

    public void setOnItemTouchCallbackListenner(OnItemTouchCallbackListener listener) {
        this.onItemTouchCallbackListenner = listener;
    }

    public void setLongPressDragEnabled(boolean _canDrag) {
        canDrag = _canDrag;
    }

    public void setItemViewSwipeEnabled(boolean _canSwipe) {
        canDrag = _canSwipe;
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
        else {
            LinearLayoutManager linearManager = (LinearLayoutManager) manager;
            int orientation = linearManager.getOrientation();
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                Log.d(TAG, "getMovementFlags: HORIZONTAL");
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            else {
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(dragFlag, swipeFlag);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder srcViewHolder, RecyclerView.ViewHolder targetViewHolder) {
        Log.d(TAG, "onMove: Move");
        if(onItemTouchCallbackListenner != null) {
            return onItemTouchCallbackListenner.onMove(srcViewHolder.getAdapterPosition(), targetViewHolder.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d(TAG, "onSwiped: Swipe");
        if(onItemTouchCallbackListenner != null) {
            onItemTouchCallbackListenner.onSwipe(viewHolder.getAdapterPosition(), direction);
        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int action) {
        if(ItemTouchHelper.ACTION_STATE_DRAG == action) {
            Log.d(TAG, "onSelectedChanged: drag");   
        }
        else if(ItemTouchHelper.ACTION_STATE_SWIPE == action) {
            Log.d(TAG, "onSelectedChanged: swipe");
        }
    }

    public interface OnItemTouchCallbackListener {
        void onSwipe(int adapterPosition, int direction);
        boolean onMove(int srcPosition, int targetPosition);

    }
}
