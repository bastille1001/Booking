package StepProjectBooking.StepProject.dao;

import StepProjectBooking.StepProject.booking.Client;
import StepProjectBooking.StepProject.flights.DateConverter;
import StepProjectBooking.StepProject.flights.Flight;
import StepProjectBooking.StepProject.flights.FlightRandomGenerator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

public class FlightDaoServices {
    DAO<Flight> flightDAO = new FlightDaoStorage();

    public ArrayList<Flight> getAllFlight() throws IOException, ClassNotFoundException {
        return flightDAO.getAll();
    }

    public ArrayList<Flight> getAvailableFlight(String cities, int freeSeats, Date date) throws IOException, ClassNotFoundException {
        ArrayList<Flight> availableFlight;
        availableFlight = (ArrayList<Flight>) flightDAO.getAll().stream()
                .filter(flight -> flight.getDestinationCity().equals(cities))
                .filter(flight -> flight.getNumberOfFreeSeats() >= freeSeats)
//                .filter(flight -> date.getTime() - flight.getStartingDate() >= DateConverter.hour(12))
                .collect(Collectors.toList());/*
                .filter(flight -> date.getTime() - flight.getStartingDate() < DateConverter.hour(12))
                .collect(Collectors.toList());
                */
        return availableFlight;
    }

    public Flight getFlightById(int flightId) throws IOException, ClassNotFoundException {
        try {
            return flightDAO.getAll().stream()
                    .filter(flight1 -> flight1.getId() == flightId)
                    .collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public void addClient(Client client, int flightId) throws IOException, ClassNotFoundException {
        Flight flight = getFlightById(flightId);
        flight.getSeats().put(client.getUserId(),client);
        flightDAO.update(flight);
        client.addFlight(flight);
    }

    public void removeClient(Client client,int flightId) throws IOException, ClassNotFoundException {
        Flight flight = getFlightById(flightId);
        flight.getSeats().remove(client.getUserId(), client);
        client.cancelFlight(flight);
    }

    public HashMap<Integer, Client> getPassengers(int flightId) throws IOException, ClassNotFoundException {
        return flightDAO.get(flightId).getSeats();
    }

    public void addFlight(Flight flight) throws IOException, ClassNotFoundException {
        flightDAO.save(flight);
    }

    public void createRandomFlight() throws ParseException, IOException, ClassNotFoundException {
        FlightRandomGenerator flightRandom = new FlightRandomGenerator();
        flightDAO.save(flightRandom.buildRandom());
    }
}
