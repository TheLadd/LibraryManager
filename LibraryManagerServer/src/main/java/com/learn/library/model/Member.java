package com.learn.library.model;

import com.learn.library.domain.ErrorMessages.MemberErrorMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@DiscriminatorValue("member")
//@Table(name = "member") // Implicitly present, I think
public class Member extends User {

    @Column(name = "joined_on")
    @NotNull(message = MemberErrorMessage.JOINED_ON_NULL)
    @PastOrPresent(message = MemberErrorMessage.JOINED_ON_FUTURE_DATED)
    LocalDate joinedOn;

    public Member() {}

    public Member(String firstName, String lastName, LocalDate joinedOn) {
        super(firstName, lastName);
        this.joinedOn = joinedOn;
    }

    public Member(int memberId, String firstName, String lastName, LocalDate joinedOn) {
        super(memberId, firstName, lastName);
        this.joinedOn = joinedOn;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Member member)) return false;
        return Objects.equals(joinedOn, member.joinedOn) &&
                Objects.equals(getFirstName(), member.getFirstName()) &&
                Objects.equals(getLastName(), member.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(joinedOn);
    }
}
