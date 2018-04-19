package com.v2.desafionubank.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.v2.desafionubank.R
import com.v2.desafionubank.adapter.RecyclerViewAdapter
import com.v2.desafionubank.controller.ObserverController
import com.v2.desafionubank.model.ReasonDetails
import com.v2.desafionubank.model.ResponseChargeback
import com.v2.desafionubank.model.ResponsePost
import com.v2.desafionubank.ui.BaseFragment
import com.v2.desafionubank.ui.view.ReasonDetailsViewHolder
import com.v2.desafionubank.ui.view.ViewDialog

/**
 * Created by CaioSChristino on 18/04/18.
 */

class ChargebackFragment : BaseFragment() {
    override val title: String
        get() = ""

    @BindView(R.id.chargeback_title)
    internal var mTitle: TextView? = null
    @BindView(R.id.padlock_image)
    internal var mPadLockImage: ImageView? = null
    @BindView(R.id.padlock_lock)
    internal var mPadLockText: TextView? = null
    @BindView(R.id.recycle_details)
    internal var mRecyclerView: RecyclerView? = null
    @BindView(R.id.about_block)
    internal var mAboutBlockEdit: EditText? = null
    @BindView(R.id.container_block)
    internal var mContainerBlock: RelativeLayout? = null


    internal var adapter: RecyclerViewAdapter<ReasonDetails> = object : RecyclerViewAdapter<ReasonDetails>(activity) {
        override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(activity)
                    .inflate(R.layout.reason_item, parent, false)
            return ReasonDetailsViewHolder(view)
        }

        override fun onBindData(holder: RecyclerView.ViewHolder, item: ReasonDetails) {
            if (holder is ReasonDetailsViewHolder) {
                holder.setItem(item)
                holder.setText(item.title)
            }
        }
    }

    override fun initViews() {
        mRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        mSessionController?.chargeback?.subscribe(object : ObserverController<ResponseChargeback>(activity.applicationContext) {
            override fun onResult(item: ResponseChargeback) {
                if (item != null) {
                    mContainerBlock!!.visibility = View.VISIBLE
                    mAboutBlockEdit!!.hint = Html.fromHtml(item.hint)
                    mTitle!!.text = item.title
                    mPadLockImage!!.setImageDrawable(resources.getDrawable(R.drawable.ic_chargeback_lock))
                    adapter.addItems(item.details!!)
                    mRecyclerView!!.adapter = adapter
                }
            }
        })
    }


    override fun setContent(): Int {
        return R.layout.chargeback_fragment
    }

    override fun doInBackFragment() {

    }

    @OnClick(R.id.contest_btn)
    fun onClickContest() {

    }

    @OnClick(R.id.cancel_btn)
    fun onClickCancel() {
        popSelf()
    }

    private fun blockUnblockCard(block: Boolean) {
        mSessionController?.blockUnblockCard(block)?.subscribe(object : ObserverController<ResponsePost>(activity.applicationContext) {
            override fun onResult(item: ResponsePost) {
                if (item.isBlock) {
                    mPadLockText!!.text = getString(R.string.text_lock_on)
                    mPadLockImage!!.setImageDrawable(resources.getDrawable(R.drawable.ic_chargeback_lock))
                } else {
                    mPadLockText!!.text = getString(R.string.text_lock_off)
                    mPadLockImage!!.setImageDrawable(resources.getDrawable(R.drawable.ic_chargeback_unlock))
                }
            }

            override fun ignoreLoader(): Boolean {
                return true
            }
        })
    }

    @OnClick(R.id.padlock_image)
    fun padlockClick() {
        var block = true
        if (mPadLockImage!!.drawable.constantState == resources.getDrawable(R.drawable.ic_chargeback_lock).constantState) {
            block = false
        }

        blockUnblockCard(block)
    }


    @OnClick(R.id.contest_btn)
    fun sendContest() {
        mSessionController?.sendContest(mAboutBlockEdit!!.text.toString(), adapter.getmItems())?.subscribe(object : ObserverController<ResponsePost>(activity.applicationContext) {
            override fun onResult(item: ResponsePost) {
                ViewDialog().showDialog(activity).run {
                    popSelf()
                }
            }
        })
        hideKeyboard()
    }
}
