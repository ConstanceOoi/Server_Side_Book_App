package com.example.serverside;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditFormServlet", value = "/EditFormServlet")
public class EditFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get isbn from parameter
        String isbn = request.getParameter("isbn");

        //get email attribute from session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            //get the book that user wants to edit based on isbn and email
            Book book = BookDAO.instance.readBook(isbn, email);
            //set book as attribute to display on the edit form
            request.setAttribute("books", book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //user is bring to editBook page
        request.getRequestDispatcher("editBook.jsp").forward(request, response);

    }
}
