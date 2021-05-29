package com.frezzcoding.savingsguru

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsFragmentInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.settingsFragment)).perform(click())
    }

    @Test
    fun all_ui_components_visible() {
        onView(withId(R.id.tv_settings_notifications)).check(matches(isDisplayed()))
        onView(withId(R.id.switch_notification)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_clear_cache)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_clear_cache)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_settings)).check(matches(isDisplayed()))
    }

    @Test
    fun switch_set_to_unchecked_on_default() {
        onView(withId(R.id.switch_notification)).check(matches(isNotChecked()))
    }

    @Test
    fun show_dialog_on_clear_button_press(){
        onView(withId(R.id.btn_clear_cache)).perform(click())

        onView(withText(R.string.confirm_cache_removal)).check(matches(isDisplayed()))
    }


}