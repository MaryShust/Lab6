package Utils;

/**
 * Enum FuelType.
 * @author Mary
 */
public enum FuelType {
    ELECTRICITY("electricity"),
    ALCOHOL("alcohol"),
    MANPOWER("manpower"),
    PLASMA("plasma");

    private String fuelType;

    FuelType(String fuelType){
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

}