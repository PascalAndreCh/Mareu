package com.projet4.maru.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DummyStartListsApiService implements StartListsApiService{

    private List<Coworker> coworkers = DummyStartListsGenerator.generateCoworkers();
    private List<Room> rooms = DummyStartListsGenerator.generateRooms();
    private List<Vip> vips = DummyStartListsGenerator.generateVips();
    private List<Meeting> meetings = DummyStartListsGenerator.generateMeetings();


    // comment définir et accéder à la liste des participants, code généré automatique
    private List<Participant> participants = new List<Participant>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<Participant> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] ts) {
            return null;
        }

        @Override
        public boolean add(Participant participant) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends Participant> collection) {
            return false;
        }

        @Override
        public boolean addAll(int i, @NonNull Collection<? extends Participant> collection) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Participant get(int i) {
            return null;
        }

        @Override
        public Participant set(int i, Participant participant) {
            return null;
        }

        @Override
        public void add(int i, Participant participant) {

        }

        @Override
        public Participant remove(int i) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<Participant> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<Participant> listIterator(int i) {
            return null;
        }

        @NonNull
        @Override
        public List<Participant> subList(int i, int i1) {
            return null;
        }
    };
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

    @Override
    public List<Participant> getParticipants() {return participants; }


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
    @Override
    public void deleteParticipant(Participant participant) { participants.remove(participant); }

    /**
     *
     * @param participant
     */
    @Override
    public void createParticipant(Participant participant) {
        participants.add(participant);
    }

}
