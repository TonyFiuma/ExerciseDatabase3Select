package it.develhope.sql;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Start{
    public static void main(String[] args){

        Connection connection = null;

        Set<String> surnames = new HashSet<>();//ho messo un set invece che una lista per evitare duplicati
        try{
            // db parameters
            String url      = "jdbc:mysql://localhost:3306/newdb";
            String user     = "root";
            String password = "password";

            // create a connection to the database
            connection = DriverManager.getConnection(url,user,password);

            // more processing here
            System.out.println(String.format("Connected to database %s successfully.",connection.getCatalog()));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT first_name,last_name FROM students");

            while(resultSet.next()){
                System.out.println("The student's name is: "+resultSet.getString("first_name"));
                surnames.add(resultSet.getString("last_name"));
            }
            for (String single:surnames){
                System.out.println("The student's surnames added on surname(list) is: "+ single);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            ;
        } finally {
            try{
                if(connection != null){
                    connection.close();}
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }

        }

    }

}