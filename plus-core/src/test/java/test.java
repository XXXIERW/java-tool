import cn.tool.core.aop.aspect.AccessLimit;
import org.junit.Test;

public class test {
    @Test
    @AccessLimit(second = 1,maxCount = 5) // 1s请求五次
    public void test(){

    }
}
