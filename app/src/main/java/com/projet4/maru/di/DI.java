package com.projet4.maru.di;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;
import com.projet4.maru.service.DummyStartListsApiService;
import com.projet4.maru.service.StartListsApiService;

import java.util.List;

public class DI {


    public static StartListsApiService service = new DummyStartListsApiService();

    public static StartListsApiService getStartListApiService() { return service; }

    public static StartListsApiService getNewInstanceApiService() {
        return new StartListsApiService() {
            @Override
            public List<Coworker> getCoworkers() {
                return null;
            }

            @Override
            public List<Room> getRooms() {
                return null;
            }

            @Override
            public List<Vip> getVips() {
                return null;
            }

            @Override
            public List<Meeting> getMeetings() {
                return null;
            }

            @Override
            public void createMeeting(Meeting meeting) {

            }

            @Override
            public void deleteMeeting(Meeting meeting) {

            }
        };
    }

}
