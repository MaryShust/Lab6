package Utils;

import java.io.Serializable;

public class VehicleAt implements Serializable {
    private int index;
    private Vehicle vehicle;

    public VehicleAt(int index, Vehicle vehicle){
        this.index = index;
        this.vehicle = vehicle;
    }

    public int getIndex(){
        return index;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }
}
