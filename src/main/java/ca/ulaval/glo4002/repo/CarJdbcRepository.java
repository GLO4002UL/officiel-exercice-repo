package ca.ulaval.glo4002.repo;

import java.util.ArrayList;

public class CarJdbcRepository implements CarRepository {
    public CarJdbcRepository() {
        System.out.println("Using JDBC repository");
    }

    @Override
    public Car getById(int id) {
       System.err.println("TODO : The JDBC connector is not implemented yet.");
       return new Car(0, "0", new ArrayList<>(), new ArrayList<>());
    }
}
