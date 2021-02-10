package cn.tool.core.utils;

import cn.tool.core.exception.ServiceException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Json工具类 进行数据类型的转化 String -> Object , Object -> String
 */
public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new GuavaModule());
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
    }

    private JsonUtils() {
    }

    /**
     * 转化空的Json类型
     * @param json
     * @return
     */
    public static String toEmptyJsonObject(String json) {
        if (StringUtils.isBlank(json) || "[]".equals(json.trim())) {
            return "{}";
        }
        return json;
    }

    public static <T> T stringToObject(String jsonString, Class<T> cla) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, cla);
        } catch (IOException e) {
            logger.error(String.format("convert json string to obj error, json:%s", jsonString), e);
            return null;
        }
    }

    /**
     * TypeReference  一个复杂泛型参数，需要传递真的类型；
     * @param jsonString
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T stringToObject(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            logger.error(String.format("convert json string to obj error, json:%s", jsonString), e);
            return null;
        }
    }

    public static String objectToString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return mapper.writer().writeValueAsString(object);
        } catch (IOException e) {
            logger.error(String.format("convert obj to json string error, obj:%s", object), e);
            return null;
        }
    }

    /**
     * 将json转换为指定的对象，该方法将异常抛出交由上层处理
     * @param jsonString
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T stringToObject2(String jsonString, Class<T> cla) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, cla);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 将json转换为指定的类型，该方法将异常抛出交由上层处理
     * @param jsonString
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T stringToObject2(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 将对象转换为json，该方法将异常抛出交由上层处理
     * @param object
     * @return
     */
    public static String objectToString2(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return mapper.writer().writeValueAsString(object);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    public static ObjectMapper mapper() {
        return mapper;
    }

    public static ObjectReader reader() {
        return mapper.reader();
    }

    public static void main(String[] args) {
//        JsonResult jr = stringToObject("", JsonResult.class);
//        System.out.println(jr);
    }
}
