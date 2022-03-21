package com.example.demo;
import java.io.PrintWriter;

/**
 * 登录拦截器
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Json;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ObjectMapper mapper;//Jackson使用ObjectMapper类将POJO对象序列化成JSON字符串，也能将JSON字符串反序列化成POJO对象。

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {       //请求进入这个拦截器
		logger.info("进入拦截器...");
		HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){//判断session中有没有user信息
        	logger.info("session中没有user信息");
        	//ajax异步请求
            if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
            	logger.info("ajax异步请求");
            	Json j = new Json();
            	j.setSuccess(false);
            	j.setMsg("session会话超时，请重新登录！");
            	response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.write(mapper.writeValueAsString(j));//将对象转成字符串
				logger.info(mapper.writeValueAsString(j));
				writer.close();
				response.flushBuffer();
				return false;
            }else {
            	logger.info("页面请求");
            	response.sendRedirect("/login");//没有user信息的话进行路由重定向到登录页面
                return false;
            }
        }
        logger.info("登录拦截验证通过。");
        return true;//有的话就继续操作
    }
}
