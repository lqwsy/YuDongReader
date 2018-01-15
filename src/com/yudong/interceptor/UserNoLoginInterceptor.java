package com.yudong.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yudong.common.Constants;

public class UserNoLoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//获取请求的URL  
        String url = request.getRequestURI();  
        //登录请求不拦截
        if(url.indexOf("login")>=0){  
            return true;  
        }  
        HttpSession session = request.getSession();  
        String admin = (String)session.getAttribute(Constants.USER_KEY);  
        
        if(admin != null){  
            return true;  
        }
        //不符合条件的，跳转到登录界面  
        request.getRequestDispatcher("/login").forward(request, response);  
          
        return false; 
	}

}
