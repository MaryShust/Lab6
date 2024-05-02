package Client;

import Utils.Coordinates;
import Utils.FuelType;
import Utils.Vehicle;
import Utils.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс For служит для интерактивного ввода команд добавления.
 * @author Mary
 */
public class For {
    private String variableValue;

    For(String variableValue) {
        this.variableValue = variableValue;
    }
    Scanner scanner = new Scanner(System.in);

    public Vehicle foradd() {
        int t = 0;
        try {
            System.out.println("Введите name:");
            String value1 = scanner.nextLine();
            System.out.println("Введите coordinates_x:");
            Float value2 = Float.parseFloat(scanner.nextLine());
            System.out.println("Введите coordinates_y:");
            Integer value3 = Integer.valueOf(scanner.nextLine());
            System.out.println("Введите enginePower:");
            Integer value4 = Integer.valueOf(scanner.nextLine());
            System.out.println("Введите distanceTravelled:");
            Integer value5 = Integer.valueOf(scanner.nextLine());
            System.out.println("Введите type:");
            String value6 = scanner.nextLine();
            VehicleType vailuvt = VehicleType.valueOf(value6);
            System.out.println("Введите fuelType:");
            String value7 = scanner.nextLine();
            FuelType vailuft = FuelType.valueOf(value7);


            return Vehicle.createVehicle(value1, new Coordinates(value2, value3), value4, value5, vailuvt, vailuft);

        } catch (Exception e) {
            System.out.println("Не верный ввод данных.");
            return null;
        }

    }

}
