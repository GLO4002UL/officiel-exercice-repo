package ca.ulaval.glo4002.repo;

import jakarta.inject.Inject;

public class CarService {
    private CarRepository repo;

    @Inject
    public CarService(CarRepository repo) {
        this.repo = repo;
    }

    public String getCarDescription(int carId) {
        Car car = repo.getById(carId);
        return car.describe();
    }
}
