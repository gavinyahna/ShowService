package com.show.rest.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import java.util.HashMap;

public class Show {

    @NotNull
    private Integer id;
    @NotBlank @Length(min=1, max=255)
    private String name;
    @NotBlank @Length(min=1, max=3)
    private float rating;
    @NotEmpty
    private HashMap<Integer, Actor> cast;

    public Show() {

    }

    public Show(Integer id, String name, float rating, HashMap<Integer, Actor> cast) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.cast = cast;

        cast.forEach((Integer actorId, Actor actor) -> actor.addShow(this));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public HashMap<Integer, Actor> getCast() {
        return cast;
    }

    public void setCast(HashMap<Integer, Actor> cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "Show [id=" + id + ", firstName=" + name + ", rating="
                + rating + "]";
    }
}