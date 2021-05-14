package cn.tool.core.aop.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
public class AccessLimitAspect extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private final static String REQUEST_OUT_LIMIT = "API_REQUEST_LIMIT";
    // 前置实现
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求是否属于方法的请求
        /**
         * isAssignableFrom() 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
         * isAssignableFrom()方法是判断是否为某个类的父类
         * instanceof关键字是判断是否某个类的子类
         */
        // 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
        if(handler instanceof HandlerMethod){
            //HandlerMethod 封装方法定义相关的信息,如类,方法,参数等
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod(); // 拿到方法
            // 获取方法中是否包含注解
            AccessLimit methodAnnotation = method.getAnnotation(AccessLimit.class);
            //获取 类中是否包含注解，也就是controller 是否有注解
            AccessLimit classAnnotation = method.getDeclaringClass().getAnnotation(AccessLimit.class);
            // 如果 方法上有注解就优先选择方法上的参数，否则类上的参数
            AccessLimit requestLimit = methodAnnotation != null ? methodAnnotation : classAnnotation;
            if(requestLimit != null){
                if(isLimit(request,requestLimit)){
                    responseOut(response,REQUEST_OUT_LIMIT);
                    return false;
                }
            }
        }

        return true;

    }

    //判断请求是否受限
    private boolean isLimit(HttpServletRequest request,AccessLimit requestLimit){
        // 受限的redis 缓存key ,因为这里用浏览器做测试，我就用sessionid 来做唯一key,如果是app ,可以使用 用户ID 之类的唯一标识。
        String limitKey = request.getServletPath() + request.getSession().getId();
        // 从缓存中获取，当前这个请求访问了几次
        Integer redisCount = (Integer) redisTemplate.opsForValue().get(limitKey); // 当前访问次数
        if(redisCount == null){
            //初始 次数
            redisTemplate.opsForValue().set(limitKey,1,requestLimit.second(), TimeUnit.SECONDS);
        }else{
            if(redisCount.intValue() >= requestLimit.maxCount()){
                return true;
            }
            // 次数自增
            redisTemplate.opsForValue().increment(limitKey,redisCount); // 进行自增
        }
        return false;
    }

    /**
     * 回写给客户端
     * @param response
     * @throws IOException
     */
    private void responseOut(HttpServletResponse response, String rs) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out ;
        out = response.getWriter();
        out.append(rs);
    }
}
