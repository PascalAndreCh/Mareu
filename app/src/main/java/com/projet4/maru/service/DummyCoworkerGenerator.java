package com.projet4.maru.service;

import com.projet4.maru.model.Coworker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyCoworkerGenerator {

    public static List<Coworker> DUMMY_COWORKERS;

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
    }

    static List<Coworker> generateCoworkers() {return new ArrayList<>(DUMMY_COWORKERS); }

}
