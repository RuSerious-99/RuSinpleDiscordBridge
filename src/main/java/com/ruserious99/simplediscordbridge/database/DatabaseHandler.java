package com.ruserious99.simplediscordbridge.database;

import java.sql.*;
import java.util.Objects;

public class DatabaseHandler extends Config{

     Connection connection;

     public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME,
                PASSWORD);

        return connection;
    }

    public boolean isConnected(){return connection != null;}

    public void disconnect(){
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   public void deleteTicket(String channelId){
       String query = "DELETE FROM " + Const.USERS_TABLE + " WHERE "
               + Const.CHANNEL_ID + "=?";

       try {
           PreparedStatement preparedStatement = getConnection().prepareStatement(query);
           preparedStatement.setString(1, channelId);
           preparedStatement.execute();
           preparedStatement.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }


   }
    public void addTicket(String name, String channelId){
        String insert = "INSERT INTO " + Const.USERS_TABLE + "("
                + Const.USERS_NAME + ","
                + Const.CHANNEL_ID + ")"
                + "VALUES(?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, channelId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasTicket(String name) {
         if(!name.equals("")) {
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                    + Const.USERS_NAME + "=?";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    if(resultSet.getString(Const.USERS_NAME).equals(name)){
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean hasTicketById(String id) {
        if(!id.equals("")) {
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                    + Const.CHANNEL_ID + "=?";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    if(resultSet.getString(Const.CHANNEL_ID).equals(id)){
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
