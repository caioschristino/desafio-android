package com.v2.desafionubank.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.v2.desafionubank.ChargebackApplication
import com.v2.desafionubank.R
import com.v2.desafionubank.controller.SessionController
import com.v2.desafionubank.di.component.ApplicationComponent
import com.v2.desafionubank.di.component.DaggerApplicationComponent
import com.v2.desafionubank.di.component.DaggerFragmentComponent
import com.v2.desafionubank.di.component.FragmentComponent
import com.v2.desafionubank.di.module.ActivityModule
import com.v2.desafionubank.di.module.AndroidModule
import com.v2.desafionubank.di.module.FragmentModule
import javax.inject.Inject

/**
 * Created by csanchez on 19/04/2018.
 */

abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var mNavigationManager: NavigationManager
    @Inject
    lateinit var mSessionController: SessionController

    internal lateinit var view: View

    abstract val title: String

    protected abstract fun setContent(): Int

    abstract fun doInBackFragment()

    internal var mFragmentComponent: FragmentComponent? = null
    private val fragmentComponent: FragmentComponent
        get() {
            if (mFragmentComponent == null) {
                mFragmentComponent = DaggerFragmentComponent.builder()
                        .fragmentModule(FragmentModule(this))
                        .applicationComponent(ChargebackApplication.app!!.applicationComponent)
                        .build()
            }
            return mFragmentComponent as FragmentComponent
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view = inflater!!.inflate(setContent(), null)
        fragmentComponent.inject(this);

        initActionBar(true, getString(R.string.app_name))
        return view
    }

    private fun initActionBar(showHomeButton: Boolean, title: String) {
        if (activity != null && activity is BaseMain) {
            val supportActionBar = (activity as BaseMain).supportActionBar
            if (supportActionBar != null) {
                supportActionBar.setHomeButtonEnabled(showHomeButton)
                supportActionBar.setTitle(title)
            }
        }
    }

    fun pushFragment(fragment: BaseFragment) {
        if (mNavigationManager != null) {
            mNavigationManager!!.pushFragment(fragment)
        }
    }

    fun popSelf() {
        hideKeyboard()
        if (mNavigationManager != null) {
            mNavigationManager!!.popFragment((activity as BaseMain).setBackNavigationListener())
        }
    }

    fun hideKeyboard() {
        val inputManager = activity
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val currentFocusedView = activity.currentFocus
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}