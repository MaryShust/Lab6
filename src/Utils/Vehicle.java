package Utils;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Класс Vehicle
 * @author  Mary
 */
public class Vehicle implements Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int enginePower; //Значение поля должно быть больше 0
    private int distanceTravelled; //Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null
    private FuelType fuelType; //Поле может быть null

    /**
     *
     * @param id
     * @param name
     * @param coordinates
     * @param creationDate
     * @param enginePower
     * @param distanceTravelled
     * @param type
     * @param fuelType
     */

    Vehicle(
            Long id,
            String name,
            Coordinates coordinates,
            ZonedDateTime creationDate,
            int enginePower,
            int distanceTravelled,
            VehicleType type,
            FuelType fuelType
    ) {
        if (id == null || id < 0 || name == null || name.equals("null") || coordinates == null || (coordinates.getCoordinates_x() == 0 && coordinates.getCoordinates_y() == 0) || enginePower < 0 || distanceTravelled < 0 || type == null || fuelType == null) {
            throw new NullException("Поле должно быть заполнено");
        } else {
            this.id = id;
            this.name = name;
            this.coordinates = coordinates;
            this.creationDate = creationDate;
            this.enginePower = enginePower;
            this.distanceTravelled = distanceTravelled;
            this.type = type;
            this.fuelType = fuelType;
        }

    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public  ZonedDateTime getCreationDate(){
        return creationDate;
    }

    public int getEnginePower(){
        return enginePower;
    }

    public int getDistanceTravelled(){
        return distanceTravelled;
    }

    public VehicleType getType(){
        return type;
    }

    public FuelType getFuelType(){
        return fuelType;
    }


    /**
     * Метод для создания Vehicle
     * @author Mary
     * @param name
     * @param coordinates
     * @param enginePower
     * @param distanceTravelled
     * @param type
     * @param fuelType
     * @return
     * @throws Exception
     */



    public static Vehicle createVehicle(
            String name,
            Coordinates coordinates,
            int enginePower,
            int distanceTravelled,
            VehicleType type,
            FuelType fuelType
    ) throws Exception {
        if (name == null || name.equals("null")) {
            throw new NullException("Поле должно быть заполнено");
        }
        if (coordinates == null || (coordinates.getCoordinates_x() == 0 && coordinates.getCoordinates_y() == 0)){
            throw new NullException("Поле должно быть заполнено");
        }
        if (enginePower < 0) {
            throw new Exception("Поле должно быть больше 0");
        }
        if (distanceTravelled < 0) {
            throw new Exception("Поле должно быть больше 0");
        }
        if (type == null){
            throw new NullException("Поле должно быть заполнено");
        }
        if (fuelType == null){
            throw new NullException("Поле должно быть заполнено");
        }
        return new Vehicle(
                Math.abs(UUID.randomUUID().getLeastSignificantBits()),
                name,
                coordinates,
                ZonedDateTime.now(),
                enginePower,
                distanceTravelled,
                type,
                fuelType
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) && Objects.equals(name, vehicle.name) && Objects.equals(coordinates, vehicle.coordinates)
                && Objects.equals(creationDate, vehicle.creationDate) && enginePower == vehicle.enginePower
                && distanceTravelled == vehicle.distanceTravelled && Objects.equals(type, vehicle.type)
                && Objects.equals(fuelType, vehicle.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, enginePower, distanceTravelled, type, fuelType);
    }

    @Override
    public String toString() {
        String info = "";
        info += "Транспортное средство №" + id;
        info += " (добавлен " + creationDate.toString() + ")";
        info += "\n Название: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Мощность двигателя: " +  enginePower;
        info += "\n Пройденное расстояние: " + distanceTravelled;
        info += "\n Тип " + type;
        info += "\n Тип топлива: " + fuelType;
        return info;
    }
}