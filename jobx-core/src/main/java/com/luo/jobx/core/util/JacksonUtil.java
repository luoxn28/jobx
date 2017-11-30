package com.luo.jobx.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Jackson 序列化类
 *
 * @author xiangnan
 */
public class JacksonUtil {

    private final static Logger logger = LogManager.getLogger(JacksonUtil.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("JacksonUtil.toJson 错误: " + e);
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

}
