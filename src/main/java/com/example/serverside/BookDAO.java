package com.example.serverside;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public enum BookDAO {

    instance();
    ArrayList<Book> books;

    //create connection to connect with the database
    public Connection getConnection() throws Exception{
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con;
        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "sa", "");
        return con;
    }

    //get one book from database based on the isbn and email
    public Book readBook(String isbn, String email) throws Exception{
        Connection c = getConnection();
        PreparedStatement psmt = c.prepareStatement("SELECT * FROM PUBLIC.BOOK WHERE isbn = ? AND email = ?");
        psmt.setString(1, isbn);
        psmt.setString(2, email);
        ResultSet rs = psmt.executeQuery();

        Book book = new Book();
        while (rs.next()){
            String title = rs.getString("title");
            String synopsis = rs.getString("synopsis");
            String author = rs.getString("author");
            String category = rs.getString("category");
            int publicationYear = rs.getInt("publicationYear");
            book = new Book(isbn, title, synopsis, author, category, publicationYear, email);

        }
        //String email = rs.getString("email");


        return book;
    }

    //get the whole list of books based on the email
    public ArrayList<Book> readBooks(String email) throws Exception{
        Connection c = getConnection();
        PreparedStatement psmt = c.prepareStatement("SELECT * FROM PUBLIC.BOOK WHERE email = ?");
        psmt.setString(1, email);
        ResultSet rs = psmt.executeQuery();

        books = new ArrayList();
        while(rs.next()){
            String isbn = rs.getString("isbn");
            String title = rs.getString("title");
            String synopsis = rs.getString("synopsis");
            String author = rs.getString("author");
            String category = rs.getString("category");
            int publicationYear = rs.getInt("publicationYear");
            //String email = rs.getString("email");

            Book b = new Book(isbn, title, synopsis, author, category, publicationYear, email);
            books.add(b);
        }
        return books;
    }

    //add new book to database
    public Book add(Book b) throws Exception{
        Connection c = getConnection();
        PreparedStatement psmt = c.prepareStatement("INSERT INTO PUBLIC.BOOK (isbn, title, synopsis, author, category, publicationYear, email) VALUES (?,?,?,?,?,?,?)");
        psmt.setString(1, b.getIsbn());
        psmt.setString(2, b.getTitle());
        psmt.setString(3, b.getSynopsis());
        psmt.setString(4, b.getAuthor());
        psmt.setString(5, b.getCategory());
        psmt.setInt(6, b.getPublicationYear());
        psmt.setString(7, b.getEmail());

        psmt.executeUpdate();
        c.commit();
        return b;
    }

    //update and save book into database
    public void update(Book b) throws Exception{
        Connection c = getConnection();
        PreparedStatement psmt = c.prepareStatement("UPDATE PUBLIC.BOOK SET title = ?, synopsis = ?, author = ?, category = ? , publicationYear = ? WHERE isbn = ? AND email = ? ");
        psmt.setString(1, b.getTitle());
        psmt.setString(2, b.getSynopsis());
        psmt.setString(3, b.getAuthor());
        psmt.setString(4, b.getCategory());
        psmt.setInt(5, b.getPublicationYear());
        psmt.setString(6, b.getIsbn());
        psmt.setString(7, b.getEmail());

        psmt.executeUpdate();
        c.commit();

    }

    //delete book from database
    public void delete(String isbn, String email) throws Exception{
        Connection c = getConnection();
        PreparedStatement psmt = c.prepareStatement("DELETE FROM PUBLIC.BOOK WHERE isbn = ? AND email = ?");
        psmt.setString(1, isbn);
        psmt.setString(2, email);
        psmt.executeUpdate();

        c.commit();
    }
}
