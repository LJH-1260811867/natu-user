package edu.natu.systemuser.common.utils;

import edu.natu.systemuser.common.token.UserToken;

/**
 * @author Ljiahai
 * @des 线程缓存工具类
 * @date 2021-09-17 16:04:58
 */
public class LocalThread {
    /**
     * 本地线程缓存token
     */
    private static final ThreadLocal<String> LOCAL_THREAD = new ThreadLocal<>();

    /**
     * 设置token信息
     * @param content
     */
    public static void setUserToken(String content){
        removeUserToken();
        LOCAL_THREAD.set(content);
    }

    /**
     * 获取token信息
     * @return
     */
    public static UserToken getUserToken(){
        if(LOCAL_THREAD.get() != null){
            UserToken userToken = JsonUtil.jsonToObject(LOCAL_THREAD.get() , UserToken.class);
            return userToken;
        }
        return null;
    }

    /**
     * 移除token信息
     * @return
     */
    public static void removeUserToken(){
        if(LOCAL_THREAD.get() != null){
            LOCAL_THREAD.remove();
        }
    }
}
