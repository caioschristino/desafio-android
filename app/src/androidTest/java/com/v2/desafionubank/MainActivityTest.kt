package com.v2.desafionubank

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.v2.desafionubank.ui.activity.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import kotlinx.android.synthetic.main.notice_fragment.*
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by csanchez on 24/04/2018.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.title_notice)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.body_notice)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.recycle_action))
                .check(RecyclerViewItemCountAssertion.Companion.withItemCount(Matchers.greaterThan(0)))
    }

    @Before
    fun initIntents() {
        Intents.init()
    }

    @After
    fun releaseIntents() {
        Intents.release()
    }
}