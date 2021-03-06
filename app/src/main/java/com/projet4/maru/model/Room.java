package com.projet4.maru.model;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

    /**
     * Identifier
     */
    private long idRoom;

    /**
     * Full name
     */
    private String nameRoom;

    /**
     * room number
     */
    private int numberRoom;

    /**
     * maximum number of people
     */
    private int maximumParticipantRoom;

    /**
     * location in the building
     */
    private int stageRoom;

    /**
     * @param idRoom
     * @param nameRoom
     * @param numberRoom
     * @param maximumParticipantRoom
     * @param stageRoom
     */
    public Room(long idRoom, String nameRoom, int numberRoom, int maximumParticipantRoom, int stageRoom) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.numberRoom = numberRoom;
        this.maximumParticipantRoom = maximumParticipantRoom;
        this.stageRoom = stageRoom;
    }

    /**
     * s'il s'avère nécessaire de créer une nouvelle salle (set))
     *
     * @return
     */

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public int getMaximumParticipantRoom() {
        return maximumParticipantRoom;
    }

    public void setMaximumParticipantRoom(int maximumParticipantRoom) {
        this.maximumParticipantRoom = maximumParticipantRoom;
    }

    public int getStageRoom() {
        return stageRoom;
    }

    public void setStageRoom(int stageRoom) {
        this.stageRoom = stageRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return idRoom == room.idRoom && numberRoom == room.numberRoom && maximumParticipantRoom == room.maximumParticipantRoom && stageRoom == room.stageRoom && Objects.equals(nameRoom, room.nameRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRoom, nameRoom, numberRoom, maximumParticipantRoom, stageRoom);
    }
}
