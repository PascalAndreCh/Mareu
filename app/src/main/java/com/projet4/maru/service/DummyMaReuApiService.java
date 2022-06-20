package com.projet4.maru.service;

import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Participant;
import com.projet4.maru.model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class DummyMaReuApiService implements MaReuApiService {

    private List<Room> rooms = DummyMaReuGenerator.generateRooms();
    private List<Meeting> meetings = DummyMaReuGenerator.generateMeetings();
    private List<Participant> participants = DummyMaReuGenerator.generateParticipants();

    Calendar dateJ = GregorianCalendar.getInstance();

    /**
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     */
    @Override
    public List<Meeting> getMeetings() {
        return this.meetings;
    }

    @Override
    public void setMeeting(int i, Meeting meeting) {meetings.set(i, meeting);}

    /**
     */
    @Override
    public void createParticipant(Participant participant) {
        participants.add(participant);
    }

    /**
     */
    @Override
    public void deleteParticipant(Participant participant) {
        participants.remove(participant);
    }

    /**
     */
    @Override
    public List<Participant> getParticipants() {
        return participants;
    }


    @Override
    public List<Room> getRooms() {
        return this.rooms;
    }



    /**
     * etabli la liste des réunions pour une salle donnée (filtre réunions par salle)
     */
    @Override
    public List<Meeting> getMeetingsByRoom(int room) {
        List<Meeting> meetingsFilteredByRoom = new ArrayList<>();
        for (Meeting meeting : this.meetings) {
            if (meeting.getIdRoom() == room) {
                meetingsFilteredByRoom.add(meeting);
            }
        }
        return meetingsFilteredByRoom;
    }


    /**
     * établi la liste des réunions pour une date donnée (filtre réunions par date)
     */
    public List<Meeting> getMeetingsByDate(Calendar date) {
        List<Meeting> meetingsFilteredByDate = new ArrayList<>();
        for (Meeting meeting : this.meetings) {
            Calendar date2 = meeting.getTimeStart();
            if (date.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH) && date.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
                    && date.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
                meetingsFilteredByDate.add(meeting);
            }
        }
        return meetingsFilteredByDate;
    }


    /**
     * établi la liste des salles utilisées une date donnée (filtre salles par date)
     */
    public List<Room> getRoomsByDates(Calendar date) {
        List<Room> roomFilteredByDates = new ArrayList<>();
        for (Meeting meeting : this.meetings) {
            Calendar date2 = meeting.getTimeStart();
            if (date.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH) && date.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
                    && date.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
                for (Room room : this.rooms) {
                    if (room.getIdRoom() == meeting.getIdRoom()) {
                        roomFilteredByDates.add(room);
                    }
                }
            }
        }
        return roomFilteredByDates;
    }

    /**
     * Teste si la salle choisie est disponible pour la période souhaitée
     */
    public boolean roomIsFree(long idRoom, Calendar mMeetingDateStart, Calendar mMeetingDateEnd, long idMeet) {
        for (Meeting meet : this.meetings) {
             if (idRoom == meet.getIdRoom()) {
                if (mMeetingDateEnd.before(meet.getTimeStart()) || mMeetingDateEnd.equals(meet.getTimeStart())) {
                    continue;
                } else if (mMeetingDateStart.after(meet.getTimeEnd()) || mMeetingDateStart.equals(meet.getTimeEnd())) {
                    continue;
                } else {
                    if (meet.getId() != idMeet) {
                       return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Teste si la personne sélectionnée n'est pas déjà prise par une autre réunion
     */
    public boolean participantIsFree(long idParticipant, Calendar mMeetingDateStart, Calendar mMeetingDateEnd, long idMeet) {
        for (Meeting meeting : this.meetings) {
            participants = (List<Participant>) meeting.getParticipants();
            for (Participant participant : participants) {
                if (idParticipant == participant.getId()) {
                    if (mMeetingDateEnd.before(meeting.getTimeStart()) || mMeetingDateEnd.equals(meeting.getTimeStart())) {
                        continue;
                    } else if (mMeetingDateStart.after(meeting.getTimeEnd()) || mMeetingDateStart.equals(meeting.getTimeEnd())) {
                        continue;
                    } else {
                        if (meeting.getId() != idMeet) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Etabli la liste des salles libres pour la période souhaitée
     */
    public List<Room> listRoomsFree(Calendar mMeetingDateStart, Calendar mMeetingDateEnd) {
        List<Room> roomFree = new ArrayList<Room>();
        for (Room room : this.rooms) {
            if (roomIsFree(room.getIdRoom(), mMeetingDateStart, mMeetingDateEnd, 0)) {  // crée une liste contenant toutes les salles libres pour le créneau choisie
                roomFree.add(room);
            }
        }
        return roomFree;
    }

    /**
     * teste si la salle n'est pas surdimensionnée, ce qui aurait pour effet de la monopoliser inutilement
     * au détriment d'une autre réunion ayant plus de participants
     */
    public long roomIsBetter(long idRoom, int capacityPeople, int nbPeople, Calendar mMeetingDateStart, Calendar mMeetingDateEnd) {
        long idRoomIsbetter = 0;
        int nbMinimumPeople = 99;
        List<Room> roomf = listRoomsFree(mMeetingDateStart, mMeetingDateEnd);
        if (roomf.size() == 0) {
            return idRoom;
        }
        for (Room room : roomf) {
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
    public boolean roomToSmall(long idRoom, int nbPeople) {
        for (Room room : this.rooms) {
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
        List<Meeting> meetings = new ArrayList<>(this.meetings);
        for (Meeting meeting : meetings) {
            if (meeting.getTimeEnd().before(dateJ)) {
                deleteMeeting(meeting);
            }
        }
    }

    /**
     * Sélectionne toutes les salles ayant la capacité d'accueillir la réunion
     */
    public List<Room> bigEnoughRooms(int nbPeople) {
        List<Room> roomBigEnough = new ArrayList<Room>();
        for (Room room : this.rooms) {
            if (room.getMaximumParticipantRoom() >= nbPeople) {
                roomBigEnough.add(room);
            }
        }
        return roomBigEnough;
    }


    /**
     * Teste si la date et l'heure saisie sont antérieures à la date et l'heure actuelle
     */
    public boolean inputDateSuperiorToThisDay(Calendar inputDate) {
        if (dateJ.before(inputDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Teste si la date et l'heure de fin de réunion est bien supérieure à la date et l'heure de début de réunion
     */
    public boolean endDateSuperiorToStartDate(Calendar startDate, Calendar endDate) {
        return startDate.before(endDate);
    }

    /**
     * Calucle la date de fin en fonction de la date de départ et de la durée de la réunion
     */
    public Calendar endDateMeeting(Calendar dateStart, int duration) {
        Calendar dateEnd = GregorianCalendar.getInstance();
        int k3 = dateStart.get(Calendar.YEAR);
        int k4 = dateStart.get(Calendar.MONTH);
        int k5 = dateStart.get(Calendar.DAY_OF_MONTH);
        int k6 = dateStart.get(Calendar.HOUR_OF_DAY);
        int k7 = dateStart.get(Calendar.MINUTE);
        dateEnd.set(k3,k4,k5,k6,k7,0);
        dateEnd.add(Calendar.MINUTE, duration);
        return dateEnd;
     }


    /**
     */
     public int dureeMinutes (Calendar dateStart, Calendar dateEnd) {
         int h4 = dateStart.get(Calendar.HOUR_OF_DAY);
         int h5 = dateStart.get(Calendar.MINUTE);
         int h9 = dateEnd.get(Calendar.HOUR_OF_DAY);
         int h10 = dateEnd.get(Calendar.MINUTE);
         int m1 = (h4*60) + h5;
         int m2 = (h9*60) + h10;
         int mm = m2 - m1;
         if (mm > 59) {
             int hh1 = mm / 60;
             int mm1 = mm - (hh1*60);
                      } else {
             int hh1 = 0;
             int mm1 = mm;
         }
         return mm;
         }

    /**
     * Calcule la duré en heures et minutes
     */
    public Calendar duree (Calendar dateStart, Calendar dateEnd) {
        int h1 = dateStart.get(Calendar.YEAR);
        int h2 = dateStart.get(Calendar.MONTH);
        int h3 = dateStart.get(Calendar.DAY_OF_MONTH);
        int h4 = dateStart.get(Calendar.HOUR_OF_DAY);
        int h5 = dateStart.get(Calendar.MINUTE);
        int h9 = dateEnd.get(Calendar.HOUR_OF_DAY);
        int h10 = dateEnd.get(Calendar.MINUTE);
        int hh1 = 0;
        int mm1 = 0;
        int m1 = h4*60 + h5;
        int m2 = h9*60 + h10;
        int mm = m2 - m1;
        if (mm > 59) {
            hh1 = mm / 60;
            mm1 = mm - (hh1*60);
        } else {
            hh1 = 0;
            mm1 = mm;
        }
        Calendar duree = Calendar.getInstance();
        duree.set(h1, h2, h3, hh1, mm1, 0 );
        return duree;
    }

    /**
     * Transforme la date en String
     */
    public String dateToString (Calendar dateX) {
        String stringDate = "";
        SimpleDateFormat format0 = new SimpleDateFormat("dd-MM-yyyy HH:mm:00", Locale.FRANCE);
        stringDate = (format0.format(dateX.getTime()));
        return stringDate;
    }

    /**
     * Transforme un String contenant ne contenant qu'une une date, en date
     */
    public Calendar stringToDate (String stringDate) throws ParseException {
        Calendar dateX = GregorianCalendar.getInstance();
        SimpleDateFormat format0 = new SimpleDateFormat("dd-MM-yyyy HH:mm:00", Locale.FRANCE);
        dateX.setTime(format0.parse(stringDate));
        return dateX;
    }

}
