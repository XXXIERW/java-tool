package springMvc.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * <p>
 *      想要返回统一的 JSON 格式需要返回Result<Object>才可以, 我明明返回 Object 可以了,
 *      目的是为了配合ResultUtil2进行使用 返回类型随意但是返回回去的数据我们需要统一化JSON
 *
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {
}
