package org.application.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.WebApplicationException;
import org.application.entities.Project;

import java.awt.print.PrinterJob;
import java.util.List;
import java.util.Optional;

public class ProjectRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("task-management-unit");
    private final EntityManager em = emf.createEntityManager();

    public List<Project> getAllProjects() {
        List<Project> projects = em.createQuery("select p from Project p ",Project.class).getResultList() ;
        return projects ;
    }

    public Project createProject(Project project) {
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        return project ;
    }

    public Optional<Project> findProjectById(Long id) {
        return Optional.ofNullable(em.find(Project.class, id));
    }

    public void deleteProject(Long id) {
        Project project = findProjectById(id).
                orElseThrow(() -> new IllegalArgumentException("cannot find a user with the id of : " + id));
        em.getTransaction().begin();
        em.remove(project);
        em.getTransaction().commit();
    }

    public Project updateProject(Project project) {
        em.getTransaction().begin();
        Project updatedProject = em.merge(project) ;
        em.getTransaction().commit();
        return updatedProject ;
    }
}
