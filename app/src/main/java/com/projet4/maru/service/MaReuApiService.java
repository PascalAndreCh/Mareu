package com.projet4.maru.service;


import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;

import java.util.Date;
import java.util.List;

public interface MaReuApiService {


    void createMeeting (Meeting meeting);

    void deleteMeeting (Meeting meeting);

    List<Meeting> getMeetings ();

    List<Participant> getParticipants ();

    List<Room> getRooms ();

    /**
     *
     * @param room
     * @return
     */
    List<Meeting> getMeetingsByRoom(Room room);

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<Meeting> getMeetingsByDate(Date startDate, Date endDate);

    /**
     *
     * @param starDate
     * @param endDate
     * @return
     */
    List<Room> getRoomsByDates (Date starDate, Date endDate);


}
