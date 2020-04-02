package StepProjectBooking.StepProject.services;

import StepProjectBooking.StepProject.controllers.FlightController;
import StepProjectBooking.StepProject.domain.Booking;
import StepProjectBooking.StepProject.dao.BookingDao;
import StepProjectBooking.StepProject.dao.DAO;
import StepProjectBooking.StepProject.domain.Flight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BookingServices {

    public DAO<Booking> service = new BookingDao();

    private File file = new File("./clientsData.txt");
    private FlightController fc = new FlightController();

    public boolean cancelBooking(Booking client, int flightId){
        for (Flight flight: client.getMyFlights()) {
            if (flight.getId() == flightId) {
                client.cancelFlight(flight);
                System.out.println("Your booking canceled successfully");
                return true;
            }
        }
        return false;
    }

    public void addToBase(Booking client) throws IOException, ClassNotFoundException {
        for (Booking client1: service.getAll()) {
            if (client1.equals(client)) service.update(client);
            else service.save(client);
        }
        FileWriter writer = new FileWriter(file);
        writer.write("\n");
        writer.write(client.toString());
        writer.close();
    }

    public void myFlights(String name, String surname) throws IOException, ClassNotFoundException {
        for (Booking client : service.getAll()) {
            if (client.getName().equals(name) && client.getSurname().equals(surname)) {
                System.out.printf("Your flights: %s", client.getMyFlights().toString());
            }
        }
    }

    public void addFlight(Booking client, Flight flight) throws IOException, ClassNotFoundException {
        for (Booking oldClient : service.getAll()) {
            if (oldClient.equals(client)) {
                oldClient.addFlight(flight);
                service.update(client);
            } else {
                client.addFlight(flight);
                service.save(client);
            }
        }
    }
}
