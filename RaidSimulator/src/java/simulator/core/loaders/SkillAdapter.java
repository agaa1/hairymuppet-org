package simulator.core.loaders;

import com.google.gson.*;
import simulator.core.types.skills.ASkill;

import java.lang.reflect.Type;

/**
 * This class is required to deserialize the skills from json. As it is an abstract class heirarchy, and as json elements
 * do not explicitely define class type inside the json, we have to use a special "type" variable.
 * <p>
 * This variable is manually inserted into the json when we serialize it, and then used to determine the implementing
 * class type when we deserialize.
 * <p>
 * This type of adapter will be required for all deserialization operations involving abstract classes.
 */
public class SkillAdapter implements JsonSerializer<ASkill>, JsonDeserializer<ASkill> {

    @Override
    public JsonElement serialize(ASkill src, Type type, JsonSerializationContext context) {
        JsonObject result = context.serialize(src, src.getClass()).getAsJsonObject();

        // Required for Deserialization, see below
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));

        return result;
    }

    @Override
    public ASkill deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Use the type we stored in the json before to determine which type of skill it is, then use the correct class to deserialize the json
        String type = jsonObject.get("type").getAsString();

        try {
            return context.deserialize(json, Class.forName("simulator.core.types.skills." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }


}
