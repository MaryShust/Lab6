package Server;

import java.util.Vector;

/**
 * Команда Info. Выводит справку от коллекции.
 * @author Mary
 */
public class Info implements Command {

    private Vector vector;

    Info(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload) {
        int size = vector.size();
        if (size > 0) {
            return ("Название коллекции: "+  vector.getClass().getName()) + "\n" +
                    ("Время инициализации: " + System.currentTimeMillis()) + "\n" +
                    ("Размер коллекции: " + size) + "\n"  +
                    ("Первый элемент: " + vector.get(0)) + "\n" +
                    ("Последний элемент: " + vector.get(size-1));
        } else {
            return ("Название коллекции: "+  vector.getClass().getName()) + "\n" +
                    ("Время инициализации: " + System.currentTimeMillis()) + "\n" +
                    ("Размер коллекции: " + size);
        }
    }
}
