package edu.natu.systemuser.common.utils;

import org.apache.kafka.common.protocol.types.Field;

import java.util.UUID;

/**
 * @author Ljiahai
 * @des UUID工具类
 * @date 2021-09-17 15:42:45
 */
public class UUIDUtil  {

    /**
     * 获取随机GUID串
     * @return 随机的GUID字符串
     */
    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String guid = uuid.toString();
        guid = guid.replaceAll("-", "");
        return guid.toLowerCase();
    }
}
