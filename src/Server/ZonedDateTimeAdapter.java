package Server;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс ZonedDateTimeAdapter. Нужен для сериализации и десериализации ZonedDateTime.
 * @author Mary
 */

public class ZonedDateTimeAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
    /**
     *
     * @param date
     * @param typeOfSrc
     * @param context
     * @return
     */
    @Override
    public JsonElement serialize(ZonedDateTime date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }

    /**
     *
     * @param json
     * @param type
     * @param context
     * @return
     * @throws JsonParseException
     */
    @Override
    public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString());

    }
}

