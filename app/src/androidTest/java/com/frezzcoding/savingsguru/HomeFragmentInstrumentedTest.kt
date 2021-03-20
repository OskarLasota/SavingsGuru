package com.frezzcoding.savingsguru

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.frezzcoding.savingsguru.functionalities.home.HomeFragment
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeFragmentInstrumentedTest {

    @Test
    fun testNavigationToFragment(){
        //create a testnavhostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)

        val container = launchFragmentInContainer<HomeFragment>()

        container.onFragment{
            Navigation.setViewNavController(it.requireView(), navController)
        }


        Espresso.onView(ViewMatchers.withId(R.id.settingsFragment)).perform(ViewActions.click())
        //assertThat

    }

}