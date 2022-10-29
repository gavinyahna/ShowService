package com.show.rest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.HashSet;

public class Actor {

    @NotNull
    private Integer id;
    @NotBlank @Length(min=1, max=255)
    private String firstName;
    @NotBlank @Length(min=1, max=255)
    private String lastName;

    private HashSet<Show> shows;

    public Actor() {

    }

    public Actor(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shows = new HashSet<>();
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Actor [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }
}