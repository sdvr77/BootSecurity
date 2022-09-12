package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(int id) {
        entityManager.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public User getUserById(int id) {
        return entityManager
                .createQuery("from User where id = :id", User.class)
                .setParameter("id", id).getSingleResult();
    }
    @Override
    public User getUserByUsername(String username) {
        return entityManager
                .createQuery("from User u LEFT JOIN FETCH u.roles where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
