package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("userDao1")
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager em;
    @Override
    @Cacheable(value ="users" )
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        String jpql = "select  u from User  u where 1=1 ";

        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name=:name";
        }
        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname=:surname ";
        }
        if (nationalityId != null) {
            jpql += " and u.nationality.id=:nid ";
        }

        Query query = em.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }
        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);

        }
        if (nationalityId != null) {
            query.setParameter("nid", nationalityId);

        }
        return query.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        Query q = em.createQuery("select u from User  u where u.email= :email  ", User.class);
        q.setParameter("email", email);
        List<User> list = q.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }


    public User findByEmailAndAdress(String email, String password) {

        Query q = em.createQuery("select u from User  u where u.email= :email and u.password=:password", User.class);
        q.setParameter("email", email);
        q.setParameter("password", password);

        List<User> list = q.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public User getByteID(int userId) {
        User u = em.find(User.class, userId);
        return u;

    }

    @Override
    public boolean updateUser(User u) {
        u.setPassword(crypt.encode( u.getPassword()));
        em.merge(u);
        return true;
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public boolean removeUser(int id) {
        User u = em.find(User.class, id);
        em.remove(u);
        return true;
    }

    private static BCryptPasswordEncoder crypt = new BCryptPasswordEncoder(); //crypt password

    public boolean addUser(User u) {
        u.setPassword(crypt.encode(u.getPassword()));
        em.persist(u);
        return true;
    }


}
