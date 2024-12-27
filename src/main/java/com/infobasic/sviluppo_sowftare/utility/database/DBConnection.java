package com.infobasic.sviluppo_sowftare.utility.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private final String URL = "jdbc:postgresql://localhost:5432/edu_hub";
    private final String userDB = "postgres";
    private final String pwdDB = "Dariociaodarwin125";

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
