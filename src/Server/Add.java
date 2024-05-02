package Server;

import Utils.Coordinates;
import Utils.FuelType;
import Utils.Vehicle;
import Utils.VehicleType;

import java.util.*;

/**
 * Команда Add. Добавляет новый элемент в коллекцию.
 * @author Mary
 */
public class Add implements Command {
    private Vector vector;

    Add(Vector vector) {
        this.vector = vector;
    }


    @Override
    public String execute(Object payload){
        List<Object> filteredList = new ArrayList<>();
        try {
            Vehicle fadd = (Vehicle) payload;
            vector.add(fadd);
            for(int i=0;i<vector.size();i++) {
                filteredList.add("Object" + (i + 1) + ":" + vector.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return ("Элемент не может быть добавлен. Не верный ввод элемента.");
        }
        return filteredList.toString();
    }
}
