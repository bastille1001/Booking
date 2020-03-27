import StepProjectBooking.StepProject.converter.DateConverter;
import StepProjectBooking.StepProject.domain.Booking;
import StepProjectBooking.StepProject.controllers.FlightController;
import StepProjectBooking.StepProject.domain.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class FlightControllerTest {
    private FlightController fc;
    private Flight f1;
    private Flight f2;
    private Flight f3;
    private Booking b1;

    @BeforeEach
    void setUp() throws ParseException {
        this.fc = new FlightController();
        this.f1 = new Flight(1337, 20, "12:00 02/11/2019",
                "15:00 02/11/2019", "Baku", "Kiev");
        this.f2 = new Flight(2000, 20, "15:00 02/11/2019",
                "19:00 02/11/2019", "Baku", "London");
        this.f3 = new Flight(2120, 20, "16:00 02/11/2019",
                "19:00 02/11/2019", "Baku", "Paris");
        this.b1 = new Booking(1020, "Markus", "Berg");
    }

    @Test
    void addFlightTest() throws IOException, ClassNotFoundException {
        assertEquals(0,fc.getAllFlight().size());
        fc.addFlight(f1);
        assertEquals(1,fc.getAllFlight().size());
    }

    @Test
    void getAllFlightsTest() throws IOException, ClassNotFoundException {
        assertEquals(0, fc.getAllFlight().size());
        fc.addFlight(f1);
        assertEquals(1, fc.getAllFlight().size());
        fc.addFlight(f2);
        assertEquals(2, fc.getAllFlight().size());
    }

    @Test
    void getAvailableFlightDateTest1() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(f1);
        fc.addFlight(f2);
        fc.addFlight(f3);
        Date date1 = new Date();
        date1.setTime(DateConverter.stringToMills("11:00 02/11/2019"));
        assertEquals(1, fc.getAvailableFlight("Kiev", 1, date1).size());
    }

    @Test
    void getAvailableFlightDateTest2() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(f1);
        fc.addFlight(f2);
        fc.addFlight(f3);
        Date date = new Date();
        date.setTime(DateConverter.stringToMills("13:00 02/11/2019"));
        assertEquals(1, fc.getAvailableFlight("Kiev", 1, date).size());
    }

    @Test
    void getAvailableFlightCityTest() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(f1);
        fc.addFlight(f2);
        fc.addFlight(f3);
        Date date = new Date();
        date.setTime(DateConverter.stringToMills("13:00 02/11/2019"));
        assertEquals(0, fc.getAvailableFlight("Madrid", 1, date).size());
    }

    @Test
    void getAvailableFlightSeatsTest1() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(f1);
        fc.addFlight(f2);
        fc.addFlight(f3);
        Date date = new Date();
        date.setTime(DateConverter.stringToMills("13:00 02/11/2019"));
        assertEquals(0, fc.getAvailableFlight("Kiev", 21, date).size());
    }

    @Test
    void getAvailableFlightSeatsTest2() throws IOException, ClassNotFoundException, ParseException {
        fc.addFlight(f1);
        fc.addFlight(f2);
        fc.addFlight(f3);
        Date date = new Date();
        date.setTime(DateConverter.stringToMills("13:00 02/11/2019"));
        assertEquals(1, fc.getAvailableFlight("Kiev", 19, date).size());
    }

    @Test
    void addClientTest() throws IOException, ClassNotFoundException {
        fc.addFlight(f1);
        assertEquals(20, fc.getFlightById(1337).getNumberOfFreeSeats());
        fc.addClient(b1, 1337);
        assertEquals(19, fc.getFlightById(1337).getNumberOfFreeSeats());
    }

    @Test
    void removeClientTest() throws IOException, ClassNotFoundException {
        fc.addFlight(f1);
        fc.addClient(b1, 1337);
        assertEquals(19, fc.getFlightById(1337).getNumberOfFreeSeats());
        fc.removeClient(b1, 1337);
        assertEquals(20, fc.getFlightById(1337).getNumberOfFreeSeats());
    }

    @Test
    void getFlightByIdTest() throws IOException, ClassNotFoundException {
        fc.addFlight(f1);
        fc.addFlight(f2);
        fc.addFlight(f3);
        assertEquals("Kiev", fc.getFlightById(1337).getDestinationCity());
    }
























}
