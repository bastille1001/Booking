package StepProjectBooking.StepProject.dao;

import StepProjectBooking.StepProject.flights.Flight;

import java.util.ArrayList;

public class FlightDaoStorage implements DAO<Flight> {

    private ArrayList<Flight> flightList = new ArrayList<>();

    @Override
    public Flight get(int Id) {
        try {
            return flightList.get(Id);
        }catch (IndexOutOfBoundsException ex){
            throw ex;
        }
    }

    @Override
    public ArrayList<Flight> getAll() { return flightList; }

    @Override
    public void save(Flight flight) { flightList.add(flight); }

    @Override
    public void update(Flight flight) { flightList.set(flightList.indexOf(flight),flight); }

    @Override
    public void deleteById(int id) { flightList.remove(id); }

    @Override
    public void deleteByObject(Flight flight) { flightList.remove(flight); }
}
