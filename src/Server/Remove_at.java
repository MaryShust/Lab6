package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Команда Remove_at. Удаляет элемент, находящийся в заданной позиции коллекции (index).
 * @author Mary
 */
public class Remove_at implements  Command{
    private Vector vector;

    Remove_at(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload) {
        List<Object> filteredList = new ArrayList<>();
        if(vector.size()>0 && (Integer) payload < vector.size()){
            vector.remove((int) payload);
            for(int i=0;i<vector.size();i++) {
                filteredList.add("Object" + (i + 1) + ":" + vector.get(i));
            }
        }
        else{
            return ("Элемент не может быть удален");
        }
        return filteredList.toString();
    }
}
