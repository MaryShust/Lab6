package Server;

import Utils.VehicleAt;

import java.util.Vector;

/**
 * Команда Insert_at. Добавляет новый элемент в заданную позицию.
 * @author Mary
 */

public class Insert_at implements Command{
    private Vector vector;

    Insert_at(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload) {
        VehicleAt pl = (VehicleAt) payload;
        try {
            vector.add(pl);
            for(int i=0;i<vector.size();i++) {
                return ("Object" + (i + 1) + ":" + vector.get(i)) + "\n" + " ";
            }
        } catch (Exception e) {
            return ("Не удалось выполнить команду");
        }
        return null;
    }
}
