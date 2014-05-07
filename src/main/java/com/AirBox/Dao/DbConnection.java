package com.AirBox.Dao;

import com.AirBox.Domain.UploadObject;
import com.AirBox.Domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbConnection {
	
	private static String connectionString = "jdbc:mysql://airbox.cezm78dqy1fn.us-west-1.rds.amazonaws.com:3306/airbox";
	private static String dbUsername = "airbox";
	private static String dbPassword = "password";
	
public boolean loginCheck(String username, String password){
    String query;
    boolean login = false;

    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
        Statement stmt = (Statement) con.createStatement();
        query = "SELECT username, password FROM user_details WHERE username='" + username + "' AND password='" + password + "';";
        System.out.println("username"+username+"pwd"+password);
        stmt.executeQuery(query);
        ResultSet rs = stmt.getResultSet();
        login = rs.first(); //rs.first();
        
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


public String getBucketName (String username){
	
	String query;
	String bname = "nouser";
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
        Statement stmt = (Statement) con.createStatement();
        //String uname = user.getUserName();
        query = "SELECT bucketname FROM user_details WHERE username ='"+username+"';'";
        //query = "INSERT into user_details (first_name, last_name, username, password,bucketname) values ('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getUserName()+"','"+user.getPassword()+"','"+user.getBucketname()+"')";
        ResultSet rs = stmt.executeQuery(query);
        //System.out.println("Bucketname for User "+user.getUserName()+" is "+);
        if(rs.next()){
        	bname = rs.getString("bucketname");
        	//bname = (String) rs.getObject(1);
        	
        }else
        	System.out.println("No user found for username = "+username+"  ");
        System.out.println("Inside DbConnection - testing bucket name: " +bname);
       
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return bname;
	
}


public void insertUser(User user){
    String query;
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
        Statement stmt = (Statement) con.createStatement();
        query = "INSERT into user_details (first_name, last_name, username, password, bucketname) values ('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getUserName()+"','"+user.getPassword()+"','"+user.getBucketname()+"')";
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

public boolean insertFiledata(UploadObject file, String username){
    String query1;
    String query2;
    boolean fileinsert = false;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	long filesize= file.getSize()/1024;
	long allowedSize =0;
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
        Statement stmt = (Statement) con.createStatement();
        
        query1="SELECT max(no) FROM (SELECT 1048576-sum(filesize) no FROM airbox.file_details WHERE username='"+ username +"' UNION SELECT 1048576 no) t";
        
        query2 = "INSERT into file_details (username, filename, filesize, date_created) values ('"+username+"','"+file.getFileName()+"','"+filesize+"','"+dateFormat.format(date)+"')";
          
        
        ResultSet rs = stmt.executeQuery(query1);
        while(rs.next()){
        allowedSize = Long.parseLong(rs.getString(1));
        System.out.println("allowedSize" + allowedSize);
        }
        if (file.getSize()< allowedSize)
        {
        	stmt.executeUpdate(query2);
        	System.out.println("File Inserted");
        	fileinsert=true;
        }
        else
        {
        System.out.println("file can not be inserted");}
        
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return fileinsert;
    
}

public void shareFile(String username, String filename, String shareemail){
    String query;
    String uname = username;
    String fname = filename;
    String smail = shareemail;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
        Statement stmt = (Statement) con.createStatement();
        query = "INSERT into share_details (ownername, shareuser, filename, shareddate) values ('"+uname+"','"+smail+"','"+fname+"','"+dateFormat.format(date)+"')";
        System.out.println("file inserted in db");
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
        Connection con = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
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

