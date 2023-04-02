package ru.tolstikhin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLController {

    private static Connection connection = null;

    private static final Logger logger = LoggerFactory.getLogger(SQLController.class);

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    ClassLoader classLoader = SQLController.class.getClassLoader();
                    InputStream inputStream = classLoader.getResourceAsStream("application.properties");
                    Properties properties = new Properties();
                    properties.load(inputStream);
                    String driverClass = properties.getProperty("db.driver.class");
                    String connUrl = properties.getProperty("db.conn.url");
                    String username = properties.getProperty("db.username");
                    String password = properties.getProperty("db.password");
                    Class.forName(driverClass);
                    connection = DriverManager.getConnection(connUrl, username, password);
                } catch (ClassNotFoundException | SQLException | IOException e) {
                    logger.error(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return connection;
    }
}