package com.learn.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("staff")
public class Staff extends User {
    @Column(name = "hired_on")
    private LocalDate hiredOn;

    public Staff() {}

    public Staff(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public LocalDate getHiredOn() {
        return hiredOn;
    }

    public void setHiredOn(LocalDate hiredOn) {
        this.hiredOn = hiredOn;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "firstName='" + getFirstName() + '\'' +
                ",lastName='" + getLastName() + '\'' +
                ", hiredOn=" + hiredOn +
                '}';
    }
}
