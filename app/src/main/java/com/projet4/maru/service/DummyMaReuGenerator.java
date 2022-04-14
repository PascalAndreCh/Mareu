package com.projet4.maru.service;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

public class DummyMaReuGenerator {

    public static List<Coworker> DUMMY_COWORKERS;
    public static List<Room> DUMMY_ROOMS;
    public static List<Vip> DUMMY_VIPS;
    private static List<Participant> DUMMY_PARTICIPANT1;
    private static List<Participant> DUMMY_PARTICIPANT2;
    private static List<Participant> DUMMY_PARTICIPANT3;
    public static List<Meeting> DUMMY_MEETINGS;
    public static List<Participant> DUMMY_PARTICIPANTS;

    static {
        DUMMY_COWORKERS = Arrays.asList(
                new Coworker(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development", "Developer_front_end"),
                new Coworker(2, "Henri DUPOND", "henri.dupond@pme.fr", "Development", "Developer_back_end"),
                new Coworker(3, "Marie POPPY", "marie.poppy@pme.fr", "Development", "Developer_mobile"),
                new Coworker(4, "Sophie DAPRES", "sophie.dapres@pme.fr", "Development", "Developer_full_stack"),
                new Coworker(5, "Annie VERSAIRE", "annie.versaire@pme.fr", "Development", "Developer_mobile_android"),
                new Coworker(6, "Paul NORD", "paul.nord@pme.fr", "Development", "Developer_designer"),
                new Coworker(7, "Jean NEMARD", "mathieu.dupont@pme.fr", "Development", "Developer_mobile_IOS"),
                new Coworker(8, "Pierre KIROULE", "pierre.kiroule@pme.fr", "Development", "Developer_front_end"),
                new Coworker(9, "Estelle NONCPASL", "estelle.noncpasl@pme.fr", "Development", "Developer_Chef_de_projet"),
                new Coworker(10, "Chloé MARTIN", "chloe.martin@pme.fr", "Development", "Developer_designer"),
                new Coworker(11, "Jacques COURT", "jacques.court@pme.fr", "Development", "Developer_mobile"),
                new Coworker(12, "Henry CREOLE", "henry.creole@pme.fr", "Development", "Developer_full_stack"),
                new Coworker(13, "André PICK", "andre.pick@pme.fr", "Development", "Developer_mobile"),
                new Coworker(14, "Bernard ROSIER", "bernard.rosier@pme.fr", "Trade", "Manager"),
                new Coworker(15, "Emilie JOLIE", "emile.jolie@pme.fr", "Trade", "Seller"),
                new Coworker(16, "Joseph ESPERE", "joseph.espere@pme.fr", "Trade", "Seller"),
                new Coworker(17, "Michel LEGRAND", "michel.legrand@pme.fr", "Direction", "Pdg"),
                new Coworker(18, "Yollande PETIT", "yollande.petit@pme.fr", "Direction", "Secretaire"),
                new Coworker(19, "Serge PROPRE", "mathieu.dupont@pme.fr", "Maintenace", "Technicien_Polyvalent"),
                new Coworker(20, "Nadine PELLA", "nadine.pella@pme.fr", "Human_Ressources", "DRH")
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
        DUMMY_VIPS = Arrays.asList(
                new Vip(901, "John ROUGE", "john.rouge@pmi.com", "SA BLANCHE", "Directeur"),
                new Vip(902, "Jules FERRY", "jules.ferry@pmi.com", "SA BLANCHE", "Commercial"),
                new Vip(903, "Anatole FRANCE", "anatole.france@pmi.com", "SARL DUPONTIN", "Commercial"),
                new Vip(904, "Monique KIFFER", "monique.kiffer@pmi.com", "SARL DUPONTIN", "Directeur"),
                new Vip(905, "Beatrice LE FORT", "beatrice.lefort@pmi.com", "SARL DUPONTIN", "Gection Financière")
        );
        DUMMY_PARTICIPANT1 = Arrays.asList(
                new Participant(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development", "Developer_front_end"),
                new Participant(4, "Sophie DAPRES", "sophie.dapres@pme.fr", "Development", "Developer_full_stack"),
                new Participant(14, "Emilie JOLIE", "emile.jolie@pme.fr", "Trade", "Seller"),
                new Participant(20, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development", "Developer_front_end"),
                new Participant(904, "Monique KIFFER", "monique.kiffer@pme.fr", "SARL DUPONTIN", "Directeur")
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
                new Meeting(1001, 4, new GregorianCalendar(2022, 5, 11, 10, 30), new GregorianCalendar(2022, 3, 11, 11, 30), "nouvelle fonctionnalité Ma Réunion", "Le client souhaite une fonctionnalité supplémentaire", DUMMY_PARTICIPANT1),
                new Meeting(1003, 7, new GregorianCalendar(2022, 5, 14, 9, 15), new GregorianCalendar(2022, 3, 14, 10, 15), "Avancement appli Pizza", "Faire le point sur ce qui est fait et ce qu'il reste à écrire, point bloquants", DUMMY_PARTICIPANT2),
                new Meeting(1004, 4, new GregorianCalendar(2022, 5, 14, 14, 0), new GregorianCalendar(2022, 3, 14, 14, 45), "Repas de Fin d'année", "Choisir le restaurant et le menu", DUMMY_PARTICIPANT3)
        );
        DUMMY_PARTICIPANTS = Arrays.asList(
                new Participant(1, "Mathieu DUPONT", "mathieu.dupont@pme.fr", "Development", "Developer_front_end"),
                new Participant(4, "Sophie DAPRES", "sophie.dapres@pme.fr", "Development", "Developer_full_stack"),
                new Participant(14, "Emilie JOLIE", "emile.jolie@pme.fr", "Trade", "Seller"),
                new Participant( 20, "Nadine PELLA", "nadine.pella@pme.fr", "Human_Ressources", "DRH"),
                new Participant(904, "Monique KIFFER", "monique.kiffer@pme.fr", "SARL DUPONTIN", "Directeur")
        );

    }

    static List<Coworker> generateCoworkers() {
        return new ArrayList<>(DUMMY_COWORKERS);
    }

    public static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    static List<Vip> generateVips() {
        return new ArrayList<>(DUMMY_VIPS);
    }

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    static List<Participant> generateParticipants() { return new ArrayList<>(DUMMY_PARTICIPANTS); }


    /**
     * Les dates rentrées sont le 11/04/2022 de 10h30 à 11h30
     * et le 14/04/2022 de 14h00 à 14h45
     * à modifier si les dates sont dépassées
     */

}
