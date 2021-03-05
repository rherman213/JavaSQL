
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class RoomEntry {
    
    private static Connection connection;
    private static PreparedStatement addRoom;
     private static PreparedStatement numSeats;
     private static ResultSet seatSet;
    private static PreparedStatement roomName;
     private static ResultSet roomSet;
     private static PreparedStatement deleteEntry;
    
    public static void addRoom(String name, String seats)
    {
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into rooms (roomname,seats) values (?,?)");
            addRoom.setString(1, name);
            addRoom.setString(2, seats);
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    
    
     public static ArrayList<String> getSeatsInRoom(String roomName){
          connection = DBConnection.getConnection();
          ArrayList<String> numOfSeats = new ArrayList<String>();
        try
        {
            numSeats = connection.prepareStatement("select seats from rooms where roomname = ?");
            numSeats.setString(1, roomName);
            seatSet = numSeats.executeQuery();
             while(seatSet.next())
            {
                numOfSeats.add(seatSet.getString(1));

            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return numOfSeats;
         
     }
     
     public static ArrayList<String> getRoomsList()
     {
        connection = DBConnection.getConnection();
        ArrayList<String> roomNames = new ArrayList<String>();
        try
        {
            roomName = connection.prepareStatement("select roomname from rooms");
            roomSet = roomName.executeQuery();
            
            while(roomSet.next())
            {
                roomNames.add(roomSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return roomNames;
        
    }
     
     public static void dropRoom(String roomName){
          connection = DBConnection.getConnection();
        
            try
        {
            deleteEntry = connection.prepareStatement("delete from rooms where roomname = ?");
            deleteEntry.setString(1, roomName);
            deleteEntry.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
            
           
       }
        
    
    
}
