package StepProjectBooking.StepProject.dao.Interface;

import java.io.IOException;
import java.util.ArrayList;

public interface DAO<D> {
    D get(int Id) throws IOException, ClassNotFoundException;

    ArrayList<D> getAll() throws IOException, ClassNotFoundException;

    void save(D d) throws IOException, ClassNotFoundException;

    void update(D d) throws IOException, ClassNotFoundException;

    void deleteById(int id) throws IOException, ClassNotFoundException;

    void deleteByObject(D d) throws IOException, ClassNotFoundException;
}
