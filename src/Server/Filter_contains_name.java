package Server;
import Utils.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Команда Filter_contains_name. Выводит элементы, значение поля name которых содержит заданную подстроку.
 * @author Mary
 */

public class Filter_contains_name implements Command {

    private Vector<Vehicle> vector;

    Filter_contains_name(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload) {
        return vector.stream().filter(vehicle -> vehicle.getName().contains((CharSequence) payload)).toList().toString();
//        List<Object> filteredList = new ArrayList<>();
//        int size = vector.size();
//        while (size > 0) {
//            Vehicle vehicle = vector.get(size-1);
//            if (vehicle.getName().contains((CharSequence) payload)){
//                filteredList.add(vehicle);
//            }
//            size--;
//        }
//        return filteredList.toString();
    }
}
