package springMvc;

import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 *     {
 *     "code": 200,
 *     "message": "OK",
 *     "data": {
 *
 *     }
 *    }
 *      一般返回给前端的格式 code:状态码，message:返回描述：success,failed data:返回数据体
 * </p>
 * @author RaveyXie
 * 进行数据的统一处理和返回
 */
public class JsonResult2<T> {

    private Integer code;
    /* 信息描述 */
    private String message;
    /* 返回参数*/
    private T data;

    private JsonResult2(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    /**
     *  业务成功返回业务代码和描述信息
     **/
    public static JsonResult2<Void> success() {
        return new JsonResult2<>(ResultStatus.SUCCESS, null);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> JsonResult2<T> success(T data) {
        return new JsonResult2<>(ResultStatus.SUCCESS, data);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> JsonResult2<T> success(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return success(data);
        }
        return new JsonResult2<>(resultStatus, data);
    }

    /** 业务异常返回业务代码和描述信息
     * @param resultStatus*/
    public static <T> JsonResult2<T> failure(springMvc.exception.ResultStatus resultStatus) {
        return new JsonResult2<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> JsonResult2<T> failure(ResultStatus resultStatus) {
        return new JsonResult2(resultStatus, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数*/
    public static <T> JsonResult2<T> failure(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return new JsonResult2<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
        }
        return new JsonResult2<>(resultStatus, data);
    }

    /** 业务异常返回业务代码和描述信息 */
    public static <T> JsonResult2<T> failure() {
        return new JsonResult2<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
    }

    /**
     * 定义返回枚举信息
     */
    @Getter
    @ToString
    public enum ResultStatus{
        SUCCESS( 200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        INTERNAL_SERVER_ERROR( 500, "Internal Server Error")
        ;

        private Integer code;
        private String message;

        ResultStatus(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
