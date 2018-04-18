package com.v2.desafionubank.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.*

/**
 * Created by CaioSChristino on 18/04/18.
 */

abstract class RecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var mContext: Context? = null
    private var mItems: MutableList<T> = ArrayList()

    constructor(mContext: Context) {
        this.mContext = mContext
    }

    constructor(mContext: Context, mItems: MutableList<T>) {
        this.mContext = mContext
        this.mItems = mItems
    }

    abstract fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun onBindData(holder: RecyclerView.ViewHolder, item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return setViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindData(holder, mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun addItems(items: MutableList<T>) {
        this.mItems = items
        this.notifyDataSetChanged()
    }

    fun addItems(item: T) {
        this.mItems.add(item)
        this.notifyDataSetChanged()
    }

    fun getmContext(): Context? {
        return mContext
    }

    fun getmItems(): List<T> {
        return mItems
    }
}