package test.task.parking_project.parking.car;


import test.task.parking_project.parking.ticket.Ticket;

public class Car {
    private String model;
    private String number;
//    private Ticket ticket;

    public Car(String model, String number) {
        this.model = model;
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    public Ticket getTicket() {
//        return ticket;
//    }
//
//    public void setTicket(Ticket ticket) {
//        this.ticket = ticket;
//    }
}
