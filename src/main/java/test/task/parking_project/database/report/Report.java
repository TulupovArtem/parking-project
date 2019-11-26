package test.task.parking_project.database.report;

import test.task.parking_project.parking.car.Car;

import java.sql.Connection;
import java.util.List;

public interface Report {
    int freeParkingPlaceReport(Connection con);
    List<String> carsOnParkingReport(Connection con);
    List<String> allCarsReport(Connection con);
}
