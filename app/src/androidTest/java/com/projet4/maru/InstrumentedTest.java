package com.projet4.maru;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

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

    @Test   // teste que la liste n'est pas vide et qu'il y a 4 meetings (donc, le meeting obsol??te ?? bien ??t?? supprim??
    public void ListMeetingshouldNotBeEmpty() {
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(ITEMS_COUNT));
    }

    @Test // teste qu'un clic sur la corbeille ne supprime pas le meeting si l'on r??pond non et le supprime bien si l'on r??pond oui
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

    @Test   // Teste qu'une s??lection d'un meeting ouvre bien l'activit?? de modification et qu'une modification est bien prise en compte
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

    @Test  // Teste la cr??ation d'un meeting, avec toutes les saisies, titre, description, date, heure d??part, dur??e et heure de fin, participants et salle
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
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));   // s??l??ction des participants

        onView(withId(R.id.add_coworker))   // validation de la s??lection des participants
                .perform(click());
        // retour dans la cr??ation du meeting avec affichage des participants s??lectionn??s

        onView(withId(R.id.roomMeeting))    // clic sur l'icone "salle" pour aller s??lectionner la salle
                .perform(click());
        // nous sommes dans l'??cran de s??lection des salles

        onView(withId(R.id.recyclerviewroom))       // s??lection de la 4eme salle (sicile)
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        // retour dans l'??cran de cr??ation du meeting avec affichage de la salle s??lectionn??e

        onView(withId(R.id.meeting_set))              // clic sur le bouton de cr??ation du meeting
                .perform(click());
        // retour dans l'affichage de tous les meetings

        onView(withId(R.id.recyclerviewmain)).check(withItemCount(ITEMS_COUNT + 1)); // il y a 1 meeting de plus, celui cr????
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
        onView(withText("Salle 205, Sicile , 2??me ??tage, 8 personnes")).perform(click());
        onView(withId(R.id.room_set))
                .perform(click());
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(2));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        // Click the item.
        onView(withText("Reset filter"))
                .perform(click());
        onView(withId(R.id.recyclerviewmain)).check(withItemCount(4));
    }

    /*
    @Test
    public void reaffichage() {

    // cr??ation d'un meeting 1
        onView(withId(R.id.add_meeting))
                .perform(click());

        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText("")));

        onView(withId(R.id.textMeetingtitleEdit))
                .perform(replaceText("titre 1"), closeSoftKeyboard());

        onView(withId(R.id.textMeetingCommentEdit))
                .perform(replaceText("commentaire du titre 1"), closeSoftKeyboard());

        onView(withId(R.id.dateMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 21));
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
                .perform(PickerActions.setTime(1, 15));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.participantMeeting))
                .perform(click());
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));   // s??l??ction des participants

        onView(withId(R.id.add_coworker))   // validation de la s??lection des participants
                .perform(click());
        // retour dans la cr??ation du meeting avec affichage des participants s??lectionn??s

        onView(withId(R.id.roomMeeting))    // clic sur l'icone "salle" pour aller s??lectionner la salle
                .perform(click());
        // nous sommes dans l'??cran de s??lection des salles

        onView(withId(R.id.recyclerviewroom))       // s??lection de la 4eme salle (sicile)
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // retour dans l'??cran de cr??ation du meeting avec affichage de la salle s??lectionn??e

        onView(withId(R.id.meeting_set))              // clic sur le bouton de cr??ation du meeting
                .perform(click());
        // retour dans l'affichage de tous les meetings

        stop();

        // cr??ation d'un meeting 2
        onView(withId(R.id.add_meeting))
                .perform(click());

        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText("")));

        onView(withId(R.id.textMeetingtitleEdit))
                .perform(replaceText("titre 2"), closeSoftKeyboard());

        onView(withId(R.id.textMeetingCommentEdit))
                .perform(replaceText("commentaire du titre 2"), closeSoftKeyboard());

        onView(withId(R.id.dateMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 22));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.hourStartMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(9, 30));
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
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));   // s??l??ction des participants

        onView(withId(R.id.add_coworker))   // validation de la s??lection des participants
                .perform(click());
        // retour dans la cr??ation du meeting avec affichage des participants s??lectionn??s

        onView(withId(R.id.roomMeeting))    // clic sur l'icone "salle" pour aller s??lectionner la salle
                .perform(click());
        // nous sommes dans l'??cran de s??lection des salles

        onView(withId(R.id.recyclerviewroom))       // s??lection de la 4eme salle (sicile)
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        // retour dans l'??cran de cr??ation du meeting avec affichage de la salle s??lectionn??e

        onView(withId(R.id.meeting_set))              // clic sur le bouton de cr??ation du meeting
                .perform(click());
        // retour dans l'affichage de tous les meetings

        stop();

        // cr??ation d'un meeting 3
        onView(withId(R.id.add_meeting))
                .perform(click());

        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText("")));

        onView(withId(R.id.textMeetingtitleEdit))
                .perform(replaceText("titre 3"), closeSoftKeyboard());

        onView(withId(R.id.textMeetingCommentEdit))
                .perform(replaceText("commentaire du titre 3"), closeSoftKeyboard());

        onView(withId(R.id.dateMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 23));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.hourStartMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(9, 45));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.durationMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(1, 45));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.participantMeeting))
                .perform(click());
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));   // s??l??ction des participants

        onView(withId(R.id.add_coworker))   // validation de la s??lection des participants
                .perform(click());
        // retour dans la cr??ation du meeting avec affichage des participants s??lectionn??s

        onView(withId(R.id.roomMeeting))    // clic sur l'icone "salle" pour aller s??lectionner la salle
                .perform(click());
        // nous sommes dans l'??cran de s??lection des salles

        onView(withId(R.id.recyclerviewroom))       // s??lection de la 4eme salle (sicile)
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        // retour dans l'??cran de cr??ation du meeting avec affichage de la salle s??lectionn??e

        onView(withId(R.id.meeting_set))              // clic sur le bouton de cr??ation du meeting
                .perform(click());
        // retour dans l'affichage de tous les meetings

        stop();

        // cr??ation d'un meeting  4
        onView(withId(R.id.add_meeting))
                .perform(click());

        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText("")));

        onView(withId(R.id.textMeetingtitleEdit))
                .perform(replaceText("titre 4"), closeSoftKeyboard());

        onView(withId(R.id.textMeetingCommentEdit))
                .perform(replaceText("commentaire du titre 4"), closeSoftKeyboard());

        onView(withId(R.id.dateMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 24));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.hourStartMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10, 0));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.durationMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(2, 0));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.participantMeeting))
                .perform(click());
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(7, click()));   // s??l??ction des participants

        onView(withId(R.id.add_coworker))   // validation de la s??lection des participants
                .perform(click());
        // retour dans la cr??ation du meeting avec affichage des participants s??lectionn??s

        onView(withId(R.id.roomMeeting))    // clic sur l'icone "salle" pour aller s??lectionner la salle
                .perform(click());
        // nous sommes dans l'??cran de s??lection des salles

        onView(withId(R.id.recyclerviewroom))       // s??lection de la 4eme salle (sicile)
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
        // retour dans l'??cran de cr??ation du meeting avec affichage de la salle s??lectionn??e

        onView(withId(R.id.meeting_set))              // clic sur le bouton de cr??ation du meeting
                .perform(click());
        // retour dans l'affichage de tous les meetings

        stop();

        // cr??ation d'un meeting 5
        onView(withId(R.id.add_meeting))
                .perform(click());

        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText("")));

        onView(withId(R.id.textMeetingtitleEdit))
                .perform(replaceText("titre 5"), closeSoftKeyboard());

        onView(withId(R.id.textMeetingCommentEdit))
                .perform(replaceText("commentaire du titre 5"), closeSoftKeyboard());

        onView(withId(R.id.dateMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 25));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.hourStartMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10, 15));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.durationMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(2, 15));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.participantMeeting))
                .perform(click());
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(8, click()));   // s??l??ction des participants

        onView(withId(R.id.add_coworker))   // validation de la s??lection des participants
                .perform(click());
        // retour dans la cr??ation du meeting avec affichage des participants s??lectionn??s

        onView(withId(R.id.roomMeeting))    // clic sur l'icone "salle" pour aller s??lectionner la salle
                .perform(click());
        // nous sommes dans l'??cran de s??lection des salles

        onView(withId(R.id.recyclerviewroom))       // s??lection de la 4eme salle (sicile)
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
        // retour dans l'??cran de cr??ation du meeting avec affichage de la salle s??lectionn??e

        onView(withId(R.id.meeting_set))              // clic sur le bouton de cr??ation du meeting
                .perform(click());
        // retour dans l'affichage de tous les meetings


        onView(withId(R.id.recyclerviewmain)).perform(
                RecyclerViewActions.scrollToPosition(8));
    stop();

        // cr??ation d'un meeting 6
        onView(withId(R.id.add_meeting))
                .perform(click());

        onView(withId(R.id.textMeetingtitleEdit)).check(matches(withText("")));

        onView(withId(R.id.textMeetingtitleEdit))
                .perform(replaceText("titre 6"), closeSoftKeyboard());

        onView(withId(R.id.textMeetingCommentEdit))
                .perform(replaceText("commentaire du titre 6"), closeSoftKeyboard());

        onView(withId(R.id.dateMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 7, 26));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.hourStartMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10, 30));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.durationMeeting))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(2, 30));
        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.participantMeeting))
                .perform(click());
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
        onView(withId(R.id.recyclerviewcow))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));   // s??l??ction des participants

        onView(withId(R.id.add_coworker))   // validation de la s??lection des participants
                .perform(click());
        // retour dans la cr??ation du meeting avec affichage des participants s??lectionn??s

        onView(withId(R.id.roomMeeting))    // clic sur l'icone "salle" pour aller s??lectionner la salle
                .perform(click());
        // nous sommes dans l'??cran de s??lection des salles

        onView(withId(R.id.recyclerviewroom))       // s??lection de la 4eme salle (sicile)
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));
        // retour dans l'??cran de cr??ation du meeting avec affichage de la salle s??lectionn??e

        onView(withId(R.id.meeting_set))              // clic sur le bouton de cr??ation du meeting
                .perform(click());
        // retour dans l'affichage de tous les meetings

        onView(withId(R.id.recyclerviewmain)).perform(
                RecyclerViewActions.scrollToPosition(10));

        stop();

        onView(withId(R.id.recyclerviewmain))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new DeleteViewAction()));
        onView(withText("OUI")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        stop();

        onView(withId(R.id.recyclerviewmain))
                .perform(RecyclerViewActions.scrollToPosition(9));

        stop();

        onView(withId(R.id.recyclerviewmain))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, new DeleteViewAction()));
        onView(withText("OUI")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        stop();
        stop();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Reset filter"))
                .perform(click());

        stop();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Reset filter"))
                .perform(click());

        stop();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Reset filter"))
                .perform(click());

        stop();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Reset filter"))
                .perform(click());

        stop();


    }

    public void stop () {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     */

}