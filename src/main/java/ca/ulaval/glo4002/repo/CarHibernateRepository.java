package ca.ulaval.glo4002.repo;

import java.util.ArrayList;

public class CarHibernateRepository implements CarRepository {
    public CarHibernateRepository() {
        System.out.println("Using Hibernate repository");
    }

    @Override
    public Car getById(int id) {
        System.err.println("TODO : The Hibernate ORM is not implemented yet.");
        return new Car(0, "0", new ArrayList<>(), new ArrayList<>());
    }
}
