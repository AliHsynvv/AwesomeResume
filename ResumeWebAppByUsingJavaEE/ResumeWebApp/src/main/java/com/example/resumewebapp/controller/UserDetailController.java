package com.example.resumewebapp.controller;

import entity.User;
import entity.dao.inter.UserDaoInter;
import main.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id= Integer.valueOf(request.getParameter("id"));
        String action=request.getParameter("action");
        if(action.equals("update")) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");

            System.out.println("name=" + name);
            System.out.println("surname=" + surname);

            User user = userDao.getByteID(id);
            user.setName(name);
            user.setSurname(surname);

            userDao.updateUser(user);
        }else if(action.equals("delete")){
            userDao.removeUser(id);
        }

        response.sendRedirect("users");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified");
            }

            Integer userId = Integer.parseInt(userIdStr);

            UserDaoInter userDao = Context.instanceUserDao();
            User u = userDao.getByteID(userId);
            if (u == null) {
                throw new IllegalArgumentException(" There is no user with this id");
            }

            request.setAttribute("owner", true);
            request.setAttribute("user", u);
            request.getRequestDispatcher("userdetail.jsp").forward(request, response);
        }catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg="+ex.getMessage());
        }
    }



}