package com.projet4.maru.model;

import java.util.Date;

public class Reunion {

    private long idReunion;

    private long idReunionRoom;

    //private Date dateHourReunionStart;

    //private Date dateHourReunionEnd;

    private int yearReunion;
    private int monthReunion;
    private int dayReunion;
    private int hourReunionStart;
    private int minuteReunionStart;
    private int hourReunionEnd;
    private int minuteReunionEnd;


    private String titleReunion;

    private String descriptionReunion;

    private long idPeopleReunion01;

    private long idPeopleReunion02;

    private long idPeopleReunion03;

    private long idPeopleReunion04;

    private long idPeopleReunion05;

    private long idPeopleReunion06;

    private long idPeopleReunion07;

    private long idPeopleReunion08;

    private long idPeopleReunion09;

    private long idPeopleReunion10;

    public Reunion(long idReunion, long idReunionRoom, int yearReunion, int monthReunion, int dayReunion, int hourReunionStart, int minuteReunionStart, int hourReunionEnd, int minuteReunionEnd, String titleReunion, String descriptionReunion, long idPeopleReunion01, long idPeopleReunion02, long idPeopleReunion03, long idPeopleReunion04, long idPeopleReunion05, long idPeopleReunion06, long idPeopleReunion07, long idPeopleReunion08, long idPeopleReunion09, long idPeopleReunion10) {
        this.idReunion = idReunion;
        this.idReunionRoom = idReunionRoom;
        this.yearReunion = yearReunion;
        this.monthReunion = monthReunion;
        this.dayReunion = dayReunion;
        this.hourReunionStart = hourReunionStart;
        this.minuteReunionStart = minuteReunionStart;
        this.hourReunionEnd = hourReunionEnd;
        this.minuteReunionEnd = minuteReunionEnd;
        this.titleReunion = titleReunion;
        this.descriptionReunion = descriptionReunion;
        this.idPeopleReunion01 = idPeopleReunion01;
        this.idPeopleReunion02 = idPeopleReunion02;
        this.idPeopleReunion03 = idPeopleReunion03;
        this.idPeopleReunion04 = idPeopleReunion04;
        this.idPeopleReunion05 = idPeopleReunion05;
        this.idPeopleReunion06 = idPeopleReunion06;
        this.idPeopleReunion07 = idPeopleReunion07;
        this.idPeopleReunion08 = idPeopleReunion08;
        this.idPeopleReunion09 = idPeopleReunion09;
        this.idPeopleReunion10 = idPeopleReunion10;
    }

    public long getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(long idReunion) {
        this.idReunion = idReunion;
    }

    public long getIdReunionRoom() {
        return idReunionRoom;
    }

    public void setIdReunionRoom(long idReunionRoom) {
        this.idReunionRoom = idReunionRoom;
    }

    public int getYearReunion() {
        return yearReunion;
    }

    public void setYearReunion(int yearReunion) {
        this.yearReunion = yearReunion;
    }

    public int getMonthReunion() {
        return monthReunion;
    }

    public void setMonthReunion(int monthReunion) {
        this.monthReunion = monthReunion;
    }

    public int getDayReunion() {
        return dayReunion;
    }

    public void setDayReunion(int dayReunion) {
        this.dayReunion = dayReunion;
    }

    public int getHourReunionStart() {
        return hourReunionStart;
    }

    public void setHourReunionStart(int hourReunionStart) {
        this.hourReunionStart = hourReunionStart;
    }

    public int getMinuteReunionStart() {
        return minuteReunionStart;
    }

    public void setMinuteReunionStart(int minuteReunionStart) {
        this.minuteReunionStart = minuteReunionStart;
    }

    public int getHourReunionEnd() {
        return hourReunionEnd;
    }

    public void setHourReunionEnd(int hourReunionEnd) {
        this.hourReunionEnd = hourReunionEnd;
    }

    public int getMinuteReunionEnd() {
        return minuteReunionEnd;
    }

    public void setMinuteReunionEnd(int minuteReunionEnd) {
        this.minuteReunionEnd = minuteReunionEnd;
    }

    public String getTitleReunion() {
        return titleReunion;
    }

    public void setTitleReunion(String titleReunion) {
        this.titleReunion = titleReunion;
    }

    public String getDescriptionReunion() {
        return descriptionReunion;
    }

    public void setDescriptionReunion(String descriptionReunion) {
        this.descriptionReunion = descriptionReunion;
    }

    public long getIdPeopleReunion01() {
        return idPeopleReunion01;
    }

    public void setIdPeopleReunion01(long idPeopleReunion01) {
        this.idPeopleReunion01 = idPeopleReunion01;
    }

    public long getIdPeopleReunion02() {
        return idPeopleReunion02;
    }

    public void setIdPeopleReunion02(long idPeopleReunion02) {
        this.idPeopleReunion02 = idPeopleReunion02;
    }

    public long getIdPeopleReunion03() {
        return idPeopleReunion03;
    }

    public void setIdPeopleReunion03(long idPeopleReunion03) {
        this.idPeopleReunion03 = idPeopleReunion03;
    }

    public long getIdPeopleReunion04() {
        return idPeopleReunion04;
    }

    public void setIdPeopleReunion04(long idPeopleReunion04) {
        this.idPeopleReunion04 = idPeopleReunion04;
    }

    public long getIdPeopleReunion05() {
        return idPeopleReunion05;
    }

    public void setIdPeopleReunion05(long idPeopleReunion05) {
        this.idPeopleReunion05 = idPeopleReunion05;
    }

    public long getIdPeopleReunion06() {
        return idPeopleReunion06;
    }

    public void setIdPeopleReunion06(long idPeopleReunion06) {
        this.idPeopleReunion06 = idPeopleReunion06;
    }

    public long getIdPeopleReunion07() {
        return idPeopleReunion07;
    }

    public void setIdPeopleReunion07(long idPeopleReunion07) {
        this.idPeopleReunion07 = idPeopleReunion07;
    }

    public long getIdPeopleReunion08() {
        return idPeopleReunion08;
    }

    public void setIdPeopleReunion08(long idPeopleReunion08) {
        this.idPeopleReunion08 = idPeopleReunion08;
    }

    public long getIdPeopleReunion09() {
        return idPeopleReunion09;
    }

    public void setIdPeopleReunion09(long idPeopleReunion09) {
        this.idPeopleReunion09 = idPeopleReunion09;
    }

    public long getIdPeopleReunion10() {
        return idPeopleReunion10;
    }

    public void setIdPeopleReunion10(long idPeopleReunion10) {
        this.idPeopleReunion10 = idPeopleReunion10;
    }
}


