package StepProjectBooking.StepProject;

import StepProjectBooking.StepProject.console.Console;

import java.io.IOException;
import java.text.ParseException;

public class MainApp {
    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {
        Console console = new Console();

        while (true){
            console.menu();
        }
    }
}
