package com.example.serverside;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //get user's email from session
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");

            //set books as attribute for request and session
            request.setAttribute("books", BookDAO.instance.readBooks(email));
            session.setAttribute("books", BookDAO.instance.readBooks(email));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //show all the books that are saved under the user's email
        request.getRequestDispatcher("displayAll.jsp").forward(request, response);

    }


}
