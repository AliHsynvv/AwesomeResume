package com.example.resumewebapp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerUtil {

    public static void errorPage(HttpServletResponse resp, Exception ex){
        try {
            ex.printStackTrace();
            resp.sendRedirect("error?msg="+ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
