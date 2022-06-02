package com.projet4.maru.service;

import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

public class DummyMaReuGenerator {

    public static List<Participant> DUMMY_COWORKERS;
    public static List<Room> DUMMY_ROOMS;

    private static List<Participant> DUMMY_PARTICIPANT1;
    private static List<Participant> DUMMY_PARTICIPANT2;
    private static List<Participant> DUMMY_PARTICIPANT3;
    public static List<Meeting> DUMMY_MEETINGS;
    public static List<Participant> DUMMY_PARTICIPANTS;

    static {
        DUMMY_COWORKERS = Arrays.asList(
                new Participant(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development", "Developer_front_end"),
                new Participant(2, "Henri DUPOND", "henri.dupond@pme.fr", "Development", "Developer_back_end"),
                new Participant(3, "Marie POPPY", "marie.poppy@pme.fr", "Development", "Developer_mobile"),
                new Participant(4, "Sophie DAPRES", "sophie.dapres@pme.fr", "Development", "Developer_full_stack"),
                new Participant(5, "Annie VERSAIRE", "annie.versaire@pme.fr", "Development", "Developer_mobile_android"),
                new Participant(6, "Paul NORD", "paul.nord@pme.fr", "Development", "Developer_designer"),
                new Participant(7, "Jean NEMARD", "mathieu.dupont@pme.fr", "Development", "Developer_mobile_IOS"),
                new Participant(8, "Pierre KIROULE", "pierre.kiroule@pme.fr", "Development", "Developer_front_end"),
                new Participant(9, "Estelle NONCPASL", "estelle.noncpasl@pme.fr", "Development", "Developer_Chef_de_projet"),
                new Participant(10, "Chloé MARTIN", "chloe.martin@pme.fr", "Development", "Developer_designer"),
                new Participant(11, "Jacques COURT", "jacques.court@pme.fr", "Development", "Developer_mobile"),
                new Participant(12, "Henry CREOLE", "henry.creole@pme.fr", "Development", "Developer_full_stack"),
                new Participant(13, "André PICK", "andre.pick@pme.fr", "Development", "Developer_mobile"),
                new Participant(14, "Bernard ROSIER", "bernard.rosier@pme.fr", "Trade", "Manager"),
                new Participant(15, "Emilie JOLIE", "emile.jolie@pme.fr", "Trade", "Seller"),
                new Participant(16, "Joseph ESPERE", "joseph.espere@pme.fr", "Trade", "Seller"),
                new Participant(17, "Michel LEGRAND", "michel.legrand@pme.fr", "Direction", "Pdg"),
                new Participant(18, "Yollande PETIT", "yollande.petit@pme.fr", "Direction", "Secretaire"),
                new Participant(19, "Serge PROPRE", "mathieu.dupont@pme.fr", "Maintenace", "Technicien_Polyvalent"),
                new Participant(20, "Nadine PELLA", "nadine.pella@pme.fr", "Human_Ressources", "DRH")
        );
        DUMMY_ROOMS = Arrays.asList(
                new Room(1, "Martinique", 104, 6, 1),
                new Room(2, "Guadeloupe", 118, 7, 1),
                new Room(3, "Madagascar", 120, 10, 1),
                new Room(4, "Sicile", 205, 5, 2),
                new Room(5, "Corse", 212, 9, 2),
                new Room(6, "Madère", 240, 10, 2),
                new Room(7, "Canaris", 252, 4, 2),
                new Room(8, "Baléares", 301, 8, 3),
                new Room(9, "Jersey", 302, 8, 3),
                new Room(10, "Bermudes", 307, 10, 3)
        );
         DUMMY_PARTICIPANT1 = Arrays.asList(
                new Participant(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development", "Developer_front_end"),
                new Participant(4, "Sophie DAPRES", "sophie.dapres@pme.fr", "Development", "Developer_full_stack"),
                new Participant(14, "Emilie JOLIE", "emile.jolie@pme.fr", "Trade", "Seller"),
                new Participant(20, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development", "Developer_front_end")
        );
        DUMMY_PARTICIPANT2 = Arrays.asList(
                new Participant(9, "Estelle NONCPASL", "estelle.noncpasl@pme.fr", "Development", "Developer_Chef_de_projet"),
                new Participant(5, "Annie VERSAIRE", "annie.versaire@pme.fr", "Development", "Developer_mobile_android"),
                new Participant(11, "Jacques COURT", "jacques.court@pme.fr", "Development", "Developer_mobile")
        );
        DUMMY_PARTICIPANT3 = Arrays.asList(
                new Participant(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development", "Developer_front_end"),
                new Participant(6, "Paul NORD", "paul.nord@pme.fr", "Development", "Developer_designer"),
                new Participant(20, "Nadine PELLA", "nadine.pella@pme.fr", "Human_Ressources", "DRH")
        );
        DUMMY_MEETINGS = Arrays.asList(
                new Meeting(1000, 4, new GregorianCalendar(2022, 6, 11, 10, 30), new GregorianCalendar(2022, 6, 11, 11, 30), "nouvelle fonctionnalité Ma Réunion", "Le client souhaite une fonctionnalité supplémentaire", DUMMY_PARTICIPANT1),
                new Meeting(1001, 9, new GregorianCalendar(2022, 4, 10, 9, 45), new GregorianCalendar(2022, 4, 10, 12, 0), "Réunion obsolète", "teste si la réunion est bien supprimée au démarrage de l'appli", DUMMY_PARTICIPANT2),
                new Meeting(1003, 6, new GregorianCalendar(2022, 6, 12, 9, 15), new GregorianCalendar(2022, 6, 12, 10, 15), "Avancement appli Pizza", "Faire le point sur ce qui est fait et ce qu'il reste à écrire, point bloquants", DUMMY_PARTICIPANT2),
                new Meeting(1004, 4, new GregorianCalendar(2022, 6, 12, 14, 0), new GregorianCalendar(2022, 6, 12, 14, 45), "Repas de Fin d'année", "Choisir le restaurant et le menu", DUMMY_PARTICIPANT3),
                new Meeting(1005, 7, new GregorianCalendar(2022, 6, 12, 9, 10), new GregorianCalendar(2022, 6, 12, 10, 5), "Repas de la nouvelle année", "Choisir la salle et le traiteur", DUMMY_PARTICIPANT3)
        );
    }

    static List<Participant> generateCoworkers() {
        return new ArrayList<>(DUMMY_COWORKERS);
    }

    public static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Participant> generateParticipants() { return new ArrayList<>(DUMMY_COWORKERS); }


    /**
     * Les dates rentrées sont le 11/04/2022 de 10h30 à 11h30
     * et le 14/04/2022 de 14h00 à 14h45
     * à modifier si les dates sont dépassées
     */

}
