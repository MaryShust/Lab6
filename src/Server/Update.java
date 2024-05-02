package Server;

import Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Команда Update. Обновляет значение элемента коллекции, id которого равен заданному.
 * @author Mary
 */
public class Update implements Command {
    private final Vector<Vehicle> vector;
    Update(Vector vector) {
        this.vector = vector;
    }
    List<Object> filteredList = new ArrayList<>();

    @Override
    public String execute(Object payload) {
        VehicleUpd up = (VehicleUpd) payload;
        if(vector.size()>0) {
            boolean isUpdated = false;
            for (int i = 0; i < vector.size(); i++) {
                if (vector.get(i).getId().equals(up.getId())) {
                    try {
                        vector.set(i, up.getVehicle());
                        for(int j=0;j<vector.size();j++){
                            filteredList.add("Object" + (j + 1) + ":" + vector.get(j));
                        }
                    } catch (Exception e) {
                        return ("Элемент не может быть обновлен");
                    }
                    isUpdated = true;
                    break;
                }
            }
            if (isUpdated == false) {
                return ("Элемент не может быть обновлен");
            }
        }
        else{
            return ("Элемент не может быть обновлен");
        }
        return filteredList.toString();
    }
}
