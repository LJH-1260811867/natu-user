package edu.natu.systemuser.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ljiahai
 * @des Referer拦截器
 * @date 2021-09-16 17:37:47
 */
@Slf4j
public class RefererInterceptor implements HandlerInterceptor {
    @Value("${http.referer.ip}")
    private String ipAddress;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String referer = req.getHeader("referer");
        String host = req.getServerName();
        if (referer == null) {
            // 状态置为404
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        java.net.URL url;
        try {
            url = new java.net.URL(referer);
        } catch (MalformedURLException e) {
            // URL解析异常，也置为403
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        // 首先判断请求域名和referer域名是否相同
        if (!host.equals(url.getHost())) {
            List<String> allowOriginIpList = Arrays.asList(ipAddress.split(","));
            boolean allowed = allowOriginIpList.contains(url.getHost());
            if (!allowed) {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
            return allowed;
        }
        return true;
    }
}
