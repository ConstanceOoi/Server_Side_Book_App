package com.example.serverside;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteBookServlet", value = "/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get isbn from parameter
        String isbn = request.getParameter("isbn");

        //get user's email from session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            //delete the book from database
            BookDAO.instance.delete(isbn, email);

            //read books from database and set as attribute to display
            request.setAttribute("books", BookDAO.instance.readBooks(email));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //go back to displayAll.jsp once done
        request.getRequestDispatcher("displayAll.jsp").forward(request, response);
    }
}
