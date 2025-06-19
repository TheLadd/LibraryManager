package com.learn.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("member")
//@Table(name = "member") // Implicitly present, I think
public class Member extends User {

    @Column(name = "joined_on")
    LocalDate joinedOn;

    public Member() {}

    public Member(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public LocalDate getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(LocalDate joinedOn) {
        this.joinedOn = joinedOn;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userId=" + getUserId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", joinedOn='" + this.joinedOn +
                '}';
    }
}
