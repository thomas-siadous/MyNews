package com.siadous.thomas.mynews;

import android.content.SharedPreferences;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import com.siadous.thomas.mynews.activities.MainActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.siadous.thomas.mynews.activities.NotificationActivity.PREFERENCE_FILE;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UITest {


    private final static String PREFERENCE_FILE = "PREFERENCE_FILE";

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void displayNotificationActivity() {

        onView(withId(R.id.menu_activity_main_params))
                .perform(click());
        onView(withId(R.id.switch1)).check(matches(isDisplayed()));

    }

    @Test
    public void displaySearchActivity() {

        onView(withId(R.id.menu_activity_main_search))
                .perform(click());
        onView(withId(R.id.search_button)).check(matches(isDisplayed()));

    }

    @Test
    public void editTextOfSearchActivityEmptyNoResultActivityLaunched() {
        onView(withId(R.id.menu_activity_main_search))
                .perform(click());
        onView(withId(R.id.edit_text)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.edit_text)).check(matches(withText("")));
        onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.search_button)).check(matches(isDisplayed()));
    }

    @Test
    public void displayWebView() {

        onView(allOf(isDisplayed(), withId(R.id.rv_list)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.web_view_details)).check(matches(isDisplayed()));

    }

    @Test
    public void editTextOfSearchActivityFilledResultActivityLaunched() {
        onView(withId(R.id.menu_activity_main_search))
                .perform(click());
        onView(withId(R.id.edit_text)).perform(typeText("Donald Trump"), closeSoftKeyboard());
        onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.rv_result_list)).check(matches(isDisplayed()));
    }

    @Test
    public void prefEditTextNotEmptyWhenEditTextFilledAndSwitchEnabledInNotificationActivity() {
        onView(withId(R.id.menu_activity_main_params))
                .perform(click());
        onView(withId(R.id.edit_text)).perform(replaceText("Paris"), closeSoftKeyboard());
        onView(withId(R.id.switch1)).perform(click());
        Espresso.pressBackUnconditionally();
        onView(withId(R.id.menu_activity_main_params))
                .perform(click());
        onView(withId(R.id.edit_text)).check(matches(withText("Paris")));
    }


    @Test
    public void prefCategoriesNotEmptyWhenCategoriesCheckedAndSwitchEnabledInNotificationActivity() {
        onView(withId(R.id.menu_activity_main_params))
                .perform(click());

        onView(withId(R.id.checkBox_arts)).perform(click());
        onView(withId(R.id.checkBox_business)).perform(click());

        onView(withId(R.id.switch1)).perform(click());
        Espresso.pressBackUnconditionally();
        onView(withId(R.id.menu_activity_main_params))
                .perform(click());
        onView(withId(R.id.checkBox_arts)).check(matches(isChecked()));
        onView(withId(R.id.checkBox_business)).check(matches(isChecked()));


    }


    @Test
    public void displayDateAfterSelectInDatePicker() {
        onView(withId(R.id.menu_activity_main_search))
                .perform(click());
        onView(withId(R.id.constraint_layout_begin_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2019, 11, 1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.text_view_begin_set_text)).check(matches(withText("01/11/2019")));

    }

    @Test
    public void changeFragmentFromViewPager() {
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_tabs)).check(matches(hasDescendant(withText("Most Popular"))));
    }

}



