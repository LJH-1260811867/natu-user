package edu.natu.systemuser.common.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Ljiahai
 * @des 获取spring上下文
 * @date 2021-09-22 17:09:58
 */
public final class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取 Spring 应用程序的上下文
     * @return ApplicationContext 对象
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
