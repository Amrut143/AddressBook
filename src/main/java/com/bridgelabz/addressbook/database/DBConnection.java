package com.bridgelabz.addressbook.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        String filePath = "D:/FellowshipProgram/AddressBook/src/main/resources/DBDetails.properties";
        Connection connection = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String DB_DRIVER = properties.getProperty("DB_DRIVER_CLASS");
            String DB_URL = properties.getProperty("DB_URL");
            String DB_USER = properties.getProperty("DB_USERNAME");
            String DB_PASSWORD = properties.getProperty("DB_PASSWORD");

            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connection established");
        return connection;
    }
}
