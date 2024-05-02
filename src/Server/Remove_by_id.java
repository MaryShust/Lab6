package Server;

import Utils.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Команда Remove_by_id. Удаляет элемент из коллекции по его id.
 * @author Mary
 */
public class Remove_by_id  implements Command {
    private Vector<Vehicle> vector;

    Remove_by_id(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload) {
        List<Object> filteredList = new ArrayList<>();
        if(vector.size()>0){
            vector.removeIf(vehicle -> (vehicle.getId().equals((Long) payload)));
        }
        else{
            return ("Элемент не может быть удален");
        }
        for(int i=0;i<vector.size();i++){
            filteredList.add("Object" + (i + 1) + ":" + vector.get(i));
        }
        return filteredList.toString();
    }
}