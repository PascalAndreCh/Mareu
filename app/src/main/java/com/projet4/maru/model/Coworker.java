package com.projet4.maru.model;

public class Coworker extends Participant {

    /**
     *
     * @param id
     * @param name
     * @param attachment
     * @param function
     * @param mailAddresses
     */
    public Coworker(long id, String name, String attachment, String function, String mailAddresses) {
        super(id, name, attachment, function, mailAddresses);
    }
}
