package com.v2.desafionubank

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.v2.desafionubank.ui.activity.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import com.v2.desafionubank.assertions.RecyclerViewItemCountAssertion
import com.v2.desafionubank.assertions.RecyclerViewMatcher
import org.hamcrest.Matchers
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
    fun whenNoticeFragmentIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.title_notice)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.body_notice)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.recycle_action))
                .check(RecyclerViewItemCountAssertion.Companion.withItemCount(Matchers.greaterThan(0)))

        onView(RecyclerViewMatcher(R.id.recycle_action).atPosition(0))
                .perform(click())

        onView(withId(R.id.about_block)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun whenChargebackFragmentIsLaunched_shouldDisplayInitialState() {
        onView(RecyclerViewMatcher(R.id.recycle_action).atPosition(0))
                .perform(click())

        onView(withId(R.id.about_block)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.chargeback_title)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.container_block)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.chargeback_title)).check(ViewAssertions.matches(isDisplayed()))

        onView(withId(R.id.recycle_details))
                .check(RecyclerViewItemCountAssertion.Companion.withItemCount(Matchers.greaterThan(0)))
    }
}