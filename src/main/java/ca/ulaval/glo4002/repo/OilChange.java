package ca.ulaval.glo4002.repo;

public class OilChange {
    private int id; // only used by hibernate

    private int changedAtMileage;

    public OilChange(int changedAtMileage) {
        this.changedAtMileage = changedAtMileage;
    }

    protected OilChange() {
    }

    public int getChangedAtMileage() {
        return changedAtMileage;
    }
}
