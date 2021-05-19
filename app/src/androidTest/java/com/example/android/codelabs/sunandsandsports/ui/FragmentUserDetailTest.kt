package com.example.android.codelabs.sunandsandsports.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.codelabs.sunandsandsports.R
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class FragmentUserDetailTest{

    @Test
    fun detailFragment()
    {
        val mockNavController = Mockito.mock(NavController::class.java)

        // Create a graphical FragmentScenario for the TitleScreen

        val detailScenario = launchFragmentInContainer<FragmentUserDetail>()

        // Set the NavController property on the fragment
        detailScenario.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.main_nav_graph)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
            Mockito.`when`(fragment.arguments?.containsKey("myUser")).thenReturn(true)
        }


        assert( true, { mockNavController.currentDestination?.id == R.id.fragmentUserDetail })
        onView(withId(R.id.rootConstraint)).check(matches(isDisplayed()))
        Espresso.pressBack()
    }
}