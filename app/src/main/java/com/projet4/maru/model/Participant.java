package com.projet4.maru.model;

public class Participant {

    private long id;

    private String name;

    private String attachment;

    private String function;

    private String mailAddresses;

    /**
     *
     * @param id
     * @param name
     * @param attachment
     * @param function
     * @param mailAddresses
     */
    public Participant(long id, String name, String attachment, String function, String mailAddresses) {
        this.id = id;
        this.name = name;
        this.attachment = attachment;
        this.function = function;
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getMailAddresses() {
        return mailAddresses;
    }

    public void setMailAddresses(String mailAddresses) {
        this.mailAddresses = mailAddresses;
    }

}
