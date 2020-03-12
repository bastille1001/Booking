package StepProjectBooking.StepProject.dao;
import StepProjectBooking.StepProject.booking.Client;
import StepProjectBooking.StepProject.flights.Flight;

import java.io.IOException;

public class BookingDaoController {
    public BookingDaoServices bookingDaoServices = new BookingDaoServices();

    public boolean cancelBooking(Client client, int flightId){
        return bookingDaoServices.cancelBooking(client,flightId);
    }

    public void addToDataBase(Client client) throws IOException, ClassNotFoundException {
        bookingDaoServices.addToBase(client);
    }

    public void myFlights(String name, String surname) throws IOException, ClassNotFoundException {
        bookingDaoServices.myFlights(name,surname);
    }

    public void addFlight(Client client, Flight flight) throws IOException, ClassNotFoundException{
        bookingDaoServices.addFlight(client,flight);
    }
}
