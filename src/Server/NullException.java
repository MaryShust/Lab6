package Server;

/**
 * Ошибка для элементов Vehicle
 * @author Mary
 */
public class NullException extends RuntimeException{
    NullException(String message) {
        super(message);
    }
}
