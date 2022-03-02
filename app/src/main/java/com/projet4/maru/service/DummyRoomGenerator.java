package com.projet4.maru.service;

import com.projet4.maru.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyRoomGenerator {

    public static List<Room> DUMMY_ROOMS;

    static {
        DUMMY_ROOMS = Arrays.asList(
                new Room(1, "Martinique", 104 , 6, 1, true),
                new Room(2, "Guadeloupe", 118 , 7, 1, true),
                new Room(3, "Madagascar", 120 , 10, 1, true),
                new Room(4, "Sicile", 205 , 8, 2, false),
                new Room(5, "Corse", 212 , 9, 2, true),
                new Room(6, "Madère", 240 , 10, 2, true),
                new Room(7, "Canaris", 252 , 4, 2, true),
                new Room(8, "Baléares", 301 , 8, 3, true),
                new Room(9, "Jersey", 302 , 8, 3, true),
                new Room(10, "Bermudes", 307 , 10, 3, true)
        );
    }

    static List<Room> generateRooms() {return new ArrayList<>(DUMMY_ROOMS); }

}
