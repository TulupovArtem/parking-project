package test.task.parking_project;

import test.task.parking_project.parking.car.Car;
import test.task.parking_project.parking.Parking;
import test.task.parking_project.parking.ParkingMeter;
import test.task.parking_project.parking.ticket.Ticket;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking(5);
        ParkingMeter parkingMeter = new ParkingMeter(parking);
        Car bmw = new Car("BMW", "BC565A77RUS");
        Car mers = new Car("mersedes", "AA967B88RUS");
        Car audi = new Car("audi", "CC857L60RUS");
        Car kia = new Car("kia", "TT666TT111RUS");
        Ticket ticketBMW = parkingMeter.gettingTicket(parking, bmw);
        Ticket ticketMers = parkingMeter.gettingTicket(parking, mers);
        Ticket ticketAudi = parkingMeter.gettingTicket(parking, audi);
        Ticket ticketKia = parkingMeter.gettingTicket(parking, kia);
        parkingMeter.ticketReturn(ticketKia);
        System.out.println("Count free places: " + parkingMeter.freeParkingPlaceReport() + '\n');
        List<String> report1 = parkingMeter.allCarsReport();
        List<String> report2 = parkingMeter.carsOnParkingReport();
        System.out.println("All came to us: ");
        if (report1 != null) {
            for (String line : report1) {
                System.out.println(line);
            }
        } else {
            System.out.println("No cars");
        }
        System.out.println();
        System.out.println("On parking: ");
        if (report2 != null) {
            for (String line : report2) {
                System.out.println(line);
            }
        } else {
            System.out.println("No cars");
        }
        parkingMeter.completionWork();
    }
}
