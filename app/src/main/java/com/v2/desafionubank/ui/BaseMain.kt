package com.v2.desafionubank.ui

import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.v2.desafionubank.ChargebackApplication
import com.v2.desafionubank.R
import com.v2.desafionubank.di.component.ActivityComponent
import com.v2.desafionubank.di.module.ActivityModule
import com.v2.desafionubank.model.ProcessRequest
import com.v2.desafionubank.model.ResponseError
import com.v2.desafionubank.ui.activity.ErrorActivity
import rx.functions.Action1
import javax.inject.Inject



/**
 * Created by csanchez on 19/04/2018.
 */

abstract class BaseMain : AppCompatActivity() {
    protected val ERROR_MESSAGE = "error_message"
    @Inject
    internal var mNavigationManager: NavigationManager? = null

    @BindView(R.id.progress)
    internal var mProgressBar: RelativeLayout? = null

    internal var mActivityComponent: ActivityComponent? = null

    private val activityComponent: ActivityComponent
        get() {
            if (mActivityComponent == null) {
                mActivityComponent = DaggerActivityComponent.builder()
                        .activityModule(ActivityModule(this))
                        .applicationComponent(ChargebackApplication.app!!.applicationComponent)
                        .build()
            }
            return mActivityComponent as ActivityComponent
        }

    @LayoutRes
    abstract fun layoutResID(): Int

    abstract fun initViews()

    abstract fun setBackNavigationListener(): NavigationManager.BackFragmentNavigationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResID())
        ButterKnife.bind(this)
        activityComponent.inject(this)
        mNavigationManager!!.init(supportFragmentManager)

        (application as ChargebackApplication)
                .bus!!
                .toObservable()
                .subscribe({ item ->
                    if (item is ResponseError) {
                        val error = item as ResponseError
                        val intent = Intent(this, ErrorActivity::class.java)
                        intent.putExtra(ERROR_MESSAGE, error.error)
                        startActivity(intent)
                    } else if (item is ProcessRequest) {
                        val (isShowProcess) = item
                        if (mProgressBar != null) {
                            mProgressBar!!.setVisibility(if (isShowProcess) View.VISIBLE else View.GONE)
                        }
                    }
                } as Action1<in Any>)

        initViews()
    }

    fun openFragment(fragment: BaseFragment) {
        mNavigationManager!!.pushFragment(fragment)
    }

    override fun onBackPressed() {
        mNavigationManager!!.popFragment(setBackNavigationListener())
    }
}