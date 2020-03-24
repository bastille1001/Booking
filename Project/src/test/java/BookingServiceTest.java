import StepProjectBooking.StepProject.booking.Booking;
import StepProjectBooking.StepProject.dao.Services.BookingServices;
import StepProjectBooking.StepProject.flights.Flight;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {
    private BookingServices bs = new BookingServices();

    @Test
    void equals() {
        Booking b1 = new Booking(12, "Dexter", "Johnson");
        Booking b2 = new Booking(13, "Sasha", "Petrov");

        assertNotEquals(b1, b2);

        Booking b3 = new Booking(31, "Dexter", "Johnson");
        assertNotEquals(b1, b3);
    }

    @Test
    void get() throws IOException, ClassNotFoundException {
        Booking b1 = new Booking(44, "Petr", "Mitrich");
        assertNull(bs.service.get(2));

        bs.service.save(b1);
        assertEquals(b1, bs.service.get(44));
    }

    @Test
    void cancelBooking() throws ParseException {
        Booking client = new Booking(2, "Javid", "Mammadli");
        Map<Integer, Booking> map = new HashMap<>();
        map.put(10, client);

        Flight flight = new Flight(2, 100, "11:21 01/11/2019", "11:21 21/11/2019", "London", "New-York");

        client.addFlight(flight);
        assertTrue(bs.cancelBooking(client, 2));

        Flight flight2 = new Flight(3, 111, "11:21 01/11/2019", "11:21 21/11/2019", "London", "New-York");
        Flight flight3 = new Flight(4, 10, "11:21 01/11/2019", "11:21 21/11/2019", "London", "New-York");
        Flight flight4 = new Flight(5, 41, "11:21 01/11/2019", "11:21 21/11/2019", "London", "New-York");
        client.addFlight(flight);
        client.addFlight(flight2);
        client.addFlight(flight3);
        client.addFlight(flight4);

        assertTrue(bs.cancelBooking(client, 2));
        assertTrue(bs.cancelBooking(client, 3));
        assertFalse(bs.cancelBooking(client, 2121));
        assertTrue(bs.cancelBooking(client, 4));
        assertTrue(bs.cancelBooking(client, 5));
    }
}
