package com.learn.library.model;


import com.learn.library.domain.ErrorMessages.UserErrorMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // or SINGLE_TABLE / TABLE_PER_CLASS
@DiscriminatorColumn(name = "user_type")
@Table(name = "user")
public abstract class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "first_name")
    @NotNull(message = UserErrorMessage.FIRST_NAME_NULL)
    @NotEmpty(message = UserErrorMessage.FIRST_NAME_EMPTY)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = UserErrorMessage.LAST_NAME_NULL)
    @NotEmpty(message = UserErrorMessage.LAST_NAME_EMPTY)
    private String lastName;

    public User() {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId() { return this.userId; };
    public String getFirstName() { return this.firstName; };
    public String getLastName() { return this.lastName; };
    public void setFirstName(String firstName) { this.firstName = firstName; };
    public void setLastName(String lastName) { this.lastName = lastName; };
}
