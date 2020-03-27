package StepProjectBooking.StepProject;

import StepProjectBooking.StepProject.console.Console;
import StepProjectBooking.StepProject.console.SecConsole;

import java.io.IOException;
import java.text.ParseException;

public class MainApp {
    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {
//        Console app = new Console();
//
//        while (true){
//            app.mainMenu();
//        }

        SecConsole secConsole = new SecConsole();

        while (true){
            secConsole.menu();
        }
    }
}
