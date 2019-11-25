package test.task.parking_project.database;

import test.task.parking_project.database.configuration.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
    private static Connection connector;

    private ConnectorDB() {
    }

    public static Connection getConnection() {
        try {
            if (connector == null) {
                connector = DriverManager.getConnection(
                        Configuration.getProperty(Configuration.DB_URL),
                        Configuration.getProperty(Configuration.DB_LOGIN),
                        Configuration.getProperty(Configuration.DB_PASSWORD));
            }
        } catch (SQLException ex) {
            System.out.println("No connection with DB");
            ex.printStackTrace();
        }
        return connector;
    }
}
