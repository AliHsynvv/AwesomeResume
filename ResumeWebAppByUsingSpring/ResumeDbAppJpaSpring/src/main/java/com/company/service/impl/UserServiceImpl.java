package com.company.service.impl;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {

    @Autowired
    @Qualifier("userDao1")
    private UserRepositoryCustom userDao;


    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        return userDao.getAll(name, surname, nationalityId);

    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    public User findByEmailAndAdress(String email, String password) {
        return userDao.findByEmailAndAdress(email, password);
    }

    @Override
    public User getByteID(int userId) {
        return userDao.getByteID(userId);
    }

    @Override
    @Transactional
    public boolean updateUser(User u) {
        return userDao.updateUser(u);
    }

    @Override
    @Transactional
    public boolean removeUser(int id) {
        return userDao.removeUser(id);
    }

    @Transactional
    public boolean addUser(User u) {
        return userDao.addUser(u);
    }


}
