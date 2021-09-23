package edu.natu.systemuser.config.context;

import edu.natu.systemuser.common.utils.ApplicationContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ljiahai
 * @des spring上下文配置
 * @date 2021-09-22 17:59:53
 */
@Configuration
public class ContextConfig {

    @Bean
    public ApplicationContextUtil registerApplicationContextUtil() {
        return new ApplicationContextUtil();
    }
}
