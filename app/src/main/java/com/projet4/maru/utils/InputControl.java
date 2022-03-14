package com.projet4.maru.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.projet4.maru.model.Meeting;
import com.projet4.maru.model.Room;
import com.projet4.maru.service.FakeMeetingApiService;
import com.projet4.maru.service.RoomApiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class InputControl {

//    LocalDateTime thisDay = LocalDateTime.now();
    Date dateJ = new Date();

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
     * Teste si la salle choisie est disponible pour la période souhaitée
     */
    public FakeMeetingApiService mMeetingApiService;
    public boolean roomIsFree(long idRoom, Date mMeetingDateStart, Date mMeetingDateEnd) {

        for (Meeting i: mMeetingApiService.getMeetings()) {
            if (idRoom == i.getIdRoom()){
                if (i.getTimeStart().before(mMeetingDateStart) && mMeetingDateStart.before(i.getTimeEnd())) {
//                    return i.getDateHourMeetingStart();
//                    return i.getDateHourMeetingEnd();
                    return false;
                }
                if (i.getTimeEnd().after(mMeetingDateEnd) && mMeetingDateEnd.after(i.getTimeStart())) {
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
    public RoomApiService mRoomApiService;
    public List<Room> listRoomsFree(Date mMeetingDateStart, Date mMeetingDateEnd) {
        roomFree.clear();
        for (Room j: mRoomApiService.getRooms()) {
            if (roomIsFree(j.getIdRoom(), mMeetingDateStart, mMeetingDateEnd) == true) {  // crée une liste contenant toutes les salles libres pour le créneau choisie
                roomFree.add(j);
            }
        }
        return roomFree;
    }

    /**
     * Teste si la salle n'est pas trop petite
     */
    public boolean roomToSmall(long idRoom, int nbPeople) {
      for (Room k: mRoomApiService.getRooms())  {
          if (k.getIdRoom() == idRoom) {
              if (k.getMaximumParticipantRoom() >= nbPeople) {   // si la salle est suffisemment grande, on renvoie true, sinon on renvoi false
                  return true;
              } else {
                  return false;
              }
          }
      }
      return false; // ne doit pas arriver, voir pour message d'erreur, salle non trouvée ?
    }

    /**
     * teste si la salle n'est pas surdimensionnée, ce qui aurait pour effet de la monopoliser inutilement
     * au détriment d'une autre réunion ayant plus de participants
     */
    public long roomIsBetter(long idRoom, int capacitePeople, int nbPeople, Date mMeetingDateStart, Date mMeetingDateEnd) {
        long idRoomIsbetter = 0;
        int nbMinimumPeople = 99;
        listRoomsFree(mMeetingDateStart, mMeetingDateEnd);
        for (Room l: roomFree){
            if (l.getMaximumParticipantRoom() >= nbPeople) {
                if (l.getMaximumParticipantRoom() < nbMinimumPeople) {
                    nbMinimumPeople = l.getMaximumParticipantRoom();        // on conserve les données de la salle la plus petite
                                                                            // pouvant accueillir la réunion
                    idRoomIsbetter = l.getIdRoom();
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
     * suppression des réunions obsoletes (à exécuter une fois à chaque lancement de l'application
     */
    public void deleteObsoleteMeetings() {
        for (Meeting m: mMeetingApiService.getMeetings()) {
            if (m.getTimeEnd().before(dateJ)) {
                mMeetingApiService.deleteMeeting(m);
            }
        }
    }

    /**
     * Sélectionne toutes les salles ayant la capacité d'accueillir la réunion
     */
    public List<Room> roomBigEnough = new ArrayList<Room>();
    public List<Room> bigEnoughRooms(int nbPeople) {
        roomBigEnough.clear();
        for (Room n: mRoomApiService.getRooms()) {
            if (n.getMaximumParticipantRoom() >= nbPeople) {
                roomBigEnough.add(n);
            }
        }
//    Collections.sort(roomBigEnough, roomBigEnough.get().getMaximumParticipantRoom());
        return roomBigEnough;
    }

}
