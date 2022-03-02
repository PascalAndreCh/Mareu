package com.projet4.maru.service;

import com.projet4.maru.model.Vip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyVipGenerator {

    public static List<Vip> DUMMY_VIPS;

    static {
        DUMMY_VIPS = Arrays.asList(
                new Vip(901, "John ROUGE", "SA BLANCHE", "Directeur", "john.rouge@pme.fr"),
                new Vip(902, "Jules FERRY", "SA BLANCHE", "Commercial", "jules.ferry@pme.fr"),
                new Vip(903, "Anatole FRANCE", "SARL DUPONTIN", "Commercial", "anatole.france@pme.fr"),
                new Vip(904, "Monique KIFFER", "SARL DUPONTIN", "Directeur", "monique.kiffer@pme.fr"),
                new Vip(905, "Beatrice LE FORT", "SARL DUPONTIN", "Gection Financière", "beatrice.lefort@pme.fr")
        );
    }

    static List<Vip> generateVips() {return new ArrayList<>(DUMMY_VIPS); }

}
