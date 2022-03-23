package com.projet4.maru.model;

public class Person {

    private long id;

    private String name;

    private String mailAddresses;

    /**
     *
     * @param id
     * @param name
     * @param mailAddresses
     */
    public Person(long id, String name, String mailAddresses) {
        this.id = id;
        this.name = name;
        this.mailAddresses = mailAddresses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddresses() {
        return mailAddresses;
    }

    public void setMailAddresses(String mailAddresses) {
        this.mailAddresses = mailAddresses;
    }

}
