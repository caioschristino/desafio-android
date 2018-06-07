package com.v2.desafio.assertions

import android.support.v7.widget.RecyclerView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.view.View
import org.hamcrest.Matcher
import org.junit.Assert


/**
 * Created by csanchez on 24/04/2018.
 */

class RecyclerViewItemCountAssertion private constructor(private val matcher: Matcher<Int>) : ViewAssertion {
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        Assert.assertThat(adapter.itemCount, matcher)
    }

    companion object {
        fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
            return withItemCount((expectedCount))
        }

        fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
            return RecyclerViewItemCountAssertion(matcher)
        }
    }
}