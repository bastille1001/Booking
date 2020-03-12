package StepProjectBooking.StepProject.dao;

import StepProjectBooking.StepProject.booking.Client;

import java.io.FileWriter;
import java.util.ArrayList;

public class BookingDaoStorage implements DAO<Client> {

    private ArrayList<Client> allClients = new ArrayList<>();
    private FileWriter writer;
    @Override
    public Client get(int Id) {
        for (Client client: allClients) {
            if (client.getUserId() == Id)
                return client;
        }
        return null;
    }

    @Override
    public ArrayList<Client> getAll() {
        return allClients;
    }

    @Override
    public void save(Client data) {
        allClients.add(data);
    }

    @Override
    public void update(Client client) {
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
    public void deleteByObject(Client client) {
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
