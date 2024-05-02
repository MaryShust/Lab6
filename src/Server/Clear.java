package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Команда Clear. Очищает коллекцию.
 * @author Mary
 */
public class Clear implements Command {
    private Vector vector;

    Clear(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload){
        List<Object> filteredList = new ArrayList<>();
        for(int i=0;i<vector.size();i++){
            vector.removeAllElements();
//            filteredList.add("Object" + (i + 1) + ":" + vector.get(i));
        }
        return filteredList.toString();
    }
}
