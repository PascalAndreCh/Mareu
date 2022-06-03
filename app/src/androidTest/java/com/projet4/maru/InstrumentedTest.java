package com.projet4.maru;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import static utils.RecyclerViewItemCountAssertion.withItemCount;

import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.ui.meeting.MainActivity;

import utils.DeleteViewAction;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private MainActivity mActivity;
    private static int ITEMS_COUNT = 4;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
      mActivity = mActivityRule.getActivity();
      assertThat(mActivity, notNullValue());
    }

   @Test
   public void ListMeetingshouldNotBeEmpty() {
        onView(withId(R.id.recyclerview)).check(matches(hasMinimumChildCount(1)));
   }

    @Test
    public void meetingList_deleteAction_shouldRemoveItemOk() {

        // Given : We remove the element at position 2

        // When perform a click on a delete icon
        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withText("OUI")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT - 1));
        ITEMS_COUNT--;
    }

    @Test
    public void myNeighboursList_click_shouldOpenNeighbourActivity() {
        Meeting meeting = DI.getStartListApiService().getMeetings().get(3);
        //On performe un clic à la position 3 sur la vue list_neighbours
        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        //Check that the name is the right one
        onView(withId(R.id.textMeetingtitle)).check(matches(withText(meeting.getTitle())));
//        onView(ViewMatchers.withId(R.id.button_back)) // dans l'écran détail, on clic sur le bouton retour pour revenir dans l'activité précédente
//                .perform(click());
    }

}