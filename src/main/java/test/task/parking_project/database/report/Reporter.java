package test.task.parking_project.database.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.task.parking_project.database.save.Saver;
import test.task.parking_project.database.sql.SQL;
import test.task.parking_project.domain.Parking;
import test.task.parking_project.domain.car.Car;
import test.task.parking_project.domain.ticket.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reporter implements Report {
    private static final Logger logger = LoggerFactory.getLogger(Reporter.class);
    @Override
    public int freeParkingPlaceReport(Connection con) {
        int freePlaces = 0;

        try (PreparedStatement stmt = con.prepareStatement(SQL.SELECT_FREE_PLACE)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                freePlaces++;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return freePlaces;
    }

    @Override
    public List<String> carsOnParkingReport(Connection con) {
        List<String> carsOnParking = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(SQL.SELECT_CARS_ON_PARKING)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                carsOnParking.add(rs.getString("car_number"));
            }
            if (carsOnParking.size() == 0) {
                return null;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return carsOnParking;
    }

    @Override
    public List<String> allCarsReport(Connection con) {
        List<String> allCars = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(SQL.SELECT_ALL_CARS)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allCars.add(rs.getString("car_number"));
            }
            if (allCars.size() == 0) {
                return null;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return allCars;
    }

    public int parkingSize(Connection con) {
        int size = 0;

        try (PreparedStatement stmt = con.prepareStatement(SQL.GET_PARKING_SIZE)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                size = rs.getInt("size");
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return size;
    }

    public List<Ticket> ticketsRecovery(Connection con, Parking parking) {
        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(SQL.SELECT_TICKETS)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                        rs.getString("car_model"),
                        rs.getString("car_number"));
                parking.getPlaces().get(rs.getInt("number_place") - 1).setCar(car);
                Ticket ticket = new Ticket(car, parking.getPlaces().get(rs.getInt("number_place") - 1));
                ticket.setId(rs.getString("ticket_number"));
                tickets.add(ticket);
            }
            if (tickets.size() == 0) {
                return null;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return tickets;
    }

    public boolean carOnParking(Connection con, Car car) {
        try (PreparedStatement stmt = con.prepareStatement(SQL.CAR_ON_PARKING)) {
            stmt.setString(1, car.getNumber());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }
}
