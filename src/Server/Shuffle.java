package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Collections;

/**
 * Команда Shuffle. Перемешает элементы коллекции в случайном порядке.
 * @author Mary
 */
public class Shuffle implements Command {
    private Vector vector;

    Shuffle(Vector vector) {
        this.vector = vector;
    }
    @Override
    public String execute(Object payload) {
        List<Object> filteredList = new ArrayList<>();
        Collections.shuffle(vector);
        for(int i=0;i<vector.size();i++) {
            filteredList.add("Object" + (i + 1) + ":" + vector.get(i));
        }
        return filteredList.toString();
    }
 }
