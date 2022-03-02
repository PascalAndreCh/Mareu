package com.projet4.maru.model;

public class Vip {

    private long idOutsidePeople;

    private String nameOutsidePeople;

    private String societyOutsidePeople;

    private String functionOutsidePeople;

    private String mailAddressOutsidePeople;

    public Vip(long idOutsidePeople, String nameOutsidePeople, String societyOutsidePeople, String functionOutsidePeople, String mailAddressOutsidePeople) {
        this.idOutsidePeople = idOutsidePeople;
        this.nameOutsidePeople = nameOutsidePeople;
        this.societyOutsidePeople = societyOutsidePeople;
        this.functionOutsidePeople = functionOutsidePeople;
        this.mailAddressOutsidePeople = mailAddressOutsidePeople;
    }

    public long getIdOutsidePeople() {
        return idOutsidePeople;
    }

    public void setIdOutsidePeople(long idOutsidePeople) {
        this.idOutsidePeople = idOutsidePeople;
    }

    public String getNameOutsidePeople() {
        return nameOutsidePeople;
    }

    public void setNameOutsidePeople(String nameOutsidePeople) {
        this.nameOutsidePeople = nameOutsidePeople;
    }

    public String getSocietyOutsidePeople() {
        return societyOutsidePeople;
    }

    public void setSocietyOutsidePeople(String societyOutsidePeople) {
        this.societyOutsidePeople = societyOutsidePeople;
    }

    public String getFunctionOutsidePeople() {
        return functionOutsidePeople;
    }

    public void setFunctionOutsidePeople(String functionOutsidePeople) {
        this.functionOutsidePeople = functionOutsidePeople;
    }

    public String getMailAddressOutsidePeople() {
        return mailAddressOutsidePeople;
    }

    public void setMailAddressOutsidePeople(String mailAddressOutsidePeople) {
        this.mailAddressOutsidePeople = mailAddressOutsidePeople;
    }

}

