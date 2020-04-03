package StepProjectBooking.StepProject.console;

import StepProjectBooking.StepProject.controllers.BookingController;
import StepProjectBooking.StepProject.controllers.FlightController;
import StepProjectBooking.StepProject.domain.Booking;
import StepProjectBooking.StepProject.domain.Flight;
import StepProjectBooking.StepProject.domain.Logging;
import StepProjectBooking.StepProject.flights.DataFlight;


import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Console {
    private static Logging logger = new Logging("application.log");
    DataFlight df = new DataFlight();
    BookingController bookingController = new BookingController();
    FlightController flightController = new FlightController();
    ArrayList<Flight> flights;

    public Console() throws ParseException, IOException, ClassNotFoundException {
        Random random = new Random();
        if (df.loadFlight() == null) {
            for (int x = 0; x < random.nextInt(20) + 20; x++)
                flightController.createRandomFlight();
            df = new DataFlight(flightController);
        } else {
            flights = df.loadFlight();
            for (Flight flight : flights) {
                flightController.addFlight(flight);
            }
            df = new DataFlight(flightController);
        }
    }


    public void menu() throws IOException, ClassNotFoundException {
        System.out.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" +
                "* Please, select one of the following commands by number  * \n" +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" +
                "*  [1] Display all flights                                *\n" +
                "*  [2] Display info about flight                          *\n" +
                "*  [3] Search and book flight                             *\n" +
                "*  [4] Cancel booking                                     *\n" +
                "*  [5] My flights                                         *\n" +
                "*  [6] Exit                                               *\n" +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *" +
                "\n--->\n");


        Scanner scanner = new Scanner(System.in);
        System.out.println("Select number:");
        int command = scanner.nextInt();
        switch (command){
            case 1:
                System.out.print("All available flights: \n");
                flightController.getAllFlight().forEach(flight -> System.out.println(flight.toString()));
                logger.info("Display all flights");
                break;
            case 2:
                System.out.println("Please enter flight id : \n");
                try {
                    int query = scanner.nextInt();
                    if (flightController.getFlightById(query) == null) {
                        System.out.println("No flight with this Id. ");
                    } else {
                        System.out.println(flightController.getFlightById(query).toString());
                        logger.info("Display info about flight");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Wrong input. ");
                    logger.error(ex,"Wrong input");
                }
                break;
            case 3:
                String city;
                int people, userSelection;

                printer("Please enter destination city : ");
                city = scanner.next();
                printer("How many people will travel: ");
                try {
                    people = scanner.nextInt();
                    ArrayList<Flight> cFlight = flightController.getAvailableFlight(city, people, new Date());
                    System.out.println("\nMost similar results :");
                    printer(cFlight.toString() + "\n");
                    printer("Select any available flights above : ");
                    userSelection = scanner.nextInt();
                    if (cFlight.stream().noneMatch(item -> item.getId()
                            == userSelection) || userSelection < 0) {
                        System.out.println("Wrong flight id");
                    } else if (userSelection == 0)
                        return;
                    else {
                        for (int i = 0; i < people; i++) {
                            printer("Enter name of passenger : ");
                            String name = scanner.next();
                            printer("Enter surname of passenger : \n");
                            String surname = scanner.next();
                            printer("Enter user ID of passenger : \n");
                            int userId = scanner.nextInt();
                            Booking client = new Booking(userId, name, surname);
                            flightController.addClient(client,userSelection);
                            bookingController.addToDataBase(client);
                            logger.info("Search for flight and booking");
                        }
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Wrong input!");
                    logger.error(ex,"Wrong input");
                }
                break;
            case 4:
                System.out.println("Enter your name:");
                String name = scanner.next();
                System.out.println("Enter your surname");
                String surname = scanner.next();
                System.out.println("Enter user id:");
                int userId = scanner.nextInt();
                System.out.println("Enter flight id:");
                userSelection = scanner.nextInt();
                Booking client = new Booking(userId, name, surname);
                flightController.removeClient(client,userSelection);
                bookingController.cancelBooking(client,userSelection);
                logger.info("Cancel booking");
                break;
            case 5:
                System.out.println("Enter your name:");
                name = scanner.next();
                System.out.println("Enter your surname");
                surname = scanner.next();
                for (Flight f : flightController.getAllFlight()) {
                    for (Booking c : f.getSeats().values()) {
                        if (c.getName().equals(name) && c.getSurname().equals(surname))
                            c.getMyFlights().forEach(item -> printer(item.toString()));
                    }
                }
                logger.info("User`s flight(s)");
                break;
            case 6:
                df = new DataFlight(flightController);
                System.exit(0);
                logger.info("Exit from app");
        }
    }

    private void printer(String message) {
        System.out.print(message);
    }
}
