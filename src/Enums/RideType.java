package Enums;
public enum RideType {
    DEPARTURE(1),
    ARRIVAL(2);

    private final int type;

    private RideType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}