package StepProjectBooking.StepProject.dao;
import StepProjectBooking.StepProject.booking.Client;
import StepProjectBooking.StepProject.flights.Flight;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FlightDaoController {


    public FlightDaoServices flightDaoServices = new FlightDaoServices();

    public ArrayList<Flight> getAllFlight() throws IOException, ClassNotFoundException {
        return flightDaoServices.getAllFlight();
    }

    public ArrayList<Flight> getAvailableFlight(String cities, int freeSeats, Date date) throws IOException, ClassNotFoundException {
        return flightDaoServices.getAvailableFlight(cities,freeSeats,date);
    }

    public void addClient(Client client, int flightId) throws IOException, ClassNotFoundException {
        flightDaoServices.addClient(client,flightId);
    }

    public void removeClient(Client client, int flightId) throws IOException, ClassNotFoundException {
        flightDaoServices.removeClient(client,flightId);
    }

    public HashMap<Integer, Client> getPassengers(int flightId) throws IOException, ClassNotFoundException {
        return flightDaoServices.getPassengers(flightId);
    }

    public void addFlight(Flight flight) throws IOException, ClassNotFoundException {
        flightDaoServices.addFlight(flight);
    }

    public Flight getFlightById(int flightId) throws IOException, ClassNotFoundException {
        return flightDaoServices.getFlightById(flightId);
    }

    public void createRandomFlight() throws ParseException, IOException, ClassNotFoundException {
        flightDaoServices.createRandomFlight();
    }
}
