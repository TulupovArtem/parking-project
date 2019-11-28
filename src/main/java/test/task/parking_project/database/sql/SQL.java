package test.task.parking_project.database.sql;

public class SQL {
    public static final String UPDATE_PLACE = "UPDATE parking " +
            "SET ticket_number = ?, car_number = ?, free = ? WHERE number_place = ?";
    public static final String INIT_PARKING = "INSERT INTO parking(ticket_number, car_number, free)" +
            " VALUES (NULL, NULL, 'true')";
    public static final String GET_CAR = "SELECT car_number " +
            "FROM car_history WHERE UPPER(car_number) LIKE UPPER(?)";
    public static final String INSERT_CAR = "INSERT INTO car_history(car_number, car_model)" +
            " VALUES (?, ?)";
    public static final String INSERT_TICKET = "INSERT INTO tickets("+
            "ticket_number, car_model, car_number, number_place, time, on_parking)"+
            "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_INIT_PLACES = "SELECT * FROM parking";
    public static final String UPDATE_EXIT_PLACE = "UPDATE parking " +
            "SET ticket_number = NULL, car_number = NULL, free = 'true' WHERE number_place = ?";
    public static final String UPDATE_EXIT_TICKET = "UPDATE tickets " +
            "SET on_parking = false WHERE ticket_number = ?";
    public static final String SELECT_FREE_PLACE = "SELECT free FROM parking WHERE free LIKE 'true'";
    public static final String SELECT_CARS_ON_PARKING = "SELECT car_number FROM parking " +
            "WHERE free LIKE 'false'";
    public static final String SELECT_ALL_CARS = "SELECT car_number FROM car_history";
    public static final String ADD_SIZE_PARKING = "INSERT INTO parking_param(size) VALUES (?)";
    public static final String GET_PARKING_SIZE = "SELECT size FROM parking_param";
    public static final String SELECT_TICKETS = "SELECT * FROM tickets WHERE on_parking LIKE 'true'";
}
