package com.example.serverside;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditBookServlet", value = "/EditBookServlet")
public class EditBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get book's details from parameter
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String synopsis = request.getParameter("synopsis");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        int publicationYear = 0;

        if(isbn.length() == 0 || title.length() == 0 || synopsis.length() == 0 || author.length() == 0 || author.length() == 0 || category.length() == 0){
            System.out.println("Details not filled in");
            //go back to editBook.jsp to fill in again
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
        }

        //error checking for publication year
        try{

            publicationYear = Integer.parseInt(request.getParameter("publicationYear"));

        }catch (Exception e){
            //go back to editBook.jsp to fill in again
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
        }

        //get user email from session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        //create a book obj based on the newly passed in details
        Book book = new Book(isbn, title, synopsis, author, category, publicationYear, email);

        try {
            //update the book in database
            BookDAO.instance.update(book);

            //read books from database and set attribute to display in displayAll.jsp
            request.setAttribute("books", BookDAO.instance.readBooks(email));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //go back to displayAll.jsp once done
        request.getRequestDispatcher("displayAll.jsp").forward(request, response);
    }
}
