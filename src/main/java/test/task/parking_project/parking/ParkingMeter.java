package test.task.parking_project.parking;

import test.task.parking_project.database.ConnectorDB;
import test.task.parking_project.database.delete.Deleter;
import test.task.parking_project.database.report.Reporter;
import test.task.parking_project.database.save.Saver;
import test.task.parking_project.parking.car.Car;
import test.task.parking_project.parking.ticket.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ParkingMeter {
    private int freePlace;
    private Parking parking;
    private Saver saver;
    private Deleter deleter;
    private Connection con;
    private Reporter reporter;

    public ParkingMeter(Parking parking) {
        this.parking = parking;
        this.con = ConnectorDB.getConnection();
        this.saver = new Saver();
        this.deleter = new Deleter();
        saver.initParking(this.parking, this.con);
        this.reporter = new Reporter();
    }

    public Ticket gettingTicket(Parking parking, Car car) {
        definitionFreePlaces();
        if (freePlace == -1) {
            System.out.println("No vacant places");
            return null;
        }
        Ticket ticket = new Ticket(car, parking.getPlaces().get(freePlace));
        car.setTicket(ticket);
        parking.getPlaces().get(freePlace).setCar(car);
        savingDataInDB(ticket, car);
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

    public void ticketReturn(Ticket ticket) {
        if (ticket != null) {
            ticket.setOnParking(false);
            ticket.getParkingPlace().setCar(null);
            deleter.deletePlace(ticket, con);
            deleter.deleteTicket(ticket, con);
        } else {
            System.out.println("Not ticket!");
        }
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
        } catch (SQLException exp) {
            exp.printStackTrace();
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
}
