package ca.ulaval.glo4002.repo;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarJsonRepository implements CarRepository {
    public static final String WHEELS_FILE = "/json/wheels.json";
    public static final String OIL_CHANGES_FILE = "/json/oil_changes.json";
    public static final String CARS_FILE = "/json/cars.json";
    private final ObjectMapper mapper;

    public CarJsonRepository() {
        System.out.println("Using JSON repository");
        this.mapper = new ObjectMapper();
    }

    @Override
    public Car getById(int id) {
        List<Wheel> wheels = readFile(WHEELS_FILE, new TypeReference<List<WheelDto>>() {
        }).stream()
            .filter(x -> x.carId == id)
            .map(x -> new Wheel(x.brand))
            .toList();

        List<OilChange> oilChanges = readFile(OIL_CHANGES_FILE, new TypeReference<List<OilChangeDto>>() {
        }).stream()
            .filter(x -> x.carId == id)
            .map(x -> new OilChange(x.changedAtMileage))
            .toList();

        return readFile(CARS_FILE, new TypeReference<List<CarDto>>() {
        }).stream()
            .filter(x -> x.id == id)
            .map(x -> new Car(x.id, x.serialNumber, wheels, oilChanges))
            .findFirst()
            .orElseThrow();
    }

    private <T> T readFile(String file, TypeReference<T> clazz) {
        try {
            URL url = this.getClass().getResource(file);
            return mapper.readValue(url, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file " + file + " as JSON.", e);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class CarDto {
        @JsonProperty("id")
        public int id;
        @JsonProperty("serial_number")
        public String serialNumber;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class WheelDto {
        @JsonProperty("brand")
        public String brand;
        @JsonProperty("car_id")
        public int carId;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class OilChangeDto {
        @JsonProperty("car_id")
        public int carId;
        @JsonProperty("changed_at_mileage")
        public int changedAtMileage;
    }
}
