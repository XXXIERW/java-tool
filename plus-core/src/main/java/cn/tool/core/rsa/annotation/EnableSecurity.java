package cn.tool.core.rsa.annotation;

import cn.tool.core.rsa.advice.EncryptRequestBodyAdvice;
import cn.tool.core.rsa.advice.EncryptResponseBodyAdvice;
import cn.tool.core.rsa.config.SecretKeyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author:Bobby
 * DateTime:2019/4/9 16:44
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({SecretKeyConfig.class,
        EncryptResponseBodyAdvice.class,
        EncryptRequestBodyAdvice.class})
public @interface EnableSecurity{

}
