package StepProjectBooking.StepProject.dao.Controllers;
import StepProjectBooking.StepProject.booking.Booking;
import StepProjectBooking.StepProject.dao.Services.BookingServices;
import StepProjectBooking.StepProject.flights.Flight;

import java.io.IOException;

public class BookingController {
    public BookingServices bookingDaoServices = new BookingServices();

    public boolean cancelBooking(Booking client, int flightId){
        return bookingDaoServices.cancelBooking(client,flightId);
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
