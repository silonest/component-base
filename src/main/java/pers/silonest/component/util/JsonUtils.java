package pers.silonest.component.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

import pers.silonest.component.base.exception.JsonParserException;

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
   * 私有的 {@link ObjectMapper} 实例.用来转译Json数据。
   */
  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    // 如果被转换的对象不可序列化时，将它们序列化成空对象。如果设置成true，则会抛出不能序列化的异常。
    MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    // 可以将json字符串中的""转换到object中属性的""，如果设置成false，则只能转换null。
    MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    // 可以使用反斜杠的形式转译特殊字符。
    MAPPER.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    // 在json字符串中可以使用java/c++类型的注释，如果设置成false，则不能正确解析这些注解。
    MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    // 可以将json字符串中的某些特殊值转换为数字型，比如NaN转换成0，INF转换成正无穷，-INF转换为负无穷。
    MAPPER.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
    // 可以解析有如"00001"这样带前导"0"的数字，由于标准json不允许这种情况，如果设置成false，遇到前导0的情况会抛出异常。
    MAPPER.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
    // 可以解析使用单引号作为字符串引用的json数据，用于标准json只允许双引号，如果设置成false，遇到单引号情况会抛出异常。
    MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    // 可以解析json字符串中的ASC码小于32的特殊字符，如果设置成false，遇到该情况时会抛出异常。
    MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    // 可以解析不带引号的字符名，由于不符合json标准，果设置成false，会抛出异常。
    MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    // 遇到没有声明set方法的属性时，不会抛出异常，如果设置成true，则会抛出异常。
    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * 不能实例化.
   */
  private JsonUtils() {}

  /**
   * 将对象转换成json字符串.
   *
   * @param object 存储数据的数据源
   *
   * @return 从数据源转换成的json字符串
   */
  public static String toJson(Object object) {
    if (!ObjectUtils.isEmpty(object)) {
      try {
        return MAPPER.writeValueAsString(object);
      } catch (JsonProcessingException e) {
        throw new JsonParserException("Processing object to json is failed!");
      }
    } else {
      return null;
    }
  }

  public static JsonNode toJsonNode(String json) {
    if (!StringUtils.isEmpty(json)) {
      try {
        JsonNode node = MAPPER.readTree(json);
        return node;
      } catch (IOException e) {
        throw new JsonParserException("Processing object to json is failed!");
      }
    } else {
      return null;
    }
  }

  public static String getJson(String json, String path) {
    if (!StringUtils.isEmpty(json)) {
      try {
        JsonNode node = MAPPER.readTree(json);
        String[] pathSet = path.split("\\.");
        for (String name : pathSet) {
          node = node.get(name);
        }
        return node.toString();
      } catch (JsonProcessingException e) {
        throw new JsonParserException("Processing object to json is failed!");
      } catch (IOException e) {
        throw new JsonParserException("Processing object to json is failed!");
      }
    } else {
      return null;
    }
  }

  /**
   * 将对象转换成JsonNode.
   * 
   * @param object 存储数据的数据源
   * @return 从数据源转换成的JsonNode
   */
  public static JsonNode toJsonNode(Object object) {
    if (!ObjectUtils.isEmpty(object)) {
      if (object instanceof byte[]) {
        try {
          return MAPPER.readTree((byte[]) object);
        } catch (JsonProcessingException e) {
          throw new JsonParserException("Processing json charset failed!");
        } catch (IOException e) {
          throw new JsonParserException("Processing json charset failed!");
        }
      }
      String jsonStr;
      if (object instanceof String) {
        jsonStr = (String) object;
      } else {
        jsonStr = toJson(object);
      }
      try {
        return MAPPER.readTree(jsonStr);
      } catch (JsonProcessingException e) {
        throw new JsonParserException("Processing json charset failed!");
      } catch (IOException e) {
        throw new JsonParserException("Processing json charset failed!");
      }
    } else {
      return null;
    }
  }

  /**
   * 将json字符串转换成对象.
   *
   * @param json字符串
   * @param 转换的对象类型
   * @param <T> 结果的类型
   *
   * @return json字符串转换成的object对象。
   */
  public static <T> T toObject(String json, Class<T> clazz) {
    if (StringUtils.isNotEmpty(json)) {
      try {
        return MAPPER.readValue(json, clazz);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      return null;
    }
  }

  /**
   * 将json数据转换成对象.
   *
   * @param json字符串
   * @param 对象的引用类型
   * @param <T> 结果的类型
   *
   * @return json字符串转换成的object对象。
   */
  public static <T> T toObject(String json, TypeReference<T> typeReference) {
    if (StringUtils.isNotEmpty(json)) {
      try {
        return MAPPER.readValue(json, typeReference);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      return null;
    }
  }

  /**
   * 将json数据转换成对象.使用自定义的jsonparser将json字符串转换成对象。
   *
   * @param json字符串
   * @param jsonparser的实例
   * @param <T> 结果的类型
   *
   * @return json字符串转换成的object对象。
   */
  public static <T> T toObject(String json, JsonNodeParser<T> parser) {
    if (StringUtils.isNotEmpty(json)) {
      try {
        return parser.parse(MAPPER.readTree(json));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      return null;
    }
  }

  public static <T> T toObject(String json, String jsonPath, Class<T> clazz) {
    if (StringUtils.isNotEmpty(json)) {
      String jsonPathString = getJson(json, jsonPath);
      return toObject(jsonPathString, clazz);
    } else {
      return null;
    }
  }

  public interface JsonNodeParser<T> {
    T parse(JsonNode jsonNode);
  }

}
