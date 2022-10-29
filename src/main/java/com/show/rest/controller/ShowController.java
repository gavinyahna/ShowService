package com.show.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.show.rest.dao.ShowDB;
import com.show.rest.model.Actor;
import com.show.rest.model.Show;

@Path("/shows")
@Produces(MediaType.APPLICATION_JSON)
public class ShowController {

    private final Validator validator;

    public ShowController(Validator validator) {
        this.validator = validator;
    }

    @GET
    public Response getShows() {
        return Response.ok(ShowDB.getShows()).build();
    }

    @GET
    @Path("/actor/{actorId}")
    public Response getShowsByActorId(@PathParam("actorId") Integer actorId) {
        List<Show> matches = new ArrayList<>();
        ShowDB.getShows().stream().forEach(show-> {
            if (show.getCast().containsKey(actorId)) {
                matches.add(show);
            }
        });
        return Response.ok(matches).build();
    }

    @GET
    @Path("/{id}")
    public Response getShowById(@PathParam("id") Integer id) {
        Show show = ShowDB.getShow(id);
        if (show != null)
            return Response.ok(show).build();
        else
            return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    public Response createShow(Show show) throws URISyntaxException {
        // validation
        Set<ConstraintViolation<Show>> violations = validator.validate(show);
        // validate actors exist before adding them to show
        Set<ConstraintViolation<Actor>> actorViolations = new HashSet<>();
        show.getCast().forEach((Integer id, Actor actor)-> actorViolations.addAll(validator.validate(actor)));

        Show e = ShowDB.getShow(show.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Show> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            ShowDB.updateShow(show.getId(), show);
            return Response.created(new URI("/shows/" + show.getId()))
                    .build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateShowById(@PathParam("id") Integer id, Show show) {
        // validation
        Set<ConstraintViolation<Show>> violations = validator.validate(show);
        Show e = ShowDB.getShow(show.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Show> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            show.setId(id);
            ShowDB.updateShow(id, show);
            return Response.ok(show).build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeShowById(@PathParam("id") Integer id) {
        Show show = ShowDB.getShow(id);
        if (show != null) {
            ShowDB.updateShow(id);
            return Response.ok().build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
}
