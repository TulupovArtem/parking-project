package test.task.parking_project.parking.ticket;

import test.task.parking_project.parking.car.Car;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.Place;

import java.util.List;

public class ParkingMeter {
    private int freePlace;
    private Parking parking;

    public ParkingMeter(Parking parking) {
        this.parking = parking;
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
}
