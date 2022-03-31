package com.projet4.maru.model;

import java.util.Objects;

public class Participant extends Person {

    private String attachment;

    private String job;

    /**
     * @param id
     * @param name
     * @param mailAddresses
     * @param attachment
     * @param job
     */
    public Participant(long id, String name, String mailAddresses, String attachment, String job) {
        super(id, name, mailAddresses);
        this.attachment = attachment;
        this.job = job;
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
