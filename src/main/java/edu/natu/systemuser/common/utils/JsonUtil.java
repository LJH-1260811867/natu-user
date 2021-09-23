package edu.natu.systemuser.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LJH
 * @des JSON工具类
 * @date 2021-09-17 16:10:49
 */
public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * @author LJH @date 2021-08-10 09:57:20
     * @des 对象转JSON字符串
     */
    public static String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Object to Json String error,object ==>{}",object);
        }
        return null;
    }

    /**
     * @author LJH @date 2021-08-10 09:57:20
     * @des JSON字符串转对象
     */
    public static <T> T jsonToObject(String jsonString, Class<T> classz) {
        try {
            return mapper.readValue(jsonString, classz);
        } catch (JsonProcessingException e) {
            log.error("Object to Json String error,jsonString ==>{}", jsonString);
        }
        return null;
    }
}
