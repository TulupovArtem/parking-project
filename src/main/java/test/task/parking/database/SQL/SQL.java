package test.task.parking.database.SQL;

public class SQL {
    public static final String UPDATE_PLACE = "UPDATE parking " +
            "SET ticket_number = ?, free = ? WHERE number_place = ?";
    public static final String INIT_PARKING = "INSERT INTO parking(ticket_number, free)" +
            " VALUES (NULL, 'true')";
    public static final String GET_CAR = "SELECT car_number " +
            "FROM car_history WHERE UPPER(car_number) LIKE UPPER(?)";
    public static final String INSERT_CAR = "INSERT INTO car_history(car_number)" +
            " VALUES (?)";
    public static final String INSERT_TICKET = "INSERT INTO tickets("+
            "ticket_number, car_model, car_number, number_place, time)"+
            "VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_INIT_PLACES = "SELECT * FROM parking";
}
