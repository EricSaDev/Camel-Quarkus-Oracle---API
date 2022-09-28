package com.redhat.models;

import java.sql.Date;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(serialization = true)
public class PeopleInsert { 
    private String firstname;
    private String lastname;
    private String email;
    private Date start_date;
    private Date end_date;

    public PeopleInsert(String firstname, String lastname, String email, Date start_date, Date end_date) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public PeopleInsert() {
		super();		
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStart_date() {
        return this.start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
