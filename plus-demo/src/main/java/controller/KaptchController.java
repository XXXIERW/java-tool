package controller;

import cn.tool.core.springMvc.JsonResult;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class KaptchController {

    @Autowired
    private Producer kaptchaProducer;

    /**
     * 给前端下发验证码；
     * @param request
     * @return
     */
//    @ApiImplicitParam(name = "request",value = "浏览器返回",required = true) // 进行参数的赋值
    @ApiOperation(value = "验证码获取")
    @GetMapping("/kaptcha")
    public Object kaptcha(HttpServletRequest request) {
        String kaptcha = doKaptcha(request);
        if (kaptcha != null) {
            return JsonResult.success(kaptcha);
        }
        return JsonResult.failed("生成验证码失败");
    }

    /**
     * 生成验证码；
     * @param request
     * @return
     */
    private String doKaptcha(HttpServletRequest request) {
        // 生成验证码
        String text = kaptchaProducer.createText(); // 创建验证码
        BufferedImage image = kaptchaProducer.createImage(text);
        // 将验证码的信息存在session中进行比对
        HttpSession session = request.getSession();
        session.setAttribute("kaptcha", text); // 存在session中 然后通过session进行返回 信息域

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(outputStream.toByteArray());
            String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
            return captchaBase64;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 进行验证码比对
     * @param request
     * @param code
     * @return
     */
    public JsonResult kaptchaTest(HttpServletRequest request,String code){
        HttpSession session = request.getSession(); // 获取session中的验证码进行比对
        String kaptcha = (String)session.getAttribute("kaptcha");
        if (Objects.requireNonNull(code).compareToIgnoreCase(kaptcha) != 0) { // 进行数据的比对 忽略大小写 只需要数据对上即可
            return JsonResult.failed( "验证码不正确");
        }
        return JsonResult.success();
    }
}
