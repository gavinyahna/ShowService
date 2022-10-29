package com.show.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.show.rest.model.Actor;

public class ActorDB {

    public static HashMap<Integer, Actor> actors = new HashMap<>();
    static{
        //Breaking Bad
        Actor brianC = new Actor(1, "Brian", "Cranston");
        Actor aaronP = new Actor(2, "Aaron", "Paul");
        Actor annaG = new Actor(3, "Anna", "Gunn");
        Actor bobO = new Actor(4, "Bob", "Odenkirk");
        Actor jonB = new Actor(5, "Jonathan", "Banks");

        //Saul
        Actor rheaS = new Actor(6, "Rhea", "Seehorn");

        //GOT
        Actor emiliaC = new Actor(101, "Emilia", "Clarke");
        Actor sophieT = new Actor(102, "Sophie", "Turner");
        Actor maisieW = new Actor(103, "Maisie", "Williams");
        Actor kitH = new Actor(104, "Kit", "Harrington");
        Actor jasonM = new Actor(105, "Jason", "Momoa");

        //Dune
        Actor timC = new Actor(201, "Timothee", "Chalamet");
        Actor zendaya = new Actor(202, "Zendaya", "");
        Actor rebeccaF = new Actor(203, "Rebecca", "Ferguson");
        Actor oscarI = new Actor(204, "Oscar", "Isaac");

        //Aquaman
        Actor amberH = new Actor(301, "Oscar", "Isaac");
        Actor willemDafoe = new Actor(302, "Willem", "Dafoe");

        actors.put(1, brianC);
        actors.put(2, aaronP);
        actors.put(3, annaG);
        actors.put(4, bobO);
        actors.put(5, jonB);
        actors.put(6, rheaS);
        actors.put(7, emiliaC);
        actors.put(8, sophieT);
        actors.put(9, maisieW);
        actors.put(10, kitH);
        actors.put(11, jasonM);
        actors.put(12, timC);
        actors.put(13, zendaya);
        actors.put(14, rebeccaF);
        actors.put(15, oscarI);
        actors.put(16, amberH);
        actors.put(17, willemDafoe);
    }

    public static List<Actor> getActors(){
        return new ArrayList<>(actors.values());
    }

    public static Actor getActor(Integer id){
        return actors.get(id);
    }

    public static void updateActor(Integer id, Actor actor){
        actors.put(id, actor);
    }

    public static void updateActor(Integer id){
        actors.remove(id);
    }
}