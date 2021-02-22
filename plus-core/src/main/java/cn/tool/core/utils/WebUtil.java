package cn.tool.core.utils;

import springMvc.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 关于web的处理
 */
public class WebUtil {
	
	public static void sendJson(HttpServletResponse resp, JsonResult result) throws IOException{
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		String str = result == null ? "" : result.toJsonString();
		PrintWriter out = resp.getWriter();
        out.println(str);
        out.flush();
        out.close();
	}
	
	public static void sendJson(HttpServletResponse resp,String json) throws IOException{
        //以下代码从JSON.java中拷过来的
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
	}
	
	public static String getBasePath(HttpServletRequest paramHttpServletRequest)
	{
	    StringBuilder localStringBuffer = new StringBuilder();
	    localStringBuffer.append(paramHttpServletRequest.getScheme());
	    localStringBuffer.append("://");
	    localStringBuffer.append(paramHttpServletRequest.getServerName());
	    localStringBuffer.append(":");
	    localStringBuffer.append(paramHttpServletRequest.getServerPort());
	    localStringBuffer.append(paramHttpServletRequest.getContextPath());
	    return localStringBuffer.toString();
	}


}
