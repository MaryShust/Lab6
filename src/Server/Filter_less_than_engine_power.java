package Server;

import Utils.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Команда Filter_less_than_engine_power. Выводит элементы, значение поля enginePower которых меньше заданного.
 * @author Mary
 */
public class Filter_less_than_engine_power implements Command{

    private Vector<Vehicle> vector;

    Filter_less_than_engine_power(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload){
        return vector.stream().filter(vehicle -> vehicle.getEnginePower() > (Integer) payload).toList().toString();

//        List<Vehicle> filteredList = new ArrayList<>();
//        int size = vector.size();
//        while (size > 0) {
//            Vehicle vehicle = vector.get(size-1);
//            if (vehicle.getEnginePower() > (Integer) payload){
//                filteredList.add(vehicle);
//            }
//            size--;
//        }
//        return filteredList.toString();
    }
}
