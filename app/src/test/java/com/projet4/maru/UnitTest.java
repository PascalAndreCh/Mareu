package com.projet4.maru;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

import com.projet4.maru.di.DI;
import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;
import com.projet4.maru.service.DummyMaReuGenerator;
import com.projet4.maru.service.MaReuApiService;

import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class UnitTest {

    private MaReuApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMaReuGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting newMeeting = new Meeting(30, 1,
                new GregorianCalendar(2022, 3, 2, 10, 30),
                new GregorianCalendar(2022, 3, 2, 11, 45),
                "Avancement projet", "définir les points bloquants et valider les parties de codes fonctionnelles",
                Arrays.asList(new Participant(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development",
                        "Developer_front_end"), new Participant(9, "Estelle NONCPASL", "estelle.noncpasl@pme.fr",
                        "Development", "Developer_Chef_de_projet")));
        service.createMeeting(newMeeting);
        assertTrue(service.getMeetings().contains(newMeeting));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void getparticipantWithSuccess() {
        List<Participant> participants = service.getParticipants();
        List<Participant> expectedParticipants = DummyMaReuGenerator.DUMMY_PARTICIPANTS;
        assertThat(participants, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedParticipants.toArray()));
    }


    @Test
    public void createParticipantWithSuccess() {
        Participant newParticipant = new Participant(16, "Joseph ESPERE", "joseph.espere@pme.fr", "Trade", "Seller");
        service.createParticipant((newParticipant));
        assertTrue(service.getParticipants().contains(newParticipant));
    }

    @Test
    public void deleteParticipantWithSuccess() {
        Participant participantToDelete = service.getParticipants().get(0);
        service.deleteParticipant(participantToDelete);
        assertFalse(service.getParticipants().contains(participantToDelete));
    }

    @Test
    public void getRoomsWithSuccess() {
        List<Room> rooms = service.getRooms();
        List<Room> expectedRooms = DummyMaReuGenerator.DUMMY_ROOMS;
        assertThat(rooms, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedRooms.toArray()));
    }

    @Test
    public void getCoworkersWithSuccess() {
        List<Coworker> coworkers = service.getCoworkers();
        List<Coworker> expectedCoworkers = DummyMaReuGenerator.DUMMY_COWORKERS;
        assertThat(coworkers, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedCoworkers.toArray()));
    }

    @Test
    public void getVipsWithSuccess() {
        List<Vip> vips = service.getVips();
        List<Vip> expectedVips = DummyMaReuGenerator.DUMMY_VIPS;
        assertThat(vips, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedVips.toArray()));
    }

    @Test
    public void getMeetingFilteredRoom() {
        List<Meeting> meetings = service.getMeetings();
        int salleA = 4;
        int salleB = 2;
        assertTrue(service.getMeetingsByRoom(salleA).size() == 2);
        assertEquals(service.getMeetingsByRoom(salleA).get(0).getId(), 1001);
        assertEquals(service.getMeetingsByRoom(salleA).get(1).getId(), 1004);
        assertTrue(service.getMeetingsByRoom(salleB).size() == 0);
    }

    @Test
    public void getMeetingfiltereddate() {
        List<Meeting> meetings = service.getMeetings();
        Calendar dateA = new GregorianCalendar(2022, 03, 14);
        Calendar dateB = new GregorianCalendar(2022, 03, 15);
        assertTrue(service.getMeetingsByDate(dateA).size() == 2);
        assertEquals(service.getMeetingsByDate(dateA).get(0).getId(), 1003);
        assertEquals(service.getMeetingsByDate(dateA).get(1).getId(), 1004);
        assertTrue(service.getMeetingsByDate(dateB).size() == 0);
    }

    @Test
    public void getRoomDate() {
        List<Meeting> meetings = service.getMeetings();
        Calendar dateA = new GregorianCalendar(2022, 03, 14);
        Calendar dateB = new GregorianCalendar(2022, 03, 15);
        assertTrue(service.getRoomsByDates(dateA).size() == 2);
        assertEquals(service.getRoomsByDates(dateA).get(0).getIdRoom(), 7);
        assertEquals(service.getRoomsByDates(dateA).get(1).getIdRoom(), 4);
        assertTrue(service.getRoomsByDates(dateB).size() == 0);

    }

    @Test
    public void roomFree() {

        long roomA = 4;     // la salle est réservée le 14 avril 2022 de 14h00 à 14h45

        //disponible avant
        Calendar dateA1Top = new GregorianCalendar(2022, 03, 14, 9, 0, 0);
        Calendar dateA1End = new GregorianCalendar(2022, 03, 14, 10, 0, 0);

        //disponible après
        Calendar dateA2Top = new GregorianCalendar(2022, 03, 14, 15, 0, 0);
        Calendar dateA2End = new GregorianCalendar(2022, 03, 14, 16, 0, 0);

        // Non disponible, exactement les mêmes créneaux demandés
        Calendar dateA3Top = new GregorianCalendar(2022, 03, 14, 14, 0, 0);
        Calendar dateA3End = new GregorianCalendar(2022, 03, 14, 14, 45, 0);

        // Non disponible, créneau compris dans le créneau déjà réservé
        Calendar dateA4Top = new GregorianCalendar(2022, 03, 14, 14, 10, 0);
        Calendar dateA4End = new GregorianCalendar(2022, 03, 14, 14, 40, 0);

        // Non disponible, créneau englobe le créneau déjà réservé
        Calendar dateA5Top = new GregorianCalendar(2022, 03, 14, 13, 45, 0);
        Calendar dateA5End = new GregorianCalendar(2022, 03, 14, 14, 55);

        // Non disponible, heure de fin inclue dans le créneau déjà réservé
        Calendar dateA6Top = new GregorianCalendar(2022, 03, 14, 13, 00, 0);
        Calendar dateA6End = new GregorianCalendar(2022, 03, 14, 14, 15, 0);

        // Non disponible, heure de début inclue dans le créneau déjà réservé
        Calendar dateA7Top = new GregorianCalendar(2022, 03, 14, 14, 30, 0);
        Calendar dateA7End = new GregorianCalendar(2022, 03, 14, 15, 15, 0);

        long roomB = 6; // la salle n'a aucune réservation, donc, disponible quelquesoit le créneau choisi

        assertTrue(service.roomIsFree(roomA, dateA1Top, dateA1End));
        assertTrue(service.roomIsFree(roomA, dateA2Top, dateA2End));
        assertFalse(service.roomIsFree(roomA, dateA3Top, dateA3End));
        assertFalse(service.roomIsFree(roomA, dateA4Top, dateA4End));
        assertFalse(service.roomIsFree(roomA, dateA5Top, dateA5End));
        assertFalse(service.roomIsFree(roomA, dateA6Top, dateA6End));
        assertFalse(service.roomIsFree(roomA, dateA7Top, dateA7End));
        assertTrue(service.roomIsFree(roomB, dateA1Top, dateA1End));
    }

    @Test
    public void participantfree() {

        long idpartA = 6;     // la personne participa à une réunion le 14 avril 2022 de 14h00 à 14h45

        //disponible avant
        Calendar dateA1Top = new GregorianCalendar(2022, 03, 14, 9, 0, 0);
        Calendar dateA1End = new GregorianCalendar(2022, 03, 14, 10, 0, 0);

        //disponible après
        Calendar dateA2Top = new GregorianCalendar(2022, 03, 14, 15, 0, 0);
        Calendar dateA2End = new GregorianCalendar(2022, 03, 14, 16, 0, 0);

        // Non disponible, exactement les mêmes créneaux demandés
        Calendar dateA3Top = new GregorianCalendar(2022, 03, 14, 14, 0, 0);
        Calendar dateA3End = new GregorianCalendar(2022, 03, 14, 14, 45, 0);

        // Non disponible, créneau compris dans le créneau déjà réservé
        Calendar dateA4Top = new GregorianCalendar(2022, 03, 14, 14, 10, 0);
        Calendar dateA4End = new GregorianCalendar(2022, 03, 14, 14, 40, 0);

        // Non disponible, créneau englobe le créneau déjà réservé
        Calendar dateA5Top = new GregorianCalendar(2022, 03, 14, 13, 45, 0);
        Calendar dateA5End = new GregorianCalendar(2022, 03, 14, 14, 55);

        // Non disponible, heure de fin inclue dans le créneau déjà réservé
        Calendar dateA6Top = new GregorianCalendar(2022, 03, 14, 13, 00, 0);
        Calendar dateA6End = new GregorianCalendar(2022, 03, 14, 14, 15, 0);

        // Non disponible, heure de début inclue dans le créneau déjà réservé
        Calendar dateA7Top = new GregorianCalendar(2022, 03, 14, 14, 30, 0);
        Calendar dateA7End = new GregorianCalendar(2022, 03, 14, 15, 15, 0);

        long idPartB = 8; // la personne ne participa à aucune réunion

        assertTrue(service.participantIsFree(idpartA, dateA1Top, dateA1End));
        assertTrue(service.participantIsFree(idpartA, dateA2Top, dateA2End));
        assertFalse(service.participantIsFree(idpartA, dateA3Top, dateA3End));
        assertFalse(service.participantIsFree(idpartA, dateA4Top, dateA4End));
        assertFalse(service.participantIsFree(idpartA, dateA5Top, dateA5End));
        assertFalse(service.participantIsFree(idpartA, dateA6Top, dateA6End));
        assertFalse(service.participantIsFree(idpartA, dateA7Top, dateA7End));
        assertTrue(service.participantIsFree(idPartB, dateA1Top, dateA1End));

    }


    @Test
    public void listRoomFr() {

        //10 de disponibles
        Calendar dateA1Top = new GregorianCalendar(2022, 03, 14, 15, 0, 0);
        Calendar dateA1End = new GregorianCalendar(2022, 03, 14, 18, 0, 0);

        //8 de disponibles
        Calendar dateA2Top = new GregorianCalendar(2022, 03, 14, 10, 0, 0);
        Calendar dateA2End = new GregorianCalendar(2022, 03, 14, 14, 30, 0);

        //9 de disponibles
        Calendar dateA3Top = new GregorianCalendar(2022, 03, 14, 14, 0, 0);
        Calendar dateA3End = new GregorianCalendar(2022, 03, 14, 16, 0, 0);

        assertEquals(service.listRoomsFree(dateA1Top, dateA1End).size(), 10);

        assertEquals(service.listRoomsFree(dateA2Top, dateA2End).size(), 8);
        assertFalse(service.listRoomsFree(dateA2Top, dateA2End).contains(service.getRooms().get(3)));
        assertFalse(service.listRoomsFree(dateA2Top, dateA2End).contains(service.getRooms().get(6)));

        assertEquals(service.listRoomsFree(dateA3Top, dateA3End).size(), 9);
        assertFalse(service.listRoomsFree(dateA2Top, dateA2End).contains(service.getRooms().get(3)));

    }

    @Test
    public void roombetter() {

        //10 de disponibles
        Calendar dateA1Top = new GregorianCalendar(2022, 03, 14, 14, 30, 0);
        Calendar dateA1End = new GregorianCalendar(2022, 03, 14, 15, 0, 0);
        long idRoom = 5;
        int nbperson = 4;
        int capacité = 5;
        assertEquals(service.roomIsBetter(idRoom, capacité, nbperson, dateA1Top, dateA1End), 7);

        idRoom = 6;
        nbperson = 10;
        capacité = 10;
        assertEquals(service.roomIsBetter(idRoom, capacité, nbperson, dateA1Top, dateA1End), 6);

        idRoom = 5;
        nbperson = 9;
        capacité = 9;
        assertEquals(service.roomIsBetter(idRoom, capacité, nbperson, dateA1Top, dateA1End), 5);
    }

    @Test
    public void roomSmall() {
        long idRoom = 7;
        int nbPeople = 5;
        assertFalse(service.roomToSmall(idRoom, nbPeople));

        nbPeople = 4;
        assertTrue(service.roomToSmall(idRoom, nbPeople));
    }

    @Test
    public void deleteObsolete() {
        int nb = service.getMeetings().size();
        Meeting newMeeting1 = new Meeting(100, 1,
                new GregorianCalendar(2021, 2, 2, 10, 30),
                new GregorianCalendar(2021, 2, 2, 11, 45),
                "Avancement projet", "définir les points bloquants et valider les parties de codes fonctionnelles",
                Arrays.asList(new Participant(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development",
                        "Developer_front_end"), new Participant(9, "Estelle NONCPASL", "estelle.noncpasl@pme.fr",
                        "Development", "Developer_Chef_de_projet")));
        service.createMeeting(newMeeting1);
        Meeting newMeeting2 = new Meeting(101, 1,
                new GregorianCalendar(2021, 4, 2, 10, 30),
                new GregorianCalendar(2021, 4, 2, 11, 45),
                "Avancement projet", "définir les points bloquants et valider les parties de codes fonctionnelles",
                Arrays.asList(new Participant(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development",
                        "Developer_front_end"), new Participant(9, "Estelle NONCPASL", "estelle.noncpasl@pme.fr",
                        "Development", "Developer_Chef_de_projet")));
        service.createMeeting(newMeeting2);
        assertTrue(service.getMeetings().contains(newMeeting1));
        assertTrue(service.getMeetings().contains(newMeeting2));
        assertEquals(service.getMeetings().size(), nb+2);

        service.deleteObsoleteMeetings();

        assertEquals(service.getMeetings().size(), nb);
        assertFalse(service.getMeetings().contains(newMeeting1));
        assertFalse(service.getMeetings().contains(newMeeting2));
    }

    @Test
    public void bigEnough() {
        int nbPeople = 8;
        assertEquals(service.bigEnoughRooms(nbPeople).size(), 6);
    }

    @Test
    public void inputDate() {
        Calendar dateDe1 = new GregorianCalendar(2022, 2,15, 10,30);
        Calendar dateDe2 = new GregorianCalendar(2022, 4,15, 10,30);
        assertFalse(service.inputDateSuperiorToThisDay(dateDe1));
        assertTrue(service.inputDateSuperiorToThisDay(dateDe2));
    }

    @Test
    public void endDateSup() {
        Calendar dateADe = new GregorianCalendar(2022, 3,15, 10,30);
        Calendar dateAFi = new GregorianCalendar(2022, 2,15, 10,45);
        Calendar dateBDe = new GregorianCalendar(2022, 3,15, 10,30);
        Calendar dateBFi = new GregorianCalendar(2022, 3,15, 10,45);
        assertFalse(service.endDateSuperiorToStartDate(dateADe, dateAFi));
        assertTrue(service.endDateSuperiorToStartDate(dateBDe, dateBFi));
    }

    @Test
    public void endDateMet() {
        Calendar dateDeb = GregorianCalendar.getInstance();
        dateDeb.set(2022, 2, 30, 18, 0);
        int duration = 45;
        Calendar dateFin = GregorianCalendar.getInstance();
        dateFin.setTime(dateDeb.getTime());
        dateFin.set(2022, 2,30,18,45);
        assertEquals(service.endDateMeeting(dateDeb, duration).getTime(), dateFin.getTime());
    }
}