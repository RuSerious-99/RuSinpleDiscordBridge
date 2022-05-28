package com.ruserious99.simplediscordbridge.database;

import java.sql.*;

public class DatabaseHandler extends Config {

    Connection connection;

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME,
                PASSWORD);

        return connection;
    }


    public boolean isConnected() {
        return connection != null;
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTicket(String channelId) {
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

    public void addBadWord(String word){
        String insert = "INSERT INTO " + Const.BAD_WORDS + "("
                + Const.WORDS + ")"
                + "VALUES(?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            preparedStatement.setString(1, word);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTicket(String name, String channelId) {
        String insert = "INSERT INTO " + Const.USERS_TABLE + "("
                + Const.USERS_NAME + ","
                + Const.CHANNEL_ID + ")"
                + "VALUES(?,?)";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, channelId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasTicket(String name) {
        if (!name.equals("")) {
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                    + Const.USERS_NAME + "=?";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    if (resultSet.getString(Const.USERS_NAME).equals(name)) {
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
        if (!id.equals("")) {
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                    + Const.CHANNEL_ID + "=?";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    if (resultSet.getString(Const.CHANNEL_ID).equals(id)) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String findReactionRole(String guildId, String channelId, String messageId, String emote) {

        if (!guildId.equals("")) {
            String query = "SELECT * FROM " + Const.USERS_TABLE_ROLES ;
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    if(resultSet.getString("guild_id").equals(guildId)
                    && resultSet.getString("channel_id_roles").equals(channelId)
                    && resultSet.getString("message_id").equals(messageId)
                    && resultSet.getString("emote").equals(emote)){
                        return resultSet.getString("roles_id");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

            public void addReactionRole (String guildId, String channelId, String messageId, String emote, String roleId)
            {
                String insert = "INSERT INTO " + Const.USERS_TABLE_ROLES + "("
                        + Const.GUILD_ID + ","
                        + Const.CHANNEL_ID_ROLES + ","
                        + Const.MESSAGE_ID + ","
                        + Const.EMOTE + ","
                        + Const.ROLE_ID + ")"
                        + "VALUES(?,?,?,?,?)";

                try {
                    PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
                    preparedStatement.setString(1, guildId);
                    preparedStatement.setString(2, channelId);
                    preparedStatement.setString(3, messageId);
                    preparedStatement.setString(4, emote);
                    preparedStatement.setString(5, roleId);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
