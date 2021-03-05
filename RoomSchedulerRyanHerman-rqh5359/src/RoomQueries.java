import static java.lang.Integer.parseInt;
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
public class RoomQueries {
    
    private static Connection connection;
    private static ArrayList<String> dates = new ArrayList<String>();
    private static PreparedStatement getRoomList;
    private static ResultSet resultSet;
    
    //Gets possible rooms based off size, ordered in best fit form by number of seats
     public static ArrayList<String> getAllPossibleRooms(String seats){
          {
        connection = DBConnection.getConnection();
        ArrayList<String> roomList = new ArrayList<String>();
        try
        {
            getRoomList = connection.prepareStatement("select roomname from rooms where seats >= ? order by seats ASC");
            Integer numSeats = parseInt(seats);
            getRoomList.setInt(1,numSeats);
            resultSet = getRoomList.executeQuery();
            
            while(resultSet.next())
            {
                roomList.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return roomList;
        
    }
    
    
     }}
     
    
