package com.projet4.maru.service;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;

import java.util.List;

public interface StartListsApiService {

    List<Coworker> getCoworkers();
    List<Room> getRooms();
    List<Vip> getVips();
    List<Meeting> getMeetings();
//    List<Participant> getParticiapnts();


    void createMeeting (Meeting meeting);

    void deleteMeeting (Meeting meeting);

//    void createParticipant (Participant participant);

//    void deleteParticipant (Participant participant);

}
