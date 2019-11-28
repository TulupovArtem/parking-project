package test.task.parking_project.parking;

import test.task.parking_project.database.ConnectorDB;
import test.task.parking_project.database.report.Reporter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Parking {
    private int numberPlaces;
    private List<Place> places;

    private static Parking parking;

    public Parking(int numberPlaces) {
        this.numberPlaces = numberPlaces;
        this.places = new ArrayList<>(numberPlaces);
        for (int num = 1; num != numberPlaces + 1; num++) {
            this.places.add(new Place("A" + num));
        }
    }

    public static void initParking() {
        if (parking == null) {
            Connection con = ConnectorDB.getConnection();
            Reporter rep = new Reporter();
            int size = rep.parkingSize(con);
            if (size == 0) {
                parking = null;
            } else {
                parking = new Parking(size);
            }
        }
    }

    public static void initParking(int size) {
        if (parking == null) {
            parking = new Parking(size);
        }
    }

    public int getNumberPlaces() {
        return numberPlaces;
    }

    public void setNumberPlaces(int numberPlaces) {
        this.numberPlaces = numberPlaces;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public static Parking getParking() {
        return parking;
    }

    public static void setParking(Parking parking) {
        Parking.parking = parking;
    }
}
