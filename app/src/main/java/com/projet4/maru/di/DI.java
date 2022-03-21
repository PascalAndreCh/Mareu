package com.projet4.maru.di;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;
import com.projet4.maru.service.DummyStartListsApiService;
import com.projet4.maru.service.StartListsApiService;

import java.util.List;

public class DI {

    public static List<Coworker> coworkers;
    public static List<Meeting> meetings;
    public static List<Room> rooms;
    public static List<Vip> vips;
    public static List<Participant> participants;

    public static StartListsApiService service = new DummyStartListsApiService();

    public static StartListsApiService getStartListApiService() { return service; }

    public static StartListsApiService getNewInstanceApiService() {
        return new StartListsApiService() {
            @Override
            public List<Coworker> getCoworkers() {
                return coworkers;
            }

            @Override
            public List<Room> getRooms() {
                return rooms;
            }

            @Override
            public List<Vip> getVips() {
                return vips;
            }

            @Override
            public List<Meeting> getMeetings() {
                return meetings;
            }

            @Override
            public List<Participant> getParticipants() {
                return participants;
            }

            @Override
            public void createMeeting(Meeting meeting) {
            }
            @Override
            public void deleteMeeting(Meeting meeting) {
            }

            @Override
            public void createParticipant(Participant participants) {  // souligné en rouge et ampoule rouge, sur ampoule, ... ?
            }
            @Override
            public void deleteParticipant(Participant participants) {
            }


        };
    }

}
