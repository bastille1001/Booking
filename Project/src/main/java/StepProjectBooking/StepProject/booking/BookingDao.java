package StepProjectBooking.StepProject.booking;

import StepProjectBooking.StepProject.dao.Interface.DAO;

import java.io.FileWriter;
import java.util.ArrayList;

public class BookingDao implements DAO<Booking> {

    private ArrayList<Booking> allClients = new ArrayList<>();
    private FileWriter writer;
    @Override
    public Booking get(int Id) {
        for (Booking client: allClients) {
            if (client.getUserId() == Id)
                return client;
        }
        return null;
    }

    @Override
    public ArrayList<Booking> getAll() {
        return allClients;
    }

    @Override
    public void save(Booking data) {
        allClients.add(data);
    }

    @Override
    public void update(Booking client) {
        allClients.set(allClients.indexOf(client),client);
    }

    @Override
    public void deleteById(int id) {
        try {
            allClients.forEach(client -> {
                if (client.getUserId() == id) allClients.remove(client);
            });
        }catch (IndexOutOfBoundsException ex){
            System.out.println("There is no client with this id");
        }
    }

    @Override
    public void deleteByObject(Booking client) {
        try {
            allClients.forEach(client1 -> {
                if (client.equals(client));
                allClients.remove(client);
            });
        }catch (IndexOutOfBoundsException ex){
            System.out.println("No client");
        }
    }
}
