package com.projet4.maru;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import static utils.RecyclerViewItemCountAssertion.withItemCount;

import com.projet4.maru.di.DI;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.ui.meeting.MainActivity;

import utils.DeleteViewAction;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private static int ITEMS_COUNT = 4;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        MainActivity activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
    }

    @Test   // teste que la liste n'est pas vide et qu'il y a 4 meetings (donc, le meeting obsolète à bien été supprimé
    public void ListMeetingshouldNotBeEmpty() {
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(ITEMS_COUNT));
    }

    @Test // teste qu'un clic sur la corbeille ne supprime pas le meeting si l'on répond non et le supprime bien si l'on répond oui
    public void meetingList_deleteAction_shouldRemoveItemOk() {

        onView(withId(R.id.recyclerviewmain))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withText("NON")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(ITEMS_COUNT));

        onView(withId(R.id.recyclerviewmain))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withText("OUI")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(ITEMS_COUNT - 1));
        ITEMS_COUNT--;
    }

    @Test   // Teste qu'une sélection d'un meeting ouvre bien l'activité de modification et qu'une modification est bien prise en compte
    public void meetingList_click_shouldOpenAddMeetingActivity() {
        Meeting meeting = DI.getStartListApiService().getMeetings().get(3);
        String title = meeting.getTitle();
        onView(withId(R.id.recyclerviewmain))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText(meeting.getTitle())));
        onView(withId(R.id.textMeetingtitleEdit))
                .perform(replaceText("Ajout texte "+ meeting.getTitle()), closeSoftKeyboard());

        onView(withId(R.id.meeting_set))              // clic sur le bouton de modification du meeting
                .perform(click());

        onView(withId(R.id.recyclerviewmain))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText("Ajout texte "+title)));

        onView(withId(R.id.meeting_set))
                .perform(click());
    }

    @Test  // Teste la création d'un meeting, avec toutes les saisies, titre, description, date, heure départ, durée et heure de fin, participants et salle
    public void addMeeting_click_shouldOpenAddMeetingActivity() {
        onView(withId(R.id.add_meeting))
                .perform(click());

        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText("")));

        onView(withId(R.id.textMeetingtitleEdit))
                .perform(replaceText("titre du test"), closeSoftKeyboard());

        onView(withId(R.id.textMeetingCommentEdit))
                .perform(replaceText("ceci est le commentaire du test"), closeSoftKeyboard());

        onView(withId(R.id.dateMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 30));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.hourStartMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(9, 15));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.durationMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(1, 30));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.participantMeeting))
                .perform(click());
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));   // séléction des participants

        onView(withId(R.id.add_coworker))   // validation de la sélection des participants
                .perform(click());
        // retour dans la création du meeting avec affichage des participants sélectionnés

        onView(withId(R.id.roomMeeting))    // clic sur l'icone "salle" pour aller sélectionner la salle
                .perform(click());
        // nous sommes dans l'écran de sélection des salles

        onView(withId(R.id.recyclerviewroom))       // sélection de la 4eme salle (sicile)
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        // retour dans l'écran de création du meeting avec affichage de la salle sélectionnée

        onView(withId(R.id.meeting_set))              // clic sur le bouton de création du meeting
                .perform(click());
        // retour dans l'affichage de tous les meetings

        onView(withId(R.id.recyclerviewmain)).check(withItemCount(ITEMS_COUNT + 1)); // il y a 1 meeting de plus, celui créé
        ITEMS_COUNT++;
    }


    @Test   // Teste les filtres et reset
    public void filter_select() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("Filter by date"))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 12));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(3));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("Reset filter"))
                .perform(click());
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(4));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("Filter by room")).perform(click());
        onData(anything()).atPosition(3).perform(click());
        onView(withText("Salle 205, Sicile , 2ème étage, 8 personnes")).perform(click());
        onView(withId(R.id.room_set))
                .perform(click());
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(2));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        // Click the item.
        onView(withText("Reset filter"))
                .perform(click());
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(4));
    }

}