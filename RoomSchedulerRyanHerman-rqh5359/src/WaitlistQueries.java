/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class WaitlistQueries {
    
     private static Connection connection;
     private static PreparedStatement addFaculty;
     private static PreparedStatement availableDates;
     private static ResultSet resultSet;
     private static PreparedStatement waitlistRooms;
     private static ResultSet resultSet2;
     private static PreparedStatement waitlistByDate;
     private static ResultSet resultSet3;
     private static PreparedStatement deleteEntry;
     private static ResultSet resultSet4;
     private static PreparedStatement getWaitlist;
     
     public static void addWaitlistEntry(String faculty, String date, String seats, Timestamp timestamp1){
        connection = DBConnection.getConnection();
        try
        {
            addFaculty = connection.prepareStatement("insert into waitlist (faculty, date, seats, timestamp) values (?,?,?,?)");
            addFaculty.setString(1, faculty);
            addFaculty.setString(2, date);
            addFaculty.setString(3, seats);
            addFaculty.setTimestamp(4, timestamp1);
            addFaculty.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
     //Returns waitlist status information
      public static ArrayList<String> getWaitlist(){
        connection = DBConnection.getConnection();
        ArrayList<String> waitlist = new ArrayList<String>();
        try
        {
            waitlistRooms = connection.prepareStatement("select faculty, date, seats from JAVA.WAITLIST order by date ASC, timestamp ASC");
            
            resultSet2 = waitlistRooms.executeQuery();
            
            while(resultSet2.next())
            {
         
                waitlist.add(resultSet2.getString(1));
                waitlist.add(" ");
                
                waitlist.add(resultSet2.getString(2));
                waitlist.add(" ");
                
                waitlist.add("Seats: " + resultSet2.getString(3));
                waitlist.add("-----");

            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist;
     }
      
       public static ArrayList<String> getWaitlistbyDate(String date){
           
        connection = DBConnection.getConnection();
        ArrayList<String> waitlistDate = new ArrayList<String>();
        try
        {
            waitlistByDate = connection.prepareStatement("select * from waitlist where date = ? order by timestamp ASC");
            waitlistByDate.setString(1,date);
           
            resultSet3 = waitlistByDate.executeQuery();
            
            while(resultSet3.next())
            {    
                waitlistDate.add(resultSet3.getString(1));
                waitlistDate.add(resultSet3.getString(2));
                waitlistDate.add(resultSet3.getString(3));
                waitlistDate.add(resultSet3.getString(4));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistDate;
           
           
           
       }
       
       public static void deleteWaitlistEntry(String faculty, String date){
            connection = DBConnection.getConnection();
        
            try
        {
            deleteEntry= connection.prepareStatement("delete from waitlist where faculty = (?) AND date = (?)");
            deleteEntry.setString(1, faculty);
            deleteEntry.setString(2, date);
            deleteEntry.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
            
           
       }
       
         public static ArrayList<String> getWaitlistByFaculty(String name){
           
        connection = DBConnection.getConnection();
        ArrayList<String> facultyWaitlist = new ArrayList<String>();
        try
        {
            getWaitlist = connection.prepareStatement("select date from waitlist where faculty = ?");
            getWaitlist.setString(1,name);
           
            resultSet4 = getWaitlist.executeQuery();
            
            while(resultSet4.next())
            {    
                facultyWaitlist.add(resultSet4.getString(1));
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return facultyWaitlist;
           
           
           
       }
       
}
