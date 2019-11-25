package test.task.parking.Parking;

import test.task.parking.Parking.Car.Car;

public class Place {
    private Boolean free;
    private Car car;
    private String number;

    public Place(String number) {
        this.free = false;
        this.number = number;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Boolean isFree() {
        if (car == null) {
            this.free = true;
        } else {
            this.free = false;
        }
        return free;
    }
}
