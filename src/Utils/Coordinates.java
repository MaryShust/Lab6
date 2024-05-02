package Utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, где находятся координаты.
 * @author Mary
 */
public class Coordinates implements Serializable {


    private float x; //Значение поля должно быть больше -845
    private Integer y; //Поле не может быть null

    public Coordinates(float x, Integer y) {
        if (x <= -845 || y == null) {
            System.out.println("Не верные данные");
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public double getCoordinates_x() {
        return x;
    }

    public int getCoordinates_y() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}