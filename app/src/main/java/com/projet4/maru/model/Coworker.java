package com.projet4.maru.model;

public class Coworker {

    private long id;

    private String nameCoworker;

    private String departmentCoworker;

    private String functionCoworker;

    private String mailAddressCoworker;

    public Coworker(long id, String nameCoworker, String departmentCoworker, String functionCoworker, String mailAddressCoworker) {
        this.id = id;
        this.nameCoworker = nameCoworker;
        this.departmentCoworker = departmentCoworker;
        this.functionCoworker = functionCoworker;
        this.mailAddressCoworker = mailAddressCoworker;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCoworker() {
        return nameCoworker;
    }

    public void setNameCoworker(String nameCoworker) {
        this.nameCoworker = nameCoworker;
    }

    public String getDepartmentCoworker() {
        return departmentCoworker;
    }

    public void setDepartmentCoworker(String departmentCoworker) {
        this.departmentCoworker = departmentCoworker;
    }

    public String getFunctionCoworker() {
        return functionCoworker;
    }

    public void setFunctionCoworker(String functionCoworker) {
        this.functionCoworker = functionCoworker;
    }

    public String getMailAddressCoworker() {
        return mailAddressCoworker;
    }

    public void setMailAddressCoworker(String mailAddressCoworker) {
        this.mailAddressCoworker = mailAddressCoworker;
    }

}
