package com.v2.desafionubank.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by CaioSChristino on 07/02/2018.
 *//**/

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<T> mItems = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public RecyclerViewAdapter(Context mContext, List<T> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T item);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = setViewHolder(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindData(holder, mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItems(List<T> items) {
        this.mItems = items;
        this.notifyDataSetChanged();
    }

    public void addItems(T item) {
        this.mItems.add(item);
        this.notifyDataSetChanged();
    }

    public Context getmContext() {
        return mContext;
    }

    public List<T> getmItems() {
        return mItems;
    }
}
