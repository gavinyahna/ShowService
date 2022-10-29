package com.show.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.show.rest.dao.ActorDB;
import com.show.rest.model.Actor;

@Path("/actors")
@Produces(MediaType.APPLICATION_JSON)
public class ActorController {

    private final Validator validator;

    public ActorController(Validator validator) {
        this.validator = validator;
    }

    @GET
    public Response getActors() {
        return Response.ok(ActorDB.getActors()).build();
    }

    @GET
    @Path("/{id}")
    public Response getActorById(@PathParam("id") Integer id) {
        Actor actor = ActorDB.getActor(id);
        if (actor != null)
            return Response.ok(actor).build();
        else
            return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    public Response createActor(Actor actor) throws URISyntaxException {
        // validation
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        Actor e = ActorDB.getActor(actor.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Actor> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            ActorDB.updateActor(actor.getId(), actor);
            return Response.created(new URI("/actors/" + actor.getId()))
                    .build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateActorById(@PathParam("id") Integer id, Actor actor) {
        // validation
        Set<ConstraintViolation<Actor>> violations = validator.validate(actor);
        Actor e = ActorDB.getActor(actor.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Actor> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            actor.setId(id);
            ActorDB.updateActor(id, actor);
            return Response.ok(actor).build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeActorById(@PathParam("id") Integer id) {
        Actor actor = ActorDB.getActor(id);
        if (actor != null) {
            ActorDB.updateActor(id);
            return Response.ok().build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
}
