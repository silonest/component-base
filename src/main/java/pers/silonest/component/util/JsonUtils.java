package pers.silonest.component.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * 使用jackson操作json.
 * 
 * @author 陈晨
 * @time 2017年4月2日 下午6:12:35
 * @since v1.0.0
 * @version v0.0.1
 */
public abstract class JsonUtils {
  /**
   * The default private {@link ObjectMapper} instance.
   */
  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * Private constructor.
   */
  private JsonUtils() {}

  /**
   * Convert an object to JSON data.
   *
   * @param object the object
   *
   * @return the JSON data parsed from original object
   */
  public static String toJson(Object object) {
    try {
      return MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Convert Json data to an object of specified type.
   *
   * @param json the JSON data
   * @param clazz the class instance of result object
   * @param <T> the type of result object
   *
   * @return the result object parsed from original JSON data
   */
  public static <T> T toObject(String json, Class<T> clazz) {
    try {
      return MAPPER.readValue(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Convert JSON data to an object of specified type.
   *
   * @param json the JSON data
   * @param typeReference the type reference
   * @param <T> the type of result object
   *
   * @return the result object parsed from original JSON data
   */
  public static <T> T toObject(String json, TypeReference<T> typeReference) {
    try {
      return MAPPER.readValue(json, typeReference);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Convert JSON data to an object of specified type.
   *
   * @param json the JSON data
   * @param parser the parser to parse JSON data
   * @param <T> the type of result object
   *
   * @return the result object parsed from original JSON data
   */
  public static <T> T toObject(String json, JsonNodeParser<T> parser) {
    try {
      return parser.parse(MAPPER.readTree(json));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * A parser that parse a {@link JsonNode} instance to an object of specified type.
   *
   * @param <T> the type of result object
   */
  public interface JsonNodeParser<T> {
    /**
     * Parse an {@link JsonNode} instance to an Object of type <code>T</code>.
     *
     * @param jsonNode the instance to parse
     *
     * @return the result object of type <code>T</code>
     */
    T parse(JsonNode jsonNode);
  }

}
