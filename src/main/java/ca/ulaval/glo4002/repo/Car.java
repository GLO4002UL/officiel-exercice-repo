package ca.ulaval.glo4002.repo;

import java.util.List;
import java.util.stream.Collectors;

public class Car {
    private int id;
    private String serialNumber;
    private List<Wheel> wheels;
    private List<OilChange> oilChanges;

    public Car(int id, String serialNumber, List<Wheel> wheels, List<OilChange> oilChanges) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.wheels = wheels;
        this.oilChanges = oilChanges;
    }

    protected Car() {
    }

    public String describe() {
        String brands = wheels.stream().map(Wheel::getBrand).distinct().collect(Collectors.joining(", "));
        int lastOilChange = oilChanges.stream().mapToInt(OilChange::getChangedAtMileage).max().orElse(0);
        return "A car with serial number " + serialNumber + " has " + wheels.size() + " wheels of brands " + brands + " and " + oilChanges.size()
            + " oil changes, the last one was at mileage " + lastOilChange + ".";
    }
}
