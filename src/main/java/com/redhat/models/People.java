package com.redhat.models;

import java.sql.Timestamp;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(serialization = true)
public class People {
    private int people_id;   
    private String firstname;
    private String lastname;
    private String email;
    private Timestamp start_date;
    private Timestamp end_date;

    public People(int people_id, String firstname, String lastname, String email, Timestamp start_date, Timestamp end_date) {
        this.people_id = people_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public People() {
		super();		
    }
   
    public int getPeople_id() {
        return this.people_id;
    }

    public void setPeople_id(int people_id) {
        this.people_id = people_id;
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

    public Timestamp getStart_date() {
        return this.start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }
}
