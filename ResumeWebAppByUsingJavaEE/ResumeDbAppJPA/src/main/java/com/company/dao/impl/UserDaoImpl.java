package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.Country;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String phone = rs.getString("phone");
        int birthplaceId = rs.getInt("birthplace_id");
        int nationalityId = rs.getInt("nationality_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");
        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);
        return new User(id, name, surname, phone, email, profileDesc, birthdate, nationality, birthplace);
    }


    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String phone = rs.getString("phone");
        int birthplaceId = rs.getInt("birthplace_id");
        int nationalityId = rs.getInt("nationality_id");
        Date birthdate = rs.getDate("birthdate");

//        User user = new User(id, name, surname, phone, email, profileDesc, birthdate, null, null);
//        user.setPassword(rs.getString("password"));

        return null;
    }


    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        EntityManager em = em();
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
        EntityManager em = em();
        Query q = em.createQuery("select u from User  u where u.email= :email  ", User.class);
        q.setParameter("email", email);
        List<User> list = q.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }



    public User findByEmailAndAdress(String email, String password) {

        EntityManager em = em();
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
        EntityManager em = em();
        User u = em.find(User.class, userId);
        em.close();
        return u;

    }

    @Override
    public boolean updateUser(User u) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();


        em.close();
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager em = em();
        em.getTransaction().begin(); //bir neçə əməliyyat etməyə yarayır
        User u = em.find(User.class, id);
        em.remove(u);
        em.getTransaction().commit(); //təsdiqləmək


        em.close();
        return true;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));

        EntityManager em = em();

        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }


}
