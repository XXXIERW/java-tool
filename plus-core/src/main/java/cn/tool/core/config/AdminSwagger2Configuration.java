package cn.tool.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger在线文档配置<br>
 * 项目启动后可通过地址：http://host:ip/swagger-ui.html 查看在线文档
 *    <p> 进行swagger的全局配置在每次进行登陆的时候进行全局包的扫描</p>
 *    后面可以直接引用使用demo  会在demo里面进行详细注解
 * @version 2018-07-24
 */

@Configuration
@EnableSwagger2
//@EnableSwaggerBootstrapUI // 原本的ui  旧版本使用
public class AdminSwagger2Configuration {
    @Bean
    public Docket adminDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(adminApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.linlinjava.litemall.admin.web")) // 对包里面所有的接口进行扫描
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("一个商城api可以在这里面进行使用，在对商城的api的获取")
                .description("商城后台管理")
                .termsOfServiceUrl("www.biboom.cn")
                .contact("raveyXie")
                .version("1.0")
                .build();
    }
}
