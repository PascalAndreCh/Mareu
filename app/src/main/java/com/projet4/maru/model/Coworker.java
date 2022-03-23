package com.projet4.maru.model;

public class Coworker extends Person {

    private String department;

    private String function;

    /**
     *
     * @param id
     * @param name
     * @param mailAddresses
     * @param department
     * @param function
     */
    public Coworker(long id, String name, String mailAddresses, String department, String function) {
        super(id, name, mailAddresses);
        this.department = department;
        this.function = function;
    }

    public String getDepartment() {
        return department;
    }

    public String getFunction() {
        return function;
    }

}
