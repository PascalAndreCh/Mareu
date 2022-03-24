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
     * @param date
     * @return
     */
    List<Meeting> getMeetingsByDate(Date date);

    /**
     *
     * @param date
     * @return
     */
    List<Room> getRoomsByDates (Date date);

    /**
     *
     * @param idRoom
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    boolean roomIsFree(long idRoom, Date mMeetingDateStart, Date mMeetingDateEnd);


    /**
     *
     * @param idParticipant
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    boolean participantIsFree(long idParticipant, Date mMeetingDateStart, Date mMeetingDateEnd);

    /**
     *
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    List<Room> listRoomsFree(Date mMeetingDateStart, Date mMeetingDateEnd);

    /**
     *
     * @param idRoom
     * @param capacityPeople
     * @param nbPeople
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    long roomIsBetter(long idRoom, int capacityPeople, int nbPeople, Date mMeetingDateStart, Date mMeetingDateEnd);

    /**
     *
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
     *
     * @param nbPeople
     * @return
     */
    List<Room> bigEnoughRooms(int nbPeople);

    /**
     *
     * @param inputDate
     * @return
     */
    boolean inputDateSuperiorToThisDay(Date inputDate);

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    boolean endDateSuperiorToStartDate(Date startDate, Date endDate);

    /**
     *
     * @param startDate
     * @param duration
     * @return
     */
    Date endDateMeeting(Date startDate, int duration);


}
