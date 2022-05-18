package com.company.dao.impl;

import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User findByName(String alma);
    User findByNameAndSurname(String alma, String alma2);
    User findByEmail(String alma);


}