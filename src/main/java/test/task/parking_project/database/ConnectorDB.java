package test.task.parking_project.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.task.parking_project.database.configuration.Configuration;
import test.task.parking_project.database.save.Saver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
    private static final Logger logger = LoggerFactory.getLogger(ConnectorDB.class);

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
            logger.error(ex.getMessage(), ex);
        }
        return connector;
    }
}
