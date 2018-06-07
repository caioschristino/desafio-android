package com.v2.desafio.ui.activity

import com.v2.desafio.R
import com.v2.desafio.ui.BaseMain
import com.v2.desafio.ui.NavigationManager
import kotlinx.android.synthetic.main.activity_erro.*

/**
 * Created by csanchez on 19/04/2018.
 */

class ErrorActivity : BaseMain() {
    override fun layoutResID(): Int {
        return R.layout.activity_erro
    }

    override fun initViews() {
        val b = intent.extras
        val error = b!!.getString(ERROR_MESSAGE)
        about_error.text = error

        close_btn.setOnClickListener{
            onBackPressed()
        }
    }

    override fun setBackNavigationListener(): NavigationManager.BackFragmentNavigationListener {
        return object : NavigationManager.BackFragmentNavigationListener {
            override fun doInFinishBackNavigation() {
                finish()
            }
        }
    }
}