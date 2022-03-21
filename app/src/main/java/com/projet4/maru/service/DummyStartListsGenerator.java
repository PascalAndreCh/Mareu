package com.projet4.maru.service;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DummyStartListsGenerator {

    public static List<Coworker> DUMMY_COWORKERS;
    public static List<Room> DUMMY_ROOMS;
    public static List<Vip> DUMMY_VIPS;
    private static List<Participant> dummyParticipant1;
    public static List<Participant> dummyParticipant2;
    public static List<Meeting> DUMMY_MEETINGS;
    public static List<Participant> participants;

    static {
        DUMMY_COWORKERS = Arrays.asList(
                new Coworker(1, "Mathieu DUPONT", "Development", "Developer_front_end", "mathieu.dupont@pme.fr"),
                new Coworker(2, "Henri DUPOND", "Development", "Developer_back_end", "henri.dupond@pme.fr"),
                new Coworker(3, "Marie POPPY", "Development", "Developer_mobile", "marie.poppy@pme.fr"),
                new Coworker(4, "Sophie DAPRES", "Development", "Developer_full_stack", "sophie.dapres@pme.fr"),
                new Coworker(5, "Annie VERSAIRE", "Development", "Developer_mobile_android", "annie.versaire@pme.fr"),
                new Coworker(6, "Paul NORD", "Development", "Developer_designer", "paul.nord@pme.fr"),
                new Coworker(7, "Jean NEMARD", "Development", "Developer_mobile_IOS", "mathieu.dupont@pme.fr"),
                new Coworker(8, "Pierre KIROULE", "Development", "Developer_front_end", "pierre.kiroule@pme.fr"),
                new Coworker(9, "Estelle NONCPASL", "Development", "Developer_Chef_de_projet", "estelle.noncpasl@pme.fr"),
                new Coworker(10, "Chloé MARTIN", "Development", "Developer_designer", "chloe.martin@pme.fr"),
                new Coworker(11, "Jacques COURT", "Development", "Developer_mobile", "jacques.court@pme.fr"),
                new Coworker(12, "Henry CREOLE", "Development", "Developer_full_stack", "henry.creole@pme.fr"),
                new Coworker(13, "Bernard ROSIER", "Trade", "Manager", "bernard.rosier@pme.fr"),
                new Coworker(14, "Emilie JOLIE", "Trade", "Seller", "emile.jolie@pme.fr"),
                new Coworker(15, "Joseph ESPERE", "Trade", "Seller", "joseph.espere@pme.fr"),
                new Coworker(16, "Michel LEGRAND", "Direction", "Pdg", "michel.legrand@pme.fr"),
                new Coworker(17, "Yollande PETIT", "Direction", "Secretaire", "yollande.petit@pme.fr"),
                new Coworker(18, "Serge PROPRE", "Maintenace", "Technicien_Polyvalent", "mathieu.dupont@pme.fr"),
                new Coworker(19, "Nadine PELLA", "Human_Ressources", "DRH", "nadine.pella@pme.fr"),
                new Coworker(20, "Mathieu DUPONT", "Development", "Developer_front_end", "mathieu.dupont@pme.fr")
        );
        DUMMY_ROOMS = Arrays.asList(
                new Room(1, "Martinique", 104 , 6, 1),
                new Room(2, "Guadeloupe", 118 , 7, 1),
                new Room(3, "Madagascar", 120 , 10, 1),
                new Room(4, "Sicile", 205 , 8, 2),
                new Room(5, "Corse", 212 , 9, 2),
                new Room(6, "Madère", 240 , 10, 2),
                new Room(7, "Canaris", 252 , 4, 2),
                new Room(8, "Baléares", 301 , 8, 3),
                new Room(9, "Jersey", 302 , 8, 3),
                new Room(10, "Bermudes", 307 , 10, 3)
        );
        DUMMY_VIPS = Arrays.asList(
                new Vip(901, "John ROUGE", "SA BLANCHE", "Directeur", "john.rouge@pme.fr"),
                new Vip(902, "Jules FERRY", "SA BLANCHE", "Commercial", "jules.ferry@pme.fr"),
                new Vip(903, "Anatole FRANCE", "SARL DUPONTIN", "Commercial", "anatole.france@pme.fr"),
                new Vip(904, "Monique KIFFER", "SARL DUPONTIN", "Directeur", "monique.kiffer@pme.fr"),
                new Vip(905, "Beatrice LE FORT", "SARL DUPONTIN", "Gection Financière", "beatrice.lefort@pme.fr")
        );
        dummyParticipant1 = Arrays.asList(
                new Participant(1, "Mathieu DUPONT", "Development", "Developer_front_end", "mathieu.dupont@pme.fr"),
                new Participant(4, "Sophie DAPRES", "Development", "Developer_full_stack", "sophie.dapres@pme.fr"),
                new Participant(14, "Emilie JOLIE", "Trade", "Seller", "emile.jolie@pme.fr"),
                new Participant(20, "Mathieu DUPONT", "Development", "Developer_front_end", "mathieu.dupont@pme.fr"),
                new Participant(904, "Monique KIFFER", "SARL DUPONTIN", "Directeur", "monique.kiffer@pme.fr")
        );
        dummyParticipant2 = Arrays.asList(
                new Participant(9, "Estelle NONCPASL", "Development", "Developer_Chef_de_projet", "estelle.noncpasl@pme.fr"),
                new Participant(5, "Annie VERSAIRE", "Development", "Developer_mobile_android", "annie.versaire@pme.fr"),
                new Participant(11, "Jacques COURT", "Development", "Developer_mobile", "jacques.court@pme.fr")
        );
        DUMMY_MEETINGS = Arrays.asList(
                new Meeting(1001, 2, new Date(1649665800000L), new Date(1649669400000L), 60, "nouvelle fonctionnalité Ma Réunion", "Le client souhaite une fonctionnalité supplémentaire", dummyParticipant1 ),
                new Meeting(1003, 7, new Date(1649937600000L), new Date(1649940300000L), 45, "Avancement appli Pizza", "Faire le point sur ce qui est fait et ce qu'il reste à écrire, point bloquants", dummyParticipant2)
        );

    }

    static List<Coworker> generateCoworkers() {return new ArrayList<>(DUMMY_COWORKERS); }

    static List<Room> generateRooms() {return new ArrayList<>(DUMMY_ROOMS); }

    static List<Vip> generateVips() {return new ArrayList<>(DUMMY_VIPS); }

    static List<Meeting> generateMeetings() {return new ArrayList<>(DUMMY_MEETINGS); }

    static List<Participant> generateParticipant1() {return new ArrayList<>(dummyParticipant1); }

    static List<Participant> generateParticipant2() {return new ArrayList<>(dummyParticipant2); }

    static List<Participant> generateParticipant() { return new ArrayList<>(Participant); }

    /**
     * Les dates rentrées sont le 11/04/2022 de 10h30 à 11h30
     * et le 14/04/2022 de 14h00 à 14h45
     * à modifier si les dates sont dépassées
     */

}
