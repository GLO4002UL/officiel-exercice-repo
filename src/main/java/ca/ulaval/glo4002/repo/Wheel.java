package ca.ulaval.glo4002.repo;

public class Wheel {
    private String serialNumber;

    private String brand;

    public Wheel(String brand) {
        this.brand = brand;
    }

    protected Wheel() {
    }

    public String getBrand() {
        return brand;
    }
}
