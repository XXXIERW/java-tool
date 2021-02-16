package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springMvc.JsonResult2;
import springMvc.annotation.ResponseResultBody;

import java.util.HashMap;
import java.util.Map;

@ResponseResultBody
@RestController
@RequestMapping("/helloResult")
public class ResultController {
    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<String, Object>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("hello")
    public HashMap<String, Object> hello() {
        return INFO;
    }

    /* 测试重复包裹 */
    @GetMapping("result")
    public JsonResult2<Map<String, Object>> helloResult() {
        return JsonResult2.success(INFO);
    }

    @GetMapping("helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
//        throw new ResultException();
        return null;
    }
}
