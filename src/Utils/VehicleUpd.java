package Utils;

import java.io.Serializable;

public class VehicleUpd implements Serializable {
    private long id;
    private Vehicle vehicle;

    public VehicleUpd(long id, Vehicle vehicle){
        this.id = id;
        this.vehicle = vehicle;
    }

    public long getId(){
        return id;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }
}
