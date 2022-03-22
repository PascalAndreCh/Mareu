package com.projet4.maru.service;

import com.projet4.maru.model.Coworker;
import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;
import com.projet4.maru.model.Vip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyMaReuApiService implements MaReuApiService {

    private List<Room> rooms = DummyMaReuGenerator.generateRooms();
    private List<Meeting> meetings = DummyMaReuGenerator.generateMeetings();
    private List<Participant> participants = DummyMaReuGenerator.generateParticipant();

    //    LocalDateTime thisDay = LocalDateTime.now();
    Date dateJ = new Date();


    @Override
    public void createMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
    this.meetings.remove(meeting);
    }

    @Override
    public List<Meeting> getMeetings() {
        return this.meetings;
    }

    @Override
    public List<Participant> getParticipants() {
        return this.participants;
    }

    @Override
    public List<Room> getRooms() {
        return this.rooms;
    }

    /**
     * etabli la liste des meetings pour une salle donnée (filtre par salle)
     * @param room
     * @return
     */
    @Override
    public List<Meeting> getMeetingsByRoom(Room room) {
        List<Meeting> meetingsFilteredByRoom = new ArrayList<>();
        for (Meeting meeting : this.meetings) {
            if (meeting.getIdRoom() == room.getIdRoom()) {
                meetingsFilteredByRoom.add(meeting);
            }
        }
        return meetingsFilteredByRoom;
    }

    public List<Meeting> getMeetingsByDate(Date startDate, Date endDate) {
       List<Meeting> meetingsFilteredByDate = new ArrayList<>(); // à refaire, faux
       for (Meeting meeting : this.meetings) {
          if (roomIsFree(meeting.getIdRoom(), meeting.getTimeStart(), meeting.getTimeEnd()) == true) {
              meetingsFilteredByDate.add(meeting);
          }
       }
        return meetingsFilteredByDate;
    }


    /**
     * Teste si la salle choisie est disponible pour la période souhaitée
     */
    public List<Meeting> mMeeting; // sera à supprimer, le liste sera rempli ailleur, ici pour cohérence du code qui suit
    /**
     *
     * @param idRoom
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    public boolean roomIsFree(long idRoom, Date mMeetingDateStart, Date mMeetingDateEnd) {
        for (Meeting meeting: mMeeting) {
            if (idRoom == meeting.getIdRoom()){
                if (meeting.getTimeStart().before(mMeetingDateStart) && mMeetingDateStart.before(meeting.getTimeEnd())) {
//                    return i.getDateHourMeetingStart();
//                    return i.getDateHourMeetingEnd();
                    return false;
                }
                if (meeting.getTimeEnd().after(mMeetingDateEnd) && mMeetingDateEnd.after(meeting.getTimeStart())) {
//                    return i.getDateHourMeetingStart();
//                    return i.getDateHourMeetingEnd();
                    return false;
                }
            }
        }
        return true;
    }

     /**
     * Etabli la liste des salles libres pour la période souhaitée
     */
    public List<Room> roomFree = new ArrayList<Room>();
    /**
     *
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    public List<Room> listRoomsFree(Date mMeetingDateStart, Date mMeetingDateEnd) {
        roomFree.clear();
        for (Room room: this.rooms) {
            if (roomIsFree(room.getIdRoom(), mMeetingDateStart, mMeetingDateEnd) == true) {  // crée une liste contenant toutes les salles libres pour le créneau choisie
                roomFree.add(room);
            }
        }
        return roomFree;
    }

    /**
     * teste si la salle n'est pas surdimensionnée, ce qui aurait pour effet de la monopoliser inutilement
     * au détriment d'une autre réunion ayant plus de participants
     */
    /**
     *
     * @param idRoom
     * @param capacitePeople
     * @param nbPeople
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    public long roomIsBetter(long idRoom, int capacitePeople, int nbPeople, Date mMeetingDateStart, Date mMeetingDateEnd) {
        long idRoomIsbetter = 0;
        int nbMinimumPeople = 99;
        listRoomsFree(mMeetingDateStart, mMeetingDateEnd);
        for (Room room: roomFree){
            if (room.getMaximumParticipantRoom() >= nbPeople) {
                if (room.getMaximumParticipantRoom() < nbMinimumPeople) {
                    nbMinimumPeople = room.getMaximumParticipantRoom();        // on conserve les données de la salle la plus petite
                    // pouvant accueillir la réunion
                    idRoomIsbetter = room.getIdRoom();
                }
            }
        }
        if (capacitePeople == nbMinimumPeople) {    // si la salle récupérée a la même capacité que la salle choisie, on conserve celle choisie
            return idRoom;
        } else {
            return idRoomIsbetter;
        }
    }

    /**
     * Teste si la salle n'est pas trop petite
     */
    /**
     *
     * @param idRoom
     * @param nbPeople
     * @return
     */
    public boolean roomToSmall(long idRoom, int nbPeople) {
        for (Room room: this.rooms)  {
            if (room.getIdRoom() == idRoom) {
                if (room.getMaximumParticipantRoom() >= nbPeople) {   // si la salle est suffisemment grande, on renvoie true, sinon on renvoi false
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false; // ne doit pas arriver, voir pour message d'erreur, salle non trouvée ?
    }

    /**
     * suppression des réunions obsoletes (à exécuter une fois à chaque lancement de l'application
     */
    public void deleteObsoleteMeetings() {
        for (Meeting meeting: mMeeting) {
            if (meeting.getTimeEnd().before(dateJ)) {
//                mMeeting.deleteMeeting(m);
                mMeeting.remove(meeting);
            }
        }
    }

    /**
     * Sélectionne toutes les salles ayant la capacité d'accueillir la réunion
     */
    public List<Room> roomBigEnough = new ArrayList<Room>();
    /**
     *
     * @param nbPeople
     * @return
     */
    public List<Room> bigEnoughRooms(int nbPeople) {
        roomBigEnough.clear();
        for (Room room: this.rooms) {
            if (room.getMaximumParticipantRoom() >= nbPeople) {
                roomBigEnough.add(room);
            }
        }
//    Collections.sort(roomBigEnough, roomBigEnough.get().getMaximumParticipantRoom());
        return roomBigEnough;
    }

    /**
     * Teste si la date et l'heure saisie sont antérieures à la date et l'heure actuelle
     * @param inputDate
     * @return
     */
    public boolean inputDateSuperiorToThisDay(Date inputDate) {
        if (dateJ.before(inputDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Teste si la date et l'heure de fin de réunion est bien supérieure à la date et l'heure de début de réunion
     * @param startDate
     * @param endDate
     * @return
     */
    public boolean endDateSuperiorToStartDate(Date startDate, Date endDate) {
        return startDate.before(endDate);
    }


    @Override
    public List<Meeting> getMeetingsByDate(Date date) {
        return null;
    }

    @Override
    public List<Room> getRoomsByDates(Date starDate, Date endDate) {
        return null;
    }
}
