//package cn.tool.core.aop.aspect;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.github.pagehelper.PageRowBounds;
//import com.github.walker.mybatis.paginator.PageBounds;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.util.List;
//
///**
// * <p>
// *  进行分页处理，比如前端限制一页多少条数据 但是使用的是下一页
// *  如果用原始的导出会出现 显示很多页但是 数据没了的情况，这个就是为了避免这种情况
// *  https://github.com/HuQingmiao/mybatis-paginator
// * </p>
// *
// * @since 2020-10-13
// */
//@Aspect
//@Component
//public class PaginationAspect {
//
//    @Value("${page.count.enable:true}") // 这个要走一个动态配置 所以需要健全的系统进行配置
//    private boolean count = false;
//
//    @Around(value = "@annotation(cn.tool.core.aop.aspect.Pagination)")
//    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
//        // 目标类
//        Object target = pjp.getTarget();
//        // 方法签名
//        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
//        // 参数值
//        Object[] args = pjp.getArgs();
//        // 当前方法
//        Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
//        // 方法参数
//        Parameter[] parameters = method.getParameters();
//        Integer offset = null;
//        Integer limit = null;
//        for (int i = 0; i < parameters.length; i++) {
//            if (PageBounds.class.equals(parameters[i].getType()) || PageRowBounds.class.equals(parameters[i].getType())) {
//                if (PageBounds.class.equals(parameters[i].getType())) {
//                    PageBounds pageBounds = (PageBounds) args[i];
//                    offset = pageBounds.getOffset();
//                    limit = pageBounds.getLimit();
//                } else {
//                    PageRowBounds pageRowBounds = (PageRowBounds) args[i];
//                    offset = pageRowBounds.getOffset();
//                    limit = pageRowBounds.getLimit();
//                }
//                break;
//            } else if ("offset".equals(parameters[i].getName()) && (Integer.class.equals(parameters[i].getType()) || int.class.equals(parameters[i].getType()))) {
//                offset = (Integer) args[i];
//            } else if ("limit".equals(parameters[i].getName()) && (Integer.class.equals(parameters[i].getType()) || int.class.equals(parameters[i].getType()))) {
//                limit = (Integer) args[i];
//            }
//        }
//
//        //不带分页参数，直接返回
//        if (offset == null || limit == null) {
//            return pjp.proceed();
//        }
//
//        //带分页参数且需要计算总条数，进行分页后返回
//        if (count) {
//            PageHelper.offsetPage(offset, limit);
//            return pjp.proceed();
//        }
//
//        //带分页参数且不需要计算总条数，进行分页后，需要处理格式
//        PageHelper.offsetPage(offset, limit + 1, false);
//        Object data = pjp.proceed();
//        if (!PageInfo.class.equals(data.getClass())) {
//            return data;
//        }
//        PageInfo pageInfo = (PageInfo) data;
//        List list = pageInfo.getList();
//        //处理返回数据
//        if (list.size() > limit) {
//            pageInfo.setList(list.subList(0, limit));
//        }
//        //没有计算总条数的时候，如果业务方法里面没有设置total，那么total会是-1，要进行设置
//        if (pageInfo.getTotal() == -1) {
//            pageInfo.setTotal(list.size());
//        }
//        return pageInfo;
//    }
//}
