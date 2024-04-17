package org.application.rest;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.application.entities.Project;
import org.application.entities.User;
import org.application.repositories.ProjectRepository;

import java.util.List;

@Path("/projects")
public class ProjectResource {
    private final ProjectRepository projectRepository = new ProjectRepository() ;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects() ;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project findProjectById(@PathParam("id") Long id) {
        return projectRepository.findProjectById(id).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Project createProject(Project project) {
        try {
            return projectRepository.createProject(project);
        } catch (PersistenceException persistenceException) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteProjectById(@PathParam("id") Long id ){
        try {
            projectRepository.deleteProject(id);
        } catch (IllegalArgumentException exception) {
            throw new WebApplicationException(Response.Status.NOT_FOUND) ;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Project updateProject(Project project) {
        try {
            return projectRepository.updateProject(project);
        } catch (PersistenceException exception) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }


}
