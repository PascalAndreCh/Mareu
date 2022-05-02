package com.projet4.maru.model;

import java.io.Serializable;
import java.util.Objects;

public class Participant implements Serializable {

    private String attachment;

    private String job;

    private long id;

    private String name;

    private String mailAddresses;


    /**
     * @param id
     * @param name
     * @param mailAddresses
     * @param attachment
     * @param job
     */
    public Participant(long id, String name, String mailAddresses, String attachment, String job) {
        this.attachment = attachment;
        this.job = job;
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(attachment, that.attachment) && Objects.equals(job, that.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachment, job);
    }



}
