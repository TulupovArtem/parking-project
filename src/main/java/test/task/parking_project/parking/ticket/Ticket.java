package test.task.parking_project.parking.ticket;

import test.task.parking_project.parking.car.Car;
import test.task.parking_project.parking.Place;

public class Ticket {
    private final int NUM_FOR_GENERATOR = 50;

    private String id;
    private Car car;
    private Place parkingPlace;
    private Boolean onParking;

    public Ticket(Car car, Place place) {
        this.id = generateTicketId(car.getNumber(), place.getNumber());
        this.car = car;
        this.parkingPlace = place;
        place.setCar(car);
        this.onParking = true;
    }

    private String generateTicketId(String numberCar, String numberPlace) {
        return (numberCar + numberPlace + (int)(Math.random() * NUM_FOR_GENERATOR));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Place getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(Place parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public Boolean getOnParking() {
        return onParking;
    }

    public void setOnParking(Boolean onParking) {
        this.onParking = onParking;
    }
}
