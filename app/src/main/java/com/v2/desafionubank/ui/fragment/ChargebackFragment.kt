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
import com.v2.desafionubank.model.ReasonDetails
import com.v2.desafionubank.model.ResponseChargeback
import com.v2.desafionubank.model.ResponsePost
import com.v2.desafionubank.ui.BaseFragment
import com.v2.desafionubank.ui.view.ReasonDetailsViewHolder
import com.v2.desafionubank.ui.view.ViewDialog
import kotlinx.android.synthetic.main.chargeback_fragment.*

/**
 * Created by CaioSChristino on 18/04/18.
 */

class ChargebackFragment : BaseFragment() {
    override val title: String
        get() = ""

    lateinit var adapter: RecyclerViewAdapter<ReasonDetails>

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycle_details!!.layoutManager = LinearLayoutManager(activity)
        adapter = object : RecyclerViewAdapter<ReasonDetails>(activity.applicationContext) {
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

        mSessionController
                ?.getChargeback()
                ?.subscribe(object : ObserverController<ResponseChargeback>(activity.applicationContext) {
                    override fun onResult(item: ResponseChargeback) {
                        if (item != null) {
                            container_block.visibility = View.VISIBLE
                            about_block.hint = Html.fromHtml(item.hint)
                            chargeback_title.text = item.title
                            padlock_image.setImageDrawable(resources.getDrawable(R.drawable.ic_chargeback_lock))
                            adapter.addItems(item.details!!)
                            recycle_details.adapter = adapter
                        }
                    }
                })

        cancel_btn.setOnClickListener {
            popSelf()
        }

        padlock_image.setOnClickListener {
            var block = true
            if (padlock_image.drawable.constantState == resources.getDrawable(R.drawable.ic_chargeback_lock).constantState) {
                block = false
            }

            blockUnblockCard(block)
        }

        contest_btn.setOnClickListener {
            mSessionController
                    ?.sendContest(about_block.text.toString(), adapter.getmItems())
                    ?.subscribe(object : ObserverController<ResponsePost>(activity.applicationContext) {
                        override fun onResult(item: ResponsePost) {
                            if (item.status.equals("O4k")) {
                                ViewDialog().showDialog(activity, listener = object : ViewDialog.ViewDialogListener {
                                    override fun onDismiss() {
                                        popSelf()
                                    }
                                })
                            } else {
                                sendError(getString(R.string.error_text))
                            }
                        }
                    })
            hideKeyboard()
        }
    }

    override fun setContent(): Int {
        return R.layout.chargeback_fragment
    }

    override fun doInBackFragment() {

    }

    private fun blockUnblockCard(block: Boolean) {
        mSessionController
                ?.blockUnblockCard(block)
                ?.subscribe(object : ObserverController<ResponsePost>(activity.applicationContext) {
                    override fun onResult(item: ResponsePost) {
                        if (item.isBlock) {
                            padlock_lock.text = getString(R.string.text_lock_on)
                            padlock_image.setImageDrawable(resources.getDrawable(R.drawable.ic_chargeback_lock))
                        } else {
                            padlock_lock.text = getString(R.string.text_lock_off)
                            padlock_image.setImageDrawable(resources.getDrawable(R.drawable.ic_chargeback_unlock))
                        }
                    }

                    override fun ignoreLoader(): Boolean {
                        return true
                    }
                })
    }
}
