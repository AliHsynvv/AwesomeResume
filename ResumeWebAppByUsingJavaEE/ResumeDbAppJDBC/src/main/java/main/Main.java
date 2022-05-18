package main;

import entity.User;
import entity.dao.impl.UserDaoImpl;
import entity.dao.inter.SkillDaoInter;
import entity.dao.inter.UserDaoInter;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDaoInter=Context.instanceUserDao();
        User u=userDaoInter.getByteID(5);
        System.out.println(u.getName());

    }


}
