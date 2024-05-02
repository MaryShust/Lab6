package Server;

import Utils.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Команда Count_less_than_type. Выводит количество элементов, значение поля type которых меньше заданного.
 * @author Mary
 */
public class Count_less_than_type implements Command{
    private Vector<Vehicle> vector;

    Count_less_than_type(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload) {
        return vector.stream().filter(vehicle -> vehicle.getName().length() < ((String) payload).length()).toList().toString();
//        List<Vehicle> filteredList = new ArrayList<>();
//        int size = vector.size();
//        while (size > 0) {
//            Vehicle vehicle = vector.get(size-1);
//            String type = (String) payload;
//            String typev = vehicle.getName();
//            if (typev.length() < type.length()){
//                filteredList.add(vehicle);
//            }
//            size--;
//        }
//        return filteredList.toString();
    }
}
