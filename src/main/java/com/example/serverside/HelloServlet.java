package com.example.serverside;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "HelloServlet", value = "/HelloServlet")
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //


        //get email and password from parameter
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //error checking
        //if email and password is empty
        if(email.length() == 0 || password.length() == 0){
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }


        try {
            //get user from database
            User u = UserDAO.instance.login(email, password);
            //if user is registered and able to login
            if(u != null){
                Cookie[] cookies = request.getCookies();
                Boolean userExists = false;
                String user = null;
                for(int i = 0; i < cookies.length; i++){
                    if(cookies[i].getName().equals("name")){
                        userExists = true;
                        user = cookies[i].getValue();
                        System.out.println(user);
                    }
                }

                if(userExists){
                    System.out.println("Cookie Name Exists");
                    System.out.println(user);
                    request.setAttribute("name", user);
                }


                //get the books from database that is stored under the user
                request.setAttribute("books", BookDAO.instance.readBooks(u.getEmail()));

                //set session for user's name and email
                HttpSession session = request.getSession();
                session.setAttribute("name", u.getName());
                session.setAttribute("email", u.getEmail());

                //after login, go to displayAll.jsp
                request.getRequestDispatcher("displayAll.jsp").forward(request, response);

            }else{
                //if user not in database, the user will be brought to the register page
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void destroy() {
    }
}