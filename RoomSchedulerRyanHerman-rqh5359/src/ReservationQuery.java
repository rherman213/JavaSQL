
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan
 */
public class ReservationQuery {
    
     private static Connection connection;
     private static PreparedStatement addFaculty;
     private static PreparedStatement availableDates;
     private static ResultSet resultSet;
     private static PreparedStatement availableRooms;
     private static ResultSet resultSet2;
      private static PreparedStatement aRooms;
      private static ResultSet resultSet3;
      private static PreparedStatement bRooms;
      private static ResultSet resultSet4;
      private static PreparedStatement deleteEntry;
     private static PreparedStatement infoEntry;
     private static ResultSet infoSet;
     private static PreparedStatement checkRooms;
     private static ResultSet checkSet;
     private static PreparedStatement getRooms;
     private static ResultSet reserved;
      
    
    public static void addReservationEntry(String faculty, String room, String date, String seats, Timestamp timestamp1){
        connection = DBConnection.getConnection();
        try
        {   
            //Creates a new reservation
            addFaculty = connection.prepareStatement("insert into reservations (faculty, room, dates ,seats, timestamp) values (?,?,?,?,?)");
            addFaculty.setString(1, faculty);
            addFaculty.setString(2, room);
            addFaculty.setString(3, date);
            addFaculty.setString(4, seats);
            addFaculty.setTimestamp(5, timestamp1);
            addFaculty.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllPossibleDates()  {
        connection = DBConnection.getConnection();
        ArrayList<String> availDatesList = new ArrayList<String>();
        try
        {
            availableDates = connection.prepareStatement("select dates from reservations");
            resultSet = availableDates.executeQuery();
            
            while(resultSet.next())
            {
                availDatesList.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return availDatesList;
        
    }
    
     public static ArrayList<String> getAllPossibleRooms()  {
        connection = DBConnection.getConnection();
        ArrayList<String> availRoomsList = new ArrayList<String>();
        try
        {
            availableRooms = connection.prepareStatement("select room from reservations");
            resultSet2 = availableRooms.executeQuery();
            
            while(resultSet2.next())
            {
                availRoomsList.add(resultSet2.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return availRoomsList;
        
    }
     //Returns the rooms numbers used on a specific date
     public static ArrayList<String> getRoomsUsedbyDate(String date1){
         connection = DBConnection.getConnection();
        ArrayList<String> getRoomList = new ArrayList<String>();
        try
        {
            aRooms = connection.prepareStatement("select room from reservations where dates = ?");
            aRooms.setString(1,date1);
            resultSet3 = aRooms.executeQuery();
            
            while(resultSet3.next())
            {
                getRoomList.add(resultSet3.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return getRoomList;
        
    }
     //Returns the reservation status information
     public static ArrayList<String> getReservationsDateOrder(String date){
        connection = DBConnection.getConnection();
        ArrayList<String> reservedNames = new ArrayList<String>();
        try
        {
            bRooms = connection.prepareStatement("select faculty, room from reservations where dates = ?");
            bRooms.setString(1,date);
            resultSet4 = bRooms.executeQuery();
            
            while(resultSet4.next())
            {
                reservedNames.add(resultSet4.getString(1));
                reservedNames.add(" has Room: ");
                reservedNames.add(resultSet4.getString(2));
                reservedNames.add(" --- ");
                
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservedNames;
     }
     
     public static ArrayList<String> deleteReservationEntry(String faculty, String date){
          connection = DBConnection.getConnection();
           ArrayList<String> info = new ArrayList<String>();
          
        
            try
        {
            infoEntry= connection.prepareStatement("select * from reservations where faculty = (?) AND dates = (?)");
            infoEntry.setString(1, faculty);
            infoEntry.setString(2, date);
            infoSet = infoEntry.executeQuery();
             while(infoSet.next())
            {
                info.add(infoSet.getString(1));
                info.add(infoSet.getString(2));
                info.add(infoSet.getString(3));
                info.add(infoSet.getString(4));
                info.add(infoSet.getString(5));

            }
            
            deleteEntry= connection.prepareStatement("delete from reservations where faculty = (?) AND dates = (?)");
            deleteEntry.setString(1, faculty);
            deleteEntry.setString(2, date);
            deleteEntry.executeUpdate();
            
         
            
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
            return info;
           
       }
     
      public static ArrayList<String> getReservationsByRoom(String roomName){
         connection = DBConnection.getConnection();
        ArrayList<String> getRoomList = new ArrayList<String>();
        try
        {
            checkRooms = connection.prepareStatement("select * from reservations where room = ? order by timestamp ASC");
            checkRooms.setString(1,roomName);
            checkSet = checkRooms.executeQuery();
            
            while(checkSet.next())
            {
                getRoomList.add(checkSet.getString(1));
                getRoomList.add(checkSet.getString(2));
                getRoomList.add(checkSet.getString(3));
                getRoomList.add(checkSet.getString(4));
                getRoomList.add(checkSet.getString(5));
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return getRoomList;
        
    }
         
      
       public static ArrayList<String> getReservationsByFaculty(String name)  {
           
           connection = DBConnection.getConnection();
            ArrayList<String> reservationByFaculty = new ArrayList<String>();
        try
        {
            getRooms = connection.prepareStatement("select room, dates from RESERVATIONS where faculty = ?");
            getRooms.setString(1,name);
            reserved = getRooms.executeQuery();
            
            while(reserved.next())
            {
                reservationByFaculty.add(reserved.getString(1));
                reservationByFaculty.add(reserved.getString(2));
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservationByFaculty;
        
    }
       
     }
         
   

    
    


