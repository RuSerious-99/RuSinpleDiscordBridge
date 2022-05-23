package com.ruserious99.simplediscordbridge.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "tickets";
    private final String USERNAME = "root";     // change to config
    private final String PASSWORD = "";         // change to config


    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME,
                PASSWORD);
    }

    public Connection getConnection() {return connection;}

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
}
