package org.application.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.application.entities.User;

import java.util.List;
import java.util.Optional;


public class UserRepository {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("task-management-unit");
    private final EntityManager em = emf.createEntityManager();

    public List<User> getAllUsers() {
        List<User> users = em.createQuery("select u from User u ", User.class).getResultList();
        return users;
    }

    public User createUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public void deleteUser(Long id) {
        User user = findUserById(id).orElseThrow(() -> new IllegalArgumentException("invalid user id : " + id));
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }


    public User updateUser(User user) {
        em.getTransaction().begin();
        User updatedUser = em.merge(user);
        em.getTransaction().commit();
        return updatedUser;
    }


}
