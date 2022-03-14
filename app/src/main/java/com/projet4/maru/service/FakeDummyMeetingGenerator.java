package com.projet4.maru.service;


import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class FakeDummyMeetingGenerator {

    /**
     * Les dates rentrées sont le 11/04/2022 de 10h30 à 11h30
     * et le 14/04/2022 de 14h00 à 14h45
     * à modifier si les dates sont dépassées
     */

    //List<Participant> dummyParticipant1 = new ArrayList<Participant>();
    //List<Participant> sDummyParticipant2 = new ArrayList<Participant>();

    public static List<Participant> dummyParticipant1;
    public static List<Participant> DummyParticipant2;

    static {
        dummyParticipant1 = Arrays.asList(
                new Participant(1, "Mathieu DUPONT", "Development", "Developer_front_end", "mathieu.dupont@pme.fr"),
                new Participant(4, "Sophie DAPRES", "Development", "Developer_full_stack", "sophie.dapres@pme.fr"),
                new Participant(14, "Emilie JOLIE", "Trade", "Seller", "emile.jolie@pme.fr"),
                new Participant(20, "Mathieu DUPONT", "Development", "Developer_front_end", "mathieu.dupont@pme.fr"),
                new Participant(904, "Monique KIFFER", "SARL DUPONTIN", "Directeur", "monique.kiffer@pme.fr")
        );
    }
    static ArrayList<Participant> generateParticipants() {return new ArrayList<>(dummyParticipant1); }

       static {
        DummyParticipant2 = Arrays.asList(
                new Participant(9, "Estelle NONCPASL", "Development", "Developer_Chef_de_projet", "estelle.noncpasl@pme.fr"),
                new Participant(5, "Annie VERSAIRE", "Development", "Developer_mobile_android", "annie.versaire@pme.fr"),
                new Participant(11, "Jacques COURT", "Development", "Developer_mobile", "jacques.court@pme.fr")
        );
    }

    public static List<Meeting> DUMMY_MEETINGS;

    static {
        DUMMY_MEETINGS = Arrays.asList(
                new Meeting(1001, 2, new Date(1649665800000L), new Date(1649669400000L), 60, "nouvelle fonctionnalité Ma Réunion", "Le client souhaite une fonctionnalité supplémentaire", dummyParticipant1 ),
                new Meeting(1003, 7, new Date(1649937600000L), new Date(1649940300000L), 45, "Avancement appli Pizza", "Faire le point sur ce qui est fait et ce qu'il reste à écrire, point bloquants", DummyParticipant2)
        );
    }
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}