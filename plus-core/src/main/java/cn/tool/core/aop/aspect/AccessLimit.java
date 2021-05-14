package cn.tool.core.aop.aspect;

import java.lang.annotation.*;

/**
 * 接口防刷 使用  springboot + redis
 * 实现原理：
 * 在你请求的时候，服务器通过redis 记录下你请求的次数，如果次数超过限制就不给访问。
 * 在redis 保存的key 是有时效性的，过期就会删除。 这样的好处有
 * 可以直接在注解里面限制该用户一次性请求多少 = 比如人机：1分钟可能请求上百上千次
 * 影响服务器带宽，直接限制一秒钟只能请求两次/一分钟100次 这样爬虫文件就可以被拦截住
 * 配合 登录权限一起使用
 */
@Documented
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface AccessLimit {
    // 在 second 秒内，最大只能请求 maxCount 次
    int second() default 1;
    int maxCount() default 1;
}

/**
 * 请求限制的自定义注解
 *
 * @Target 注解可修饰的对象范围，ElementType.METHOD 作用于方法，ElementType.TYPE 作用于类
 * (ElementType)取值有：
 * 　　　　1.CONSTRUCTOR:用于描述构造器
 * 　　　　2.FIELD:用于描述域
 * 　　　　3.LOCAL_VARIABLE:用于描述局部变量
 * 　　　　4.METHOD:用于描述方法
 * 　　　　5.PACKAGE:用于描述包
 * 　　　　6.PARAMETER:用于描述参数
 * 　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 * @Retention定义了该Annotation被保留的时间长短：某些Annotation仅出现在源代码中，而被编译器丢弃；
 * 而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，
 * 而另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。
 * 使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
 * （RetentionPoicy）取值有：
 * 　　　　1.SOURCE:在源文件中有效（即源文件保留）
 * 　　　　2.CLASS:在class文件中有效（即class保留）
 * 　　　　3.RUNTIME:在运行时有效（即运行时保留）
 *
 * @Inherited
 * 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
 * 如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 */
