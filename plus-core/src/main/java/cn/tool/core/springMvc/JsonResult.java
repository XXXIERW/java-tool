package cn.tool.core.springMvc;

import cn.tool.core.common.ICodeStatus;
import cn.tool.core.utils.JsonUtils;

import java.io.Serializable;

/**
 * json转化工具类
 * @author:laikunzhen
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 2096892482295250355L;

    /**
     * {@code 0 OK}
     */
    public final static String CODE_NORMAL = "0";

    /**
     * {@code -1 ERROR}
     */
    public final static String CODE_ERROR = "-1";

    /**
     * {@code 1 REDIRECT}
     * 重定向（ajax请求重定向）
     */
    public final static String CODE_REDIRECT = "1";

    private boolean success = true;
    private String code = CODE_NORMAL;
    private String msg = "";
    private Object data = "";

    private Object extra = "";//兼容php接口新增,最好不要使用

    public static JsonResult success(Object data) {
        JsonResult result = new JsonResult();
        result.setData(data);
        return result;
    }

    public static JsonResult success() {
        JsonResult result = new JsonResult();
        return result;
    }

    public static JsonResult success(String description){
        JsonResult result = new JsonResult();
        result.setMsg(description);
        return result;
    }

    public static JsonResult failed(String message) {
        JsonResult result = new JsonResult();
        result.setCode(CODE_ERROR);
        result.setSuccess(false);
        result.setMsg(message);
        return result;
    }

    public static JsonResult failedCode(ICodeStatus codeStatus) {
        JsonResult result = new JsonResult();
        result.setMsg(codeStatus.getReasonPhrase());
        result.setCode(codeStatus.value());
        result.setSuccess(false);
        return result;
    }

    /**
     * 返回json，重定向（只用于shiro校验session过期，ajax请求重定向）
     * @param json
     * @return
     */
    public static JsonResult redirect(String json) {
        JsonResult result = new JsonResult();
        result.setCode(CODE_REDIRECT);
        result.setData(json);
        result.setSuccess(true);
        return result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toJsonString() {
        return JsonUtils.objectToString(this);
    }

}

