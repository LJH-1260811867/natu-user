package edu.natu.systemuser.config.security;

import edu.natu.systemuser.common.exception.UnAuthException;
import edu.natu.systemuser.common.token.JwtIgnore;
import edu.natu.systemuser.common.utils.AssertUtil;
import edu.natu.systemuser.common.utils.JwtTokenUtil;
import edu.natu.systemuser.common.utils.LocalThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Ljiahai
 * @des 认证拦截器
 * @date 2021-09-17 15:57:14
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法，直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //如果方法有JwtIgnore注解，直接通过
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtIgnore.class)) {
            JwtIgnore jwtIgnore = method.getAnnotation(JwtIgnore.class);
            if(jwtIgnore.value()){
                return true;
            }
        }
        log.debug("method:{}",method.getName());
        // 从http请求头中取出token
        final String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        AssertUtil.hasText(token, "token为空，鉴权失败！");
        //验证，并获取token内部信息是否合法
        String userToken = JwtTokenUtil.verifyToken(token);
        //验证token是否过期
        if(!JwtTokenUtil.ifTokenExpired(token)) {
            throw new UnAuthException("登陆超时，请重新登录");
        }
        // 刷新token过期时间
        JwtTokenUtil.refreshTokenExpired(token);
        //将userToken放入本地缓存
        LocalThread.setUserToken(userToken);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //方法结束后，移除缓存的token
        LocalThread.removeUserToken();
    }
}
