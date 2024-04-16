package org.application.rest;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.application.entities.User;
import org.application.repositories.UserRepository;

import java.util.List;

@Path("/users")
public class UserResource {
    private final UserRepository userRepository = new UserRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        try {
            return userRepository.createUser(user);
        } catch (PersistenceException persistenceException) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userRepository.getAllUsers();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserById(@PathParam("id") Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @DELETE
    @Path("{id}")
    public void deleteUserById(@PathParam("id") Long id) {
        try {
            userRepository.deleteUser(id);
        } catch (IllegalArgumentException exception) {
            System.out.println("cannot delete the user with the id of : " + id);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        try {
            return userRepository.updateUser(user);
        } catch (PersistenceException exception) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
