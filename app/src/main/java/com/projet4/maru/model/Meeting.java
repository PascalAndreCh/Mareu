package com.projet4.maru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meeting {

    private long id;

    private long idRoom;

    private Date timeStart;

    private Date timeEnd;

    private String title;

    private String description;

    private List<Participant> participants;

    /**
     *
     * @param id
     * @param idRoom
     * @param timeStart
     * @param timeEnd
     * @param title
     * @param description
     * @param participants
     */
    public Meeting(long id, long idRoom, Date timeStart, Date timeEnd, String title, String description, List<Participant> participants) {
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


