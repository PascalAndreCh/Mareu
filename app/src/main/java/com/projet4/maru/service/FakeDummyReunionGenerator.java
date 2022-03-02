package com.projet4.maru.service;

import com.projet4.maru.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class FakeDummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
       new Reunion(1001, 2, 2022, 3, 14, 10, 30, 11, 30,"nouvelle fonctionnalité Ma Réunion", "Le client souhaite une fonctionnalité supplémentaire", 1, 3, 10, 4, 14, 20, 904, 0, 0,0),
       new Reunion(1002, 7, 2022 , 3, 16, 14, 00, 14, 45,"avancement appli pizza", "faire le point sur les parties réalisées et ce qu'il reste à faire", 9, 3, 4, 0, 0, 0, 0, 0, 0,0)
    );

    static List<Reunion> generateReunions() { return new ArrayList<>(DUMMY_REUNIONS); }

}
