package com.show.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.show.rest.model.Actor;
import com.show.rest.model.Show;

public class ShowDB {

    public static HashMap<Integer, Show> shows = new HashMap<>();
    static{
        //Breaking Bad
        Actor brianC = new Actor(1, "Brian", "Cranston");
        Actor aaronP = new Actor(2, "Aaron", "Paul");
        Actor annaG = new Actor(3, "Anna", "Gunn");
        Actor bobO = new Actor(4, "Bob", "Odenkirk");
        Actor jonB = new Actor(5, "Jonathan", "Banks");

        HashMap<Integer, Actor> breakingBadCast = new HashMap<>();
        breakingBadCast.put(brianC.getId(), brianC);
        breakingBadCast.put(aaronP.getId(), aaronP);
        breakingBadCast.put(annaG.getId(), annaG);
        breakingBadCast.put(bobO.getId(), bobO);
        breakingBadCast.put(jonB.getId(), jonB);

        //Saul
        Actor rheaS = new Actor(6, "Rhea", "Seehorn");

        HashMap<Integer, Actor> saulCast = new HashMap<>();
        saulCast.put(bobO.getId(), bobO);
        saulCast.put(rheaS.getId(), rheaS);
        saulCast.put(jonB.getId(), jonB);
        saulCast.put(brianC.getId(), brianC);
        saulCast.put(aaronP.getId(), aaronP);

        //GOT
        Actor emiliaC = new Actor(101, "Emilia", "Clarke");
        Actor sophieT = new Actor(102, "Sophie", "Turner");
        Actor maisieW = new Actor(103, "Maisie", "Williams");
        Actor kitH = new Actor(104, "Kit", "Harrington");
        Actor jasonM = new Actor(105, "Jason", "Momoa");

        HashMap<Integer, Actor> gotCast = new HashMap<>();
        gotCast.put(emiliaC.getId(), emiliaC);
        gotCast.put(sophieT.getId(), sophieT);
        gotCast.put(maisieW.getId(), maisieW);
        gotCast.put(kitH.getId(), kitH);
        gotCast.put(jasonM.getId(), jasonM);

        //Dune
        Actor timC = new Actor(201, "Timothee", "Chalamet");
        Actor zendaya = new Actor(202, "Zendaya", "");
        Actor rebeccaF = new Actor(203, "Rebecca", "Ferguson");
        Actor oscarI = new Actor(204, "Oscar", "Isaac");

        HashMap<Integer, Actor> duneCast = new HashMap<>();
        duneCast.put(timC.getId(), timC);
        duneCast.put(zendaya.getId(), zendaya);
        duneCast.put(rebeccaF.getId(), rebeccaF);
        duneCast.put(oscarI.getId(), oscarI);
        duneCast.put(jasonM.getId(), jasonM);

        //Aquaman
        Actor amberH = new Actor(301, "Oscar", "Isaac");
        Actor willemDafoe = new Actor(302, "Willem", "Dafoe");

        HashMap<Integer, Actor> aquamanCast = new HashMap<>();
        aquamanCast.put(jasonM.getId(), jasonM);
        aquamanCast.put(amberH.getId(), amberH);
        aquamanCast.put(willemDafoe.getId(), willemDafoe);

        shows.put(1, new Show(1, "Breaking Bad", 4.6f, breakingBadCast));
        shows.put(2, new Show(2, "Better Call Saul", 4.9f, saulCast));
        shows.put(3, new Show(3, "Game of Thrones", 4.1f, gotCast));
        shows.put(4, new Show(4, "Dune", 3.6f, duneCast));
        shows.put(5, new Show(5, "Aquaman", 1.8f, aquamanCast));
    }

    public static List<Show> getShows(){
        return new ArrayList<>(shows.values());
    }

    public static Show getShow(Integer id){
        return shows.get(id);
    }

    public static void updateShow(Integer id, Show show){
        shows.put(id, show);
    }

    public static void updateShow(Integer id){
        shows.remove(id);
    }
}