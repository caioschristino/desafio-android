package com.v2.desafio.ui.activity

import com.v2.desafio.R
import com.v2.desafio.ui.BaseMain
import com.v2.desafio.ui.NavigationManager.BackFragmentNavigationListener
import com.v2.desafio.ui.fragment.NoticeFragment

/**
 * Created by csanchez on 19/04/2018.
 */

class MainActivity : BaseMain() {
    override fun layoutResID(): Int {
        return R.layout.activity_main
    }

    override fun onRestart() {
        super.onRestart()
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    override fun initViews() {
        openFragment(NoticeFragment())
    }

    override fun setBackNavigationListener(): BackFragmentNavigationListener {
        return object : BackFragmentNavigationListener {
            override fun doInFinishBackNavigation() {
                finish()
            }
        }
    }
}