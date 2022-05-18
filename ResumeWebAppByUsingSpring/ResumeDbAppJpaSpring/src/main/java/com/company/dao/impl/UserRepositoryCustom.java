package com.company.dao.impl;

import com.company.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepositoryCustom {
    public List<User> getAll(String name, String surname, Integer nationalityId);

    public User findByEmailAndAdress(String email, String password);

    public User getByteID(int id);

    public boolean updateUser(User u);

    public boolean removeUser(int id);

    public boolean addUser(User u);

    public User findByEmail(String email);
}
