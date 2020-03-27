package StepProjectBooking.StepProject.controllers;
import StepProjectBooking.StepProject.domain.Booking;
import StepProjectBooking.StepProject.services.BookingServices;
import StepProjectBooking.StepProject.domain.Flight;

import java.io.IOException;

public class BookingController {

    public BookingServices bookingDaoServices = new BookingServices();

    public void cancelBooking(Booking client, int flightId){
        bookingDaoServices.cancelBooking(client, flightId);
    }

    public void addToDataBase(Booking client) throws IOException, ClassNotFoundException {
        bookingDaoServices.addToBase(client);
    }

    public void myFlights(String name, String surname) throws IOException, ClassNotFoundException {
        bookingDaoServices.myFlights(name,surname);
    }

    public void addFlight(Booking client, Flight flight) throws IOException, ClassNotFoundException{
        bookingDaoServices.addFlight(client,flight);
    }
}
