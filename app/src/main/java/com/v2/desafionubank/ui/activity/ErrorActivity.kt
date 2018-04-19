package com.v2.desafionubank.ui.activity

import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.v2.desafionubank.R
import com.v2.desafionubank.ui.BaseMain
import com.v2.desafionubank.ui.NavigationManager

/**
 * Created by csanchez on 19/04/2018.
 */

class ErrorActivity : BaseMain() {
    @BindView(R.id.about_error)
    internal var mAboutError: TextView? = null

    override fun layoutResID(): Int {
        return R.layout.activity_erro
    }

    override fun initViews() {
        val b = intent.extras
        val error = b!!.getString(ERROR_MESSAGE)
        mAboutError!!.text = error
    }

    @OnClick(R.id.close_btn)
    fun onClickClose() {
        onBackPressed()
    }

    override fun setBackNavigationListener(): NavigationManager.BackFragmentNavigationListener {
        return object : NavigationManager.BackFragmentNavigationListener {
            override fun doInFinishBackNavigation() {
                finish()
            }
        }
    }
}