package com.frezzcoding.savingsguru

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {


    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun navigate_To_Settings_Fragment(){
        //nav directorsfragment
        onView(withId(R.id.settingsFragment)).perform(click())

        //verify
        onView(withId(R.id.fragment_settings_container))
            .check(matches(isDisplayed()))

        //on press back
        pressBack()

        //verify
        onView(withId(R.id.fragment_home_container))
            .check(matches(isDisplayed()))
    }


    @Test
    fun navigate_To_Statistics_Fragment() {
        //nav directorsfragment
        onView(withId(R.id.statsFragment)).perform(click())

        //verify
        onView(withId(R.id.fragment_statistics_container))
            .check(matches(isDisplayed()))

        //on press back
        pressBack()

        //verify
        onView(withId(R.id.fragment_home_container))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigate_To_Graphs_Fragment() {
        //nav directorsfragment
        onView(withId(R.id.graphsFragment)).perform(click())

        //verify
        onView(withId(R.id.fragment_graph_container))
            .check(matches(isDisplayed()))

        //on press back
        pressBack()

        //verify
        onView(withId(R.id.fragment_home_container))
            .check(matches(isDisplayed()))
    }
    @Test
    fun navigate_To_new_scenario_Fragment() {
        //nav directorsfragment
        onView(withId(R.id.addFragment)).perform(click())

        //verify
        onView(withId(R.id.fragment_newscenario_container))
            .check(matches(isDisplayed()))

        //on press back
        pressBack()

        //verify
        onView(withId(R.id.fragment_home_container))
            .check(matches(isDisplayed()))
    }


}