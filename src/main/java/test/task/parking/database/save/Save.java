package test.task.parking.database.save;

import test.task.parking.Parking.Car.Car;
import test.task.parking.Parking.Ticket.Ticket;

import java.sql.Connection;

public interface Save {
    void saveTicket(Ticket ticket, Connection connection);
    void saveCar(Car car, Connection connection);
    void saveParkingPlace(Ticket ticket, Connection connection);
}
