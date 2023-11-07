package com.example.serverside;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateBookServlet", value = "/CreateBookServlet")
public class CreateBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get new book's details from parameters
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String synopsis = request.getParameter("synopsis");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        int publicationYear = 0;

        //error checking
        //check if the details are empty
        if(isbn.length() == 0 || title.length() == 0 || synopsis.length() == 0 || author.length() == 0 || author.length() == 0 || category.length() == 0){
            System.out.println("Details not filled in");

            //go back to createBook.jsp to fill in again
            request.getRequestDispatcher("createBook.jsp").forward(request, response);
        }

        //error checking for publication year
        try{

            publicationYear = Integer.parseInt(request.getParameter("publicationYear"));

        }catch (Exception e){
            //go back to createBook.jsp to fill in again
            request.getRequestDispatcher("createBook.jsp").forward(request, response);
        }

        //get user email from session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        //create new book obj
        Book book = new Book(isbn, title, synopsis, author, category, publicationYear, email);

        try{
            //add new book into database
            BookDAO.instance.add(book);
            //read books from database and set it as attribute to display
            request.setAttribute("books", BookDAO.instance.readBooks(email));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        //go back to displayAll.jsp once done
        request.getRequestDispatcher("displayAll.jsp").forward(request, response);

    }
}
