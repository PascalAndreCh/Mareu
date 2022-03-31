package com.projet4.maru.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vip vip = (Vip) o;
        return Objects.equals(society, vip.society) && Objects.equals(responsibility, vip.responsibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), society, responsibility);
    }
}

