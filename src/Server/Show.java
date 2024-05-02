package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Команда Show. Выведет в стандартный поток вывода все элементы коллекции в строковом представлении.
 * @author Mary
 */
public class Show implements Command {

    private Vector vector;

    Show(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload){
        List<Object> filteredList = new ArrayList<>();
        for(int i=0;i<vector.size();i++){
            filteredList.add("Object" + (i + 1) + ":" + vector.get(i));
        }
        return filteredList.toString();
    }
}
