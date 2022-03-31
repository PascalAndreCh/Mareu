package com.projet4.maru.model;

import java.util.Objects;

public class Coworker extends Person {

    private String department;

    private String function;

    /**
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coworker coworker = (Coworker) o;
        return Objects.equals(department, coworker.department) && Objects.equals(function, coworker.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department, function);
    }
}
