package com.example.android.codelabs.sunandsandsports.ui

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.android.codelabs.sunandsandsports.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityTest{

    @Test
    fun testEvent() {
        val scenario = launchActivity<MainActivity>()
        scenario.onActivity { activity ->

        }

        Espresso.onView(ViewMatchers.withId(R.id.toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText(R.string.app_name))
            .check(ViewAssertions.matches(ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))))

    }
}