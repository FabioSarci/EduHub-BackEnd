package com.infobasic.sviluppo_sowftare.utility.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //
    private static DBConnection instance;
    private Connection connection;

    Dotenv dotenv = Dotenv.load();
    private final String URL = dotenv.get("URL");
    private final String userDB = dotenv.get("USER_DB");
    private final String pwdDB = dotenv.get("PWD_DB");

    private DBConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, userDB, pwdDB);
            connection.setAutoCommit(true);

        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getIstance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
