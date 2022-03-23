package com.projet4.maru.model;

public class Vip extends Person {

    private String society;

    private String responsibility;


    public Vip(long id, String name, String mailAddresses, String society, String responsibility) {
        super(id, name, mailAddresses);
        this.society = society;
        this.responsibility = responsibility;
    }

    public String getSociety() {
        return society;
    }

     public String getResponsibility() {
        return responsibility;
    }

}

