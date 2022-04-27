package main;

import entity.User;
import entity.dao.impl.UserDaoImpl;
import entity.dao.inter.UserDaoInter;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.instanceUserDao();
        userDao.removeUser(10);

    }
}

