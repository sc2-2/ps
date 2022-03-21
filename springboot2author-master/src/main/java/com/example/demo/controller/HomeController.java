package com.example.demo.controller;
/**
 * 后台管理主页，登录页面，验证码生成
 */
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.User;
import com.example.demo.service.PermissionService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired  
    private Producer captchaProducer;//验证码
	
	@Autowired
	private PermissionService permissionService;// 注入业务层的service
	
	// 未加入@ResponseBody用来返回数据给页面
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		logger.info(user.getUsername());
		logger.info(user.getId());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("userid", user.getId());
		model.addAttribute("menulist", permissionService.getUserMenu(user.getId()));
		return "admin/index";
	}

	// 未加入@ResponseBody用来返回数据给页面
	@RequestMapping("/home")
	public String home(Model model) {
		return "admin/home";
	}
	
	// 未加入@ResponseBody用来返回数据给页面
	@RequestMapping("/")
	public String login(Model model) {
		return "admin/login";
	}
	
	// 未加入@ResponseBody用来返回数据给页面
	@RequestMapping("/login")
	public String login2(Model model) {
		return "admin/login";
	}
	
	@RequestMapping("/kaptcha")  
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {    
        HttpSession session = request.getSession();    
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);    
        logger.info("******************验证码是: " + code + "******************");    
        response.setDateHeader("Expires", 0);    
        // Set standard HTTP/1.1 no-cache headers.    
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");    
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).    
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");    
        // Set standard HTTP/1.0 no-cache header.    
        response.setHeader("Pragma", "no-cache");    
        // return a jpeg    
        response.setContentType("image/jpeg");    
        // create the text for the image    
        String capText = captchaProducer.createText();    
        // store the text in the session    
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);    
        // create the image with the text    
        BufferedImage bi = captchaProducer.createImage(capText);    
        ServletOutputStream out = response.getOutputStream();    
        // write the data out    
        ImageIO.write(bi, "jpg", out);    
        try {    
            out.flush();    
        } finally {    
            out.close();    
        }    
        return null;    
    }

}
