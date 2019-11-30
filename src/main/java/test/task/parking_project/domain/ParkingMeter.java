package test.task.parking_project.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.task.parking_project.database.ConnectorDB;
import test.task.parking_project.database.delete.Deleter;
import test.task.parking_project.database.report.Reporter;
import test.task.parking_project.database.save.Saver;
import test.task.parking_project.exception.CarOnParkingException;
import test.task.parking_project.domain.car.Car;
import test.task.parking_project.domain.ticket.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingMeter {
    private int freePlace;
    private Parking parking;
    private Saver saver;
    private Deleter deleter;
    private Connection con;
    private Reporter reporter;
    private List<Ticket> tickets;

    private static ParkingMeter parkingMeter;
    private static ParkingMeter parkingMeterForReporting;

    private static final Logger logger = LoggerFactory.getLogger(ParkingMeter.class);

    private ParkingMeter() {
        this.con = ConnectorDB.getConnection();
        this.saver = new Saver();
        this.deleter = new Deleter();
        this.reporter = new Reporter();
    }

    public ParkingMeter(Parking parking) {
        this.parking = parking;
        this.con = ConnectorDB.getConnection();
        this.saver = new Saver();
        this.deleter = new Deleter();
        saver.initParking(this.parking, this.con);
        this.reporter = new Reporter();
        this.tickets = ticketsRecovery();
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
    }

    private List<Ticket> ticketsRecovery() {
        return reporter.ticketsRecovery(con, parking);
    }

    public static void initParkingMeter() {
        if (parkingMeterForReporting == null) {
            parkingMeterForReporting = new ParkingMeter();
        }
    }

    public static void initParkingMeter(Parking parking) {
        if (parkingMeter == null) {
            parkingMeter = new ParkingMeter(parking);
        }
    }

    public Ticket gettingTicket(Parking parking, Car car) {
        definitionFreePlaces();
        if (freePlace == -1) {
            return null;
        }
        if (reporter.carOnParking(con, car)) {
            logger.warn("Car on parking!");
            throw new CarOnParkingException();
        }
        Ticket ticket = new Ticket(car, parking.getPlaces().get(freePlace));
        parking.getPlaces().get(freePlace).setCar(car);
        savingDataInDB(ticket, car);
        tickets.add(ticket);
        return ticket;
    }

    private void definitionFreePlaces() {
        List<Place> places = parking.getPlaces();
        for (Place place : places) {
            if (place.isFree()) {
                this.freePlace = Integer.parseInt(place.getNumber().substring(1)) - 1;
                return;
            }
        }
        this.freePlace = -1;
    }

    private void savingDataInDB(Ticket ticket, Car car) {
        saver.saveTicket(ticket, con);
        saver.saveParkingPlace(ticket, con);
        saver.saveCar(car, con);
    }

    public Ticket searchTicket(String id) {
        if (this.tickets == null) {
            return null;
        }
        List<Ticket> tickets = this.tickets;
        for (Ticket ticket : tickets) {
            if (ticket.getId().equals(id)) {
                return ticket;
            }
        }
        return null;
    }

    public Ticket ticketReturn(String id) {
        Ticket ticket = searchTicket(id);
        if (ticket != null) {
            ticket.setOnParking(false);
            ticket.getParkingPlace().setCar(null);
            deleter.deletePlace(ticket, con);
            deleter.deleteTicket(ticket, con);
            this.tickets.remove(ticket);
        } else {
            logger.warn("No ticket!");
        }
        return ticket;
    }

    public List<String> carsOnParkingReport() {
        return reporter.carsOnParkingReport(con);
    }

    public List<String> allCarsReport() {
        return reporter.allCarsReport(con);
    }

    public int freeParkingPlaceReport() {
        return reporter.freeParkingPlaceReport(con);
    }

    public void completionWork() {
        try {
            con.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public int getFreePlace() {
        return freePlace;
    }

    public void setFreePlace(int freePlace) {
        this.freePlace = freePlace;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Saver getSaver() {
        return saver;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    public Deleter getDeleter() {
        return deleter;
    }

    public void setDeleter(Deleter deleter) {
        this.deleter = deleter;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public static ParkingMeter getParkingMeter() {
        return parkingMeter;
    }

    public static void setParkingMeter(ParkingMeter parkingMeter) {
        ParkingMeter.parkingMeter = parkingMeter;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static ParkingMeter getParkingMeterForReporting() {
        return parkingMeterForReporting;
    }

    public static void setParkingMeterForReporting(ParkingMeter parkingMeterForReporting) {
        ParkingMeter.parkingMeterForReporting = parkingMeterForReporting;
    }
}
