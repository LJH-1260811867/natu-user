package edu.natu.systemuser.common.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ljiahai
 * @des 缓存用户token,用以判断token是否过期
 * @date 2021-09-22 16:29:08
 */
public class UserTokenCache {
    /**
     * 登录用户的缓存
     * key 为 token
     * value 为UserToken对象
     */
    public static final Map<String, UserToken> LOGIN_USER_CACHE = new ConcurrentHashMap<>();
}
