
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
public class Dates {
    
    private static Connection connection;
    private static ArrayList<String> dates = new ArrayList<String>();
    private static PreparedStatement getDateList;
    private static PreparedStatement addDate;
    private static ResultSet resultSet;
    
    
   public static ArrayList<String> getDatesList()
           {
        connection = DBConnection.getConnection();
        ArrayList<String> dates = new ArrayList<String>();
        try
        {
            getDateList = connection.prepareStatement("select * from dates");
            resultSet = getDateList.executeQuery();
            
            while(resultSet.next())
            {
                dates.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return dates;
        
    }
   
    public static void addDate(String date)
    {
        connection = DBConnection.getConnection();
        try
        {
            addDate = connection.prepareStatement("insert into dates (date) values (?)");
            addDate.setString(1, date);
            addDate.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}