package com.AirBox.Dao;

import com.AirBox.Domain.UploadObject;
import com.AirBox.Domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
public boolean loginCheck(String username, String password){
    String query;
    boolean login = false;

    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airbox", "root", "password");
        Statement stmt = (Statement) con.createStatement();
        query = "SELECT username, password FROM user_details WHERE username='" + username + "' AND password='" + password + "';";
        stmt.executeQuery(query);
        ResultSet rs = stmt.getResultSet();
        login = rs.first(); 
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return login;
}

public void insertUser(User user){
    String query;
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airbox", "root", "password");
        Statement stmt = (Statement) con.createStatement();
        query = "INSERT into user_details (first_name, last_name, username, password) values ('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getUserName()+"','"+user.getPassword()+"')";
        stmt.executeUpdate(query);
        System.out.println("User Inserted successfully");
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void insertFiledata(UploadObject file){
    String query;
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airbox", "root", "password");
        Statement stmt = (Statement) con.createStatement();
        query = "INSERT into file_details (username, filename, size, date_created) values ('"+file.getUsername()+"','"+file.getFileName()+"','"+file.getSize()+"','"+file.getDateCreated()+"')";
        stmt.executeUpdate(query);
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteFile(UploadObject file){
	String query;
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airbox", "root", "password");
        Statement stmt = (Statement) con.createStatement();
        query = "delete from file_details WHERE username='" + file.getUsername() + "' AND filename='" + file.getFileName() + "'";
        stmt.executeUpdate(query);
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}

