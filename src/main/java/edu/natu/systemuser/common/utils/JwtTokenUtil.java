package edu.natu.systemuser.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import edu.natu.systemuser.business.user.model.User;
import edu.natu.systemuser.common.token.UserToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Date;
import java.util.Objects;

import static edu.natu.systemuser.common.token.UserTokenCache.LOGIN_USER_CACHE;

/**
 * @author Ljiahai
 * @des JwtTokenUtil工具类，用于创建token、验证token
 * @date 2021-09-17 15:31:56
 */
public class JwtTokenUtil {
    /**
     * 定义token返回头部
     */
    public static final String AUTH_HEADER_KEY = "token";

    /**
     * 签名密钥
     */
    private static String KEY = null;

    /**
     * token有效期,默认两小时
     */
    public static Long EFFECTIVE_DURATION = 1000L*60*60*2;

    /**
     * 获取环境配置
     */
    public static Environment ENV;

    static {
        ENV = ApplicationContextUtil.getApplicationContext().getBean(Environment.class);
        KEY = ENV.getProperty("jwt.key");
        EFFECTIVE_DURATION = Long.valueOf(Objects.requireNonNull(ENV.getProperty("jwt.effective-duration")));
    }


    /**
     * 创建TOKEN
     * @param content UserTokenD对象
     * @return token
     */
    public static String createToken(String content){
        return  JWT.create()
                .withSubject(content)
                //.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(KEY));
    }

    /**
     * 验证token是否合法
     * @param token token
     */
    public static String verifyToken(String token) throws Exception {
        try {
            return JWT.require(Algorithm.HMAC512(KEY))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (TokenExpiredException e){
            throw new Exception("token已失效，请重新登录",e);
        } catch (JWTVerificationException e) {
            throw new Exception("token验证失败！",e);
        } catch (Exception e) {
            throw new Exception("token验证失败！",e);
        }
    }

    /**
     *
     * @param token token
     * @return boolean
     */
    public static boolean ifTokenExpired(String token){
        UserToken userToken = LOGIN_USER_CACHE.get(token);
        if (Objects.isNull(userToken)) {
            return false;
        }
        if (System.currentTimeMillis() > userToken.getExpiresAt().getTime()) {
            LOGIN_USER_CACHE.remove(token);
            return false;
        }
        return true;
    }

    /**
     * @param token token
     */
    public static void refreshTokenExpired(String token) {
        UserToken userToken = LOGIN_USER_CACHE.get(token);
        userToken.setExpiresAt(new Date(System.currentTimeMillis() + EFFECTIVE_DURATION));
        LOGIN_USER_CACHE.replace(token, userToken);
    }
}
