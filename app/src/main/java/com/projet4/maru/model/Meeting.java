package com.projet4.maru.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Meeting {

    private long id;

    private long idRoom;

    private Calendar timeStart;

    private Calendar timeEnd;

    private String title;

    private String description;

    private List<Participant> participants;

    /**
     * @param id
     * @param idRoom
     * @param timeStart
     * @param timeEnd
     * @param title
     * @param description
     * @param participants
     */
    public Meeting(long id, long idRoom, Calendar timeStart, Calendar timeEnd, String title, String description, List<Participant> participants) {
        this.id = id;
        this.idRoom = idRoom;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.title = title;
        this.description = description;
        this.participants = participants;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public Calendar getTimeStart() { return timeStart; }

    public void setTimeStart(Calendar timeStart) {
        this.timeStart = timeStart;
    }

    public Calendar getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Calendar timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Participant> getParticipants() {
        return  participants;
    }

    public void setParticipants(List participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id && idRoom == meeting.idRoom && Objects.equals(timeStart, meeting.timeStart) && Objects.equals(timeEnd, meeting.timeEnd) && Objects.equals(title, meeting.title) && Objects.equals(description, meeting.description) && Objects.equals(participants, meeting.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRoom, timeStart, timeEnd, title, description, participants);
    }
}


