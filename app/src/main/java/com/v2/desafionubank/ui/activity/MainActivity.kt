package com.v2.desafionubank.ui.activity

import com.v2.desafionubank.R
import com.v2.desafionubank.ui.BaseMain
import com.v2.desafionubank.ui.NavigationManager.BackFragmentNavigationListener
import com.v2.desafionubank.ui.fragment.NoticeFragment

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