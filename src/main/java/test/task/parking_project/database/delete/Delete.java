package test.task.parking_project.database.delete;

import test.task.parking_project.parking.ticket.Ticket;

import java.sql.Connection;

public interface Delete {
    void deletePlace(Ticket ticket, Connection con);
    void deleteTicket(Ticket ticket, Connection con);
}
