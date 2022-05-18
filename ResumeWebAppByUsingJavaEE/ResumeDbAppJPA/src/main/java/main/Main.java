package main;

import com.company.entity.User;
import com.company.dao.inter.UserDaoInter;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.findByEmail("gsynvali@gmail.com");
        System.out.println(u.getId());
    }
}

