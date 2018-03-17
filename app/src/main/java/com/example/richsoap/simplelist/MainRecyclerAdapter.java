package com.example.richsoap.simplelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.richsoap.backgroundData.DataManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by richsoap on 18-3-14.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> implements RecyclerTouchHelperAdapter {
    private List<UUID> mUUIDList;

    public MainRecyclerAdapter(List<UUID> idList) {
        mUUIDList = idList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mainTitle;
        TextView subTitle;

        public ViewHolder(View view) {
            super(view);
            mainTitle = (TextView) view.findViewById(R.id.main_cardview_title);
            subTitle = (TextView) view.findViewById(R.id.main_cardview_subtitle);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycle_cardview,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UUID tempID = mUUIDList.get(position);
        holder.mainTitle.setText(DataManager.getMainTitleByUUID(tempID));
        holder.subTitle.setText(DataManager.getSubTitleByUUID(tempID));
    }

    @Override
    public int getItemCount() {
        return mUUIDList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mUUIDList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDelete(int location) {
        mUUIDList.remove(location);
        notifyItemRemoved(location);
    }

    @Override
    public void onItemAdd(List list) {
        mUUIDList.addAll(list);
        notifyItemRangeInserted(mUUIDList.size() - list.size(),list.size());
    }
}
