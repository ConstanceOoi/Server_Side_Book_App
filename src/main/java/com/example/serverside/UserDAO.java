package com.example.serverside;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public enum UserDAO {
    instance();

    //create connection to connect with the database
    public Connection getConnection() throws Exception{
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con;
        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "sa", "");
        return con;
    }


    //retrieve the user details when they login with their email and password
    public User login(String email, String password) throws Exception{
        Connection c = getConnection();
        PreparedStatement psmt = c.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
        psmt.setString(1, email);
        psmt.setString(2, password);
        ResultSet rs = psmt.executeQuery();
        User u = new User();
        while(rs.next()){
            String name = rs.getString("name");
            String address = rs.getString("address");
            u = new User(name, email, address, password);
        }
        return u;
    }

    //create new user and save it in database
    public User register(User u) throws Exception{
        Connection c = getConnection();
        PreparedStatement psmt = c.prepareStatement("INSERT INTO PUBLIC.USER (name, email, address, password) VALUES (?,?,?,?);");

        psmt.setString(1, u.getName());
        psmt.setString(2, u.getEmail());
        psmt.setString(3, u.getAddress());
        psmt.setString(4, u.getPassword());

        psmt.executeUpdate();
        c.commit();
        return u;
    }

}
