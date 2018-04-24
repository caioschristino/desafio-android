package com.v2.desafionubank.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.v2.desafionubank.R
import com.v2.desafionubank.adapter.RecyclerViewAdapter
import com.v2.desafionubank.controller.ObserverController
import com.v2.desafionubank.model.NoticeAction
import com.v2.desafionubank.model.ResponseNotice
import com.v2.desafionubank.ui.BaseFragment
import com.v2.desafionubank.ui.view.ActionViewHolder
import kotlinx.android.synthetic.main.notice_fragment.*
import java.util.*

/**
 * Created by CaioSChristino on 18/04/18.
 */

class NoticeFragment : BaseFragment() {
    lateinit var adapter: RecyclerViewAdapter<NoticeAction>
    override val title: String
        get() = ""

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mActions = ArrayList<NoticeAction>()

        recycle_action!!.layoutManager = LinearLayoutManager(activity)
        adapter = object : RecyclerViewAdapter<NoticeAction>(activity.applicationContext) {
            override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
                val mView = LayoutInflater.from(context)
                        .inflate(R.layout.action_item, parent, false)
                return ActionViewHolder(mView)
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

        mSessionController
                .getNotice("https://nu-mobile-hiring.herokuapp.com/")
                .subscribe(object : ObserverController<ResponseNotice>(activity.applicationContext) {
                    override fun onResult(item: ResponseNotice) {
                        title_notice!!.text = item.title
                        body_notice!!.text = Html.fromHtml(item.description)

                        if (null != item.primaryAction) {
                            mActions.add(item.primaryAction)
                        }
                        if (null != item.secondaryAction) {
                            mActions.add(item.secondaryAction)
                        }
                        adapter.addItems(mActions)
                        recycle_action!!.adapter = adapter
                    }
                });
    }

    override fun setContent(): Int {
        return R.layout.notice_fragment
    }

    override fun doInBackFragment() {

    }
}