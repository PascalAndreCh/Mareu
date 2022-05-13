package com.projet4.maru.service;


import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;

import java.util.Calendar;
import java.util.List;

public interface MaReuApiService {



    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);


    List<Meeting> getMeetings();


    List<Participant> getParticipants();

    void setMeeting(int i, Meeting meeting);

    void deleteParticipant(Participant participant);

    /**
     *
     * @param participant
     */
    void createParticipant(Participant participant);


    List<Room> getRooms();


    /**
     * @param room
     * @return
     */
    List<Meeting> getMeetingsByRoom(int room);

    /**
     * @param date
     * @return
     */
    List<Meeting> getMeetingsByDate(Calendar date);

     /**
     *
     * @param date
     * @return
     */
    List<Room> getRoomsByDates(Calendar date);

    /**
     * @param idRoom
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    boolean roomIsFree(long idRoom, Calendar mMeetingDateStart, Calendar mMeetingDateEnd);


    /**
     * @param idParticipant
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    boolean participantIsFree(long idParticipant, Calendar mMeetingDateStart, Calendar mMeetingDateEnd);

    /**
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    List<Room> listRoomsFree(Calendar mMeetingDateStart, Calendar mMeetingDateEnd);

    /**
     * @param idRoom
     * @param capacityPeople
     * @param nbPeople
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    long roomIsBetter(long idRoom, int capacityPeople, int nbPeople, Calendar mMeetingDateStart, Calendar mMeetingDateEnd);

    /**
     * @param idRoom
     * @param nbPeople
     * @return
     */
    boolean roomToSmall(long idRoom, int nbPeople);

    /**
     *
     */
    void deleteObsoleteMeetings();

    /**
     * @param nbPeople
     * @return
     */
    List<Room> bigEnoughRooms(int nbPeople);

    /**
     * @param inputDate
     * @return
     */
    boolean inputDateSuperiorToThisDay(Calendar inputDate);

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    boolean endDateSuperiorToStartDate(Calendar startDate, Calendar endDate);

    /**
     * @param startDate
     * @param duration
     * @return
     */
    Calendar endDateMeeting(Calendar startDate, int duration);

    int dureeMinutes (Calendar startDate, Calendar endDate);

    Calendar duree (Calendar startDate, Calendar endDate);

     void impDate (Calendar dateX) ;

     String dateToString(Calendar dateX);

     Calendar stringToDate(String stringDate);

}
