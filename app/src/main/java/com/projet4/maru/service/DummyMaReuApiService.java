package com.projet4.maru.service;


import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;

import java.util.ArrayList;
import java.util.Calendar;
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
        meetings.add(meeting);
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
     * etabli la liste des réunions pour une salle donnée (filtre réunions par salle)
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


    /**
     * établi la liste des réunions pour une date donnée (filtre réunions par date)
     * @param date
     * @return
     */
    public List<Meeting> getMeetingsByDate(Date date) {
        Calendar date1 = Calendar.getInstance();
        date1.setTime(date);
       List<Meeting> meetingsFilteredByDate = new ArrayList<>();
       for (Meeting meeting : this.meetings) {
           Calendar date2 = Calendar.getInstance();
           date2.setTime(meeting.getTimeStart());
          if (date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH) &&  date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
                  && date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)){
              meetingsFilteredByDate.add(meeting);
          }
       }
        return meetingsFilteredByDate;
    }


    /**
     * établi la liste des salles utilisées une date donnée (filtre salles par date)
     * @param date
     * @return
     */
    public List<Room> getRoomsByDates (Date date) {
        Calendar date1 = Calendar.getInstance();
        date1.setTime(date);
        List<Room> roomFilteredByDates = new ArrayList<>();
    for (Meeting meeting : this.meetings) {
        Calendar date2 = Calendar.getInstance();
        date2.setTime(meeting.getTimeStart());
        if (date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH) &&  date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
                && date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)){
            for (Room room : this.rooms) {
                if(room.getIdRoom() == meeting.getIdRoom()) {
                    roomFilteredByDates.add(room);
                }
            }
        }
    }
        return roomFilteredByDates;
    }


     /**
     * Teste si la salle choisie est disponible pour la période souhaitée
     *
     * @param idRoom
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    public boolean roomIsFree(long idRoom, Date mMeetingDateStart, Date mMeetingDateEnd) {
        for (Meeting meeting: this.meetings) {
            if (idRoom == meeting.getIdRoom()){
                if (meeting.getTimeStart().before(mMeetingDateStart) && mMeetingDateStart.before(meeting.getTimeEnd())) {
                    return false;
                }
                if (meeting.getTimeEnd().after(mMeetingDateEnd) && mMeetingDateEnd.after(meeting.getTimeStart())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Teste si la personne sélectionnée n'est pas déjà prise par une autre réunion
     *
     * @param idParticipant
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    public boolean participantIsFree(long idParticipant, Date mMeetingDateStart, Date mMeetingDateEnd){
        for (Meeting meeting: this.meetings) {
            meeting.getParticipants();
            for (Participant participant: participants){
                if (idParticipant == participant.getId()){
                    if (meeting.getTimeStart().before(mMeetingDateStart) && mMeetingDateStart.before(meeting.getTimeEnd())) {
                        return false;
                    }
                    if (meeting.getTimeEnd().after(mMeetingDateEnd) && mMeetingDateEnd.after(meeting.getTimeStart())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


     /**
     * Etabli la liste des salles libres pour la période souhaitée
     *
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    public List<Room> listRoomsFree(Date mMeetingDateStart, Date mMeetingDateEnd) {
        List<Room> roomFree = new ArrayList<Room>();
        for (Room room: this.rooms) {
            if (roomIsFree(room.getIdRoom(), mMeetingDateStart, mMeetingDateEnd) == true) {  // crée une liste contenant toutes les salles libres pour le créneau choisie
                roomFree.add(room);
            }
        }
        return roomFree; // tester si la liste n'ai pas vide au retour de la méthode
    }


    /**
     * teste si la salle n'est pas surdimensionnée, ce qui aurait pour effet de la monopoliser inutilement
     * au détriment d'une autre réunion ayant plus de participants
     */
    /**
     *
     * @param idRoom
     * @param capacityPeople
     * @param nbPeople
     * @param mMeetingDateStart
     * @param mMeetingDateEnd
     * @return
     */
    public long roomIsBetter(long idRoom, int capacityPeople, int nbPeople, Date mMeetingDateStart, Date mMeetingDateEnd) {
        long idRoomIsbetter = 0;
        int nbMinimumPeople = 99;
        List<Room> roomf = listRoomsFree(mMeetingDateStart, mMeetingDateEnd);
        if (roomf.size()==0) {
            return idRoom;
        }
        for (Room room: roomf){
            if (room.getMaximumParticipantRoom() >= nbPeople) {
                if (room.getMaximumParticipantRoom() < nbMinimumPeople) {
                    nbMinimumPeople = room.getMaximumParticipantRoom();        // on conserve les données de la salle la plus petite
                    // pouvant accueillir la réunion
                    idRoomIsbetter = room.getIdRoom();
                }
            }
        }
        if (capacityPeople == nbMinimumPeople) {    // si la salle récupérée a la même capacité que la salle choisie, on conserve celle choisie
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
        for (Meeting meeting: this.meetings) {
            if (meeting.getTimeEnd().before(dateJ)) {
                this.meetings.remove(meeting);
            }
        }
    }


    /**
     * Sélectionne toutes les salles ayant la capacité d'accueillir la réunion
     *
     * @param nbPeople
     * @return
     */
    public List<Room> bigEnoughRooms(int nbPeople) {
        List<Room> roomBigEnough = new ArrayList<Room>();
        for (Room room: this.rooms) {
            if (room.getMaximumParticipantRoom() >= nbPeople) {
                roomBigEnough.add(room);
            }
        }
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


    /**
     * Calucle la date de fin en fonction de la date de départ et de la durée de la réunion
     *
     * @param startDate
     * @param duration
     * @return
     */
    public Date endDateMeeting(Date startDate, int duration) {
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * duration));
        return endDate;
    }
}
