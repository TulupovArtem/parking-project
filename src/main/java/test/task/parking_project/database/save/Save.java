package test.task.parking_project.database.save;

import test.task.parking_project.domain.car.Car;
import test.task.parking_project.domain.ticket.Ticket;

import java.sql.Connection;

public interface Save {
    void saveTicket(Ticket ticket, Connection connection);
    void saveCar(Car car, Connection connection);
    void saveParkingPlace(Ticket ticket, Connection connection);
}
