package controller;

import cn.tool.core.aop.aspect.RequiresPermissionsDesc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "注解")
public class swaggerController {

    @RequestMapping("/test")
    @ApiOperation(value = "测试")
    @RequiresPermissionsDesc(menu="权限校验",button = "确认")
    public void test(){
        return;
    }
}
