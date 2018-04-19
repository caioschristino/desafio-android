package com.v2.desafionubank.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import com.v2.desafionubank.R
import com.v2.desafionubank.adapter.RecyclerViewAdapter
import com.v2.desafionubank.controller.ObserverController
import com.v2.desafionubank.model.NoticeAction
import com.v2.desafionubank.model.ResponseNotice
import com.v2.desafionubank.ui.BaseFragment
import com.v2.desafionubank.ui.view.ActionViewHolder
import java.util.ArrayList

/**
 * Created by CaioSChristino on 18/04/18.
 */

class NoticeFragment : BaseFragment() {
    override val title: String
        get() = ""

    @BindView(R.id.title_notice)
    internal var mTitle: TextView? = null
    @BindView(R.id.body_notice)
    internal var mBody: TextView? = null
    @BindView(R.id.recycle_action)
    internal var mRecyclerView: RecyclerView? = null

    internal var adapter: RecyclerViewAdapter<NoticeAction> = object : RecyclerViewAdapter<NoticeAction>(activity) {
        override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(activity)
                    .inflate(R.layout.action_item, parent, false)
            return ActionViewHolder(view)
        }

        override fun onBindData(holder: RecyclerView.ViewHolder, item: NoticeAction) {
            if (holder is ActionViewHolder) {

                holder.setTitle(item)
                holder.setOnclick(View.OnClickListener {
                    when (item.action) {
                        "continue" -> pushFragment(ChargebackFragment())
                        "cancel" -> popSelf()
                    }
                })
            }
        }
    }

    override fun initViews() {
        mRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        val mActions = ArrayList<NoticeAction>()
        mSessionController?.getNotice("https://nu-mobile-hiring.herokuapp.com/")?.subscribe(object : ObserverController<ResponseNotice>(activity.applicationContext) {
            override fun onResult(item: ResponseNotice) {
                mTitle!!.text = item.title
                mBody!!.text = Html.fromHtml(item.description)

                if (null != item.primaryAction) {
                    mActions.add(item.primaryAction)
                }
                if (null != item.secondaryAction) {
                    mActions.add(item.secondaryAction)
                }
                adapter.addItems(mActions)
                mRecyclerView!!.adapter = adapter
            }
        })
    }

    override fun setContent(): Int {
        return R.layout.notice_fragment
    }

    override fun doInBackFragment() {

    }
}