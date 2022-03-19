package com.projet4.maru.service;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;

import java.util.List;

public class DummyStartListsApiService implements StartListsApiService{

    private List<Coworker> coworkers = DummyStartListsGenerator.generateCoworkers();
    private List<Room> rooms = DummyStartListsGenerator.generateRooms();
    private List<Vip> vips = DummyStartListsGenerator.generateVips();
    private List<Meeting> meetings = DummyStartListsGenerator.generateMeetings();
//    private List<Participant> participants1 = DummyStartListsGenerator.generateParticipants1();
//    private List<Participant> participants2 = DummyStartListsGenerator.generateParticipants2();

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

//    @Override
//    public List<Participant> getParticipants1() {
//        return participants1;
//    }

//    @Override
//    public List<Participant> getParticipants2() {
//        return participants2;
//    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) { meetings.remove(meeting); }

    /**
     *
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }


    /**
     * {@inheritDoc}
     */
//    @Override
//    public void deleteParticipant(Participant participant) { participants.remove(participant); }

    /**
     *
     * @param participant
     */
//    @Override
//    public void createParticipant(Participant participant) {
//        participants.add(participant);
//    }

}
