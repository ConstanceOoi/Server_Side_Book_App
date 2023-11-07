package com.example.serverside;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //get user's details through parameter
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        //error checking
        //if the details is empty, the user will be redirect back to the register page
        if(name.length() == 0 || email.length() == 0 || address.length() == 0 || password.length() == 0){
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }else{
            //create new user
            User u = new User(name, email, address, password);

            //create cookie
            Cookie cookie = new Cookie("name", name);
            response.addCookie(cookie);
            request.setAttribute("name", name);
            try{
                //save it in database and set the attribute of User obj
                UserDAO.instance.register(u);

                //set session for user's name and email
                HttpSession session = request.getSession();
                session.setAttribute("name", u.getName());
                session.setAttribute("email", u.getEmail());

                //after login, go to displayAll page
                request.getRequestDispatcher("displayAll.jsp").forward(request, response);
            }catch (Exception e){
                throw new RuntimeException(e);
            }


        }

    }

    public void destroy() {
    }
}
