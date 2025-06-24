package com.learn.library.model;

import com.learn.library.domain.ErrorMessages.StaffErrorMessages;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@DiscriminatorValue("staff")
public class Staff extends User {
    @Column(name = "hired_on")
    @NotNull(message = StaffErrorMessages.HIRED_ON_NULL)
    @PastOrPresent(message = StaffErrorMessages.HIRED_ON_FUTURE_DATED)
    private LocalDate hiredOn;

    public Staff() {}

    public Staff(String firstName, String lastName, LocalDate hiredOn) {
        super(firstName, lastName);
        this.hiredOn = hiredOn;
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
                "userId=" + getUserId() +
                " firstName='" + getFirstName() + '\'' +
                ",lastName='" + getLastName() + '\'' +
                ", hiredOn=" + hiredOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Staff staff)) return false;
        return Objects.equals(hiredOn, staff.hiredOn) &&
                Objects.equals(getFirstName(), staff.getFirstName()) &&
                Objects.equals(getLastName(), staff.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hiredOn);
    }
}
