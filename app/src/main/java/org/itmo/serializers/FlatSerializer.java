package org.itmo.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.itmo.models.Flat;

/**
 * The FlatSerializer class provides methods for serializing and deserializing Flat objects to/from JSON format.
 */
/**
 * The FlatSerializer class provides methods to serialize and deserialize Flat objects to JSON format.
 */
public class FlatSerializer {
    static JsonSerializer<ZonedDateTime> serializer = new JsonSerializer<ZonedDateTime>() {
        @Override
        public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        }
    };

    static JsonDeserializer<ZonedDateTime> deserializer = new JsonDeserializer<ZonedDateTime>() {
        @Override
        public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return ZonedDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
        }
    };
    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, serializer)
            .registerTypeAdapter(ZonedDateTime.class, deserializer)
            .create();

    /**
     * Serializes a Flat object to JSON format.
     *
     * @param flat The Flat object to be serialized.
     * @return The JSON representation of the Flat object.
     */
    static public String jsonDumps(Flat flat) {
        return FlatSerializer.gson.toJson(flat);
    }

    /**
     * Deserializes a JSON string to a Flat object.
     *
     * @param json The JSON string to be deserialized.
     * @return The Flat object deserialized from the JSON string.
     */
    static public Flat jsonLoads(String json) {
        return FlatSerializer.gson.fromJson(json, Flat.class);
    }
}