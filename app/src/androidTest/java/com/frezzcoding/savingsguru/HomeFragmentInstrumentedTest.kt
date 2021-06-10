package com.frezzcoding.savingsguru

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.frezzcoding.savingsguru.functionalities.home.HomeFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeFragmentInstrumentedTest {

    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun is_HomeFragment_open_first(){
        onView(ViewMatchers.withId(R.id.fragment_home_container))
            .check(matches(isDisplayed()))
    }

    @Test
    fun all_ui_elements_visible(){
        onView(ViewMatchers.withId(R.id.tv_financial_goal))
            .check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.recycler_scenario))
            .check(matches(isDisplayed()))
    }

}