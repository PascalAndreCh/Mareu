package com.projet4.maru.service;

import com.projet4.maru.model.Room;

import java.util.List;

public class DummyRoomApiService implements RoomApiService {

    private List<Room> rooms = DummyRoomGenerator.generateRooms();

    @Override
    public List<Room> getRooms() {
        return rooms;
    }
}
