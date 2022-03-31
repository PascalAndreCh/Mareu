package com.projet4.maru.model;

import java.util.Objects;

public class Person {

    private long id;

    private String name;

    private String mailAddresses;

    /**
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name) && Objects.equals(mailAddresses, person.mailAddresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mailAddresses);
    }
}
