package com.projet4.maru.service;

import com.projet4.maru.model.Meeting;

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

    public static List<Meeting> sDummyMeetings = Arrays.asList(
       new Meeting(1001, 2,new Date (1649665800000L) , new Date(1649669400000L),"nouvelle fonctionnalité Ma Réunion", "Le client souhaite une fonctionnalité supplémentaire", 1, 3, 10, 4, 14, 20, 904, 0, 0,0),
            new Meeting(1003, 7,new Date (1649937600000L) , new Date(1649940300000L),"Avancement appli Pizza", "Faire le point sur ce qui est fait et ce qu'il reste à écrire, point bloquants", 9, 11, 5, 0, 0, 0, 0, 0, 0,0)
    );

    static List<Meeting> generateMeetings() { return new ArrayList<>(sDummyMeetings); }

}
