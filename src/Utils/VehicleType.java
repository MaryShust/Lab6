package Utils;

/**
 * Enum VehicleType.
 * @author Mary
 */
public enum VehicleType {
    CAR("car"),
    DRONE("drone"),
    BOAT("boat"),
    BICYCLE("bicycle");

    private String type;

    VehicleType (String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}