package com.v2.desafio.ui

import android.support.v4.app.FragmentManager
import com.v2.desafio.R
import java.util.*

/**
 * Created by csanchez on 19/04/2018.
 */

class NavigationManager {
    private var mFragmentManager: FragmentManager? = null
    private var mFragmentStack: Stack<BaseFragment>? = null

    interface BackFragmentNavigationListener {

        /**
         * Callback on backstack changed.
         */
        fun doInFinishBackNavigation()
    }

    fun init(fragmentManager: FragmentManager) {
        mFragmentManager = fragmentManager
        mFragmentStack = Stack()
    }

    fun popFragment(listener: BackFragmentNavigationListener) {
        if (mFragmentStack!!.size > 1) {
            val ft = mFragmentManager!!.beginTransaction()
            mFragmentStack!!.lastElement().onPause()
            ft.remove(mFragmentStack!!.pop())
            mFragmentStack!!.lastElement().onResume()
            ft.show(mFragmentStack!!.lastElement())
            ft.commit()
            mFragmentStack!!.lastElement().doInBackFragment()
        } else {
            listener.doInFinishBackNavigation()
        }
    }

    fun pushFragment(fragment: BaseFragment?) {
        if (fragment != null) {
            val ft = mFragmentManager!!.beginTransaction()
            ft.show(fragment)

            ft.add(R.id.main_container, fragment)
            if (mFragmentStack!!.size > 0) {
                val last = mFragmentStack!!.lastElement()
                if (last != null) {
                    last.onPause()
                    ft.hide(last)
                }
            }
            mFragmentStack!!.push(fragment)
            ft.commit()
        }
    }

    private fun popEveryFragment() {
        // Clear all back stack.
        val backStackCount = mFragmentManager!!.backStackEntryCount
        for (i in 0 until backStackCount) {
            // Get the back stack fragment id.
            val backStackId = mFragmentManager!!.getBackStackEntryAt(i).id
            mFragmentManager!!.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}