package test.task.parking_project.database.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";

    private static Properties properties = new Properties();

    public synchronized static String getProperty(String name) {
        if (properties.isEmpty()) {
            try (InputStream is = Configuration.class.getClassLoader()
                    .getResourceAsStream("database.properties")) {
                properties.load(is);
            } catch (IOException ex) {
                System.out.println("Invalid properties");
                ex.printStackTrace();
                throw new RuntimeException();
            }
        }
        return properties.getProperty(name);
    }
}
