package Server;

import Utils.Vehicle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Vector;

/**
 * Класс Save. Сохраняет коллекцию в файл.
 * @author Mary
 */
public class Save implements Command{
    private Vector vector;

    Save(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String execute(Object payload) {

        final Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .create();
        String json = gson.toJson(vector);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\\\Users\\\\marys\\\\IdeaProjects\\\\Ex\\\\src\\\\Server\\\\FileToCollection.json"))) {
            writer.write(json);
            for(int i=0;i<vector.size();i++){
                System.out.println("Object" + (i + 1) + ":" + vector.get(i));
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Коллекция не может быть сохранена");
        }
        return null;
    }

}
