package cn.tool.core.config;

import cn.tool.core.aop.aspect.AccessLimitAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 注册重写 配置类实现接口防刷
 */
@Slf4j
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AccessLimitAspect requestLimitIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLimitIntercept);
    }
}
