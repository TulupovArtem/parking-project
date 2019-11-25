package test.task.parking_project;

import test.task.parking_project.parking.car.Car;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ticket.ParkingMeter;
import test.task.parking_project.parking.ticket.Ticket;
import test.task.parking_project.database.ConnectorDB;
import test.task.parking_project.database.save.Saver;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking(5);
        Saver saver = new Saver();
        try {
            Connection con = ConnectorDB.getConnection();
            saver.initParking(parking, con);
            for (int cars = 0; cars != 6; cars++) {
                Car car = new Car("BMW" + cars, "BC565A77RUS" + cars);
                ParkingMeter parkingMeter = new ParkingMeter(parking);
                Ticket ticket = parkingMeter.gettingTicket(parking, car);
                if (ticket != null) {
                    Car temp = car;
                    saver.saveTicket(ticket, con);
                    saver.saveParkingPlace(ticket, con);
                    saver.saveCar(temp, con);
                }
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: in main");
            ex.printStackTrace();
        }
    }
}
