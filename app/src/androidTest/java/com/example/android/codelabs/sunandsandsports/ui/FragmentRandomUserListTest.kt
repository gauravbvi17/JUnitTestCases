package com.example.android.codelabs.sunandsandsports.ui


import android.accessibilityservice.AccessibilityGestureEvent.CREATOR
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcel
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.bumptech.glide.load.resource.bitmap.VideoDecoder.parcel
import com.example.android.codelabs.sunandsandsports.R
import com.example.android.codelabs.sunandsandsports.adapter.UsersAdapter
import com.example.android.codelabs.sunandsandsports.model.UserResult
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(JUnit4::class)
class FragmentRandomUserListTest{

   // private lateinit var scenario: ActivityScenario<MainActivity>

  //  var mockLayoutManager: LinearLayoutManager? = null



    private lateinit var mockNavController:NavController

    @Before
    fun setUp()
    {
        mockNavController = Mockito.mock(NavController::class.java)

        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<FragmentRandomUserList>()
        titleScenario.moveToState(Lifecycle.State.RESUMED)

        // Set the NavController property on the fragment
        titleScenario.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.main_nav_graph)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

    }


    @Test
    fun atestisListFragmentVisible() {
        // Create a mock NavController

        onView(withId(R.id.recycler_view_users)).check(matches(isDisplayed()))
       //check view is exist
        //whether particular opetion is clickable or not
        Handler(Looper.getMainLooper()).postDelayed( Runnable {
       //
            onView(withId(R.id.textViewGender)).check(matches(withText("male")))

            onView(withId(R.id.recycler_view_users)) // scrollTo will fail the test if no item matches.
                .perform(
                    RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                        hasDescendant(withText("not in the list"))
                    )
                )

       //     onView(withId(R.id.recycler_view_users)).perform(RecyclerViewActions.actionOnItemAtPosition<UsersAdapter.UsersViewHolder>(0, click()))

        /*    onView(withId(R.id.lnrRoot)).perform(RecyclerViewActions.actionOnItemAtPosition<UsersAdapter.UsersViewHolder>(0, click()))
            val myUser=UserResult("1234")


            val bundle = bundleOf("myUser" to myUser)
            //  view.findNavController().navigate(R.id.confirmationAction, bundle)
            verify(mockNavController).navigate(R.id.fragmentUserDetail,bundle)
*/

            onView(withId(R.id.lnrRoot)).perform(RecyclerViewActions.actionOnItemAtPosition<UsersAdapter.UsersViewHolder>(0, click()))
            val myUser=UserResult("1234")
            val bundle = bundleOf("myUser" to myUser)
            //  view.findNavController().navigate(R.id.confirmationAction, bundle)
            verify(mockNavController).navigate(R.id.fragmentUserDetail,bundle)

            //detail fragment view load or not
            onView(withId(R.id.rootConstraint)).check(matches(isDisplayed()))

            //detail view first position gender check
         //   onView(withId(R.id.txtGender)).check(matches(withText("aaaaa")))

        },8000)








        //     verify(mockNavController).navigate(R.id.fragmentUserDetail)


    }


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
                //   Mockito.`when`(fragment.arguments?.containsKey("myUser")).thenReturn(true)
            }


            assert( true, { mockNavController.currentDestination?.id == R.id.fragmentUserDetail })
            onView(withId(R.id.rootConstraint)).check(matches(isDisplayed()))
            Espresso.pressBack()
        }
    }

}

