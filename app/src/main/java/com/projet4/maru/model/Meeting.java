package com.projet4.maru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meeting {

    private long id;

    private long idRoom;

    private Date timeStart;

    private Date timeEnd;

    private long duration;

    private String title;

    private String description;

    private List<Participant> participants;

    public Meeting(long id, long idRoom, Date timeStart, Date timeEnd, long duration, String title, String description, List<Participant> participants) {
        this.id = id;
        this.idRoom = idRoom;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.title = title;
        this.duration = duration;
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

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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

    public List getParticipants() {
        return participants;
    }

    public void setParticipants(List participants) {
        this.participants = participants;
    }
}

