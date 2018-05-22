package com.yudong.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yudong.common.Constants;
import com.yudong.entity.Users;
import com.yudong.service.UserService;
import com.yudong.utils.JavaMD5Util;
import com.yudong.utils.SMSHttpManager;

/**
 * 用户后台管理逻辑类
 */
@Controller
public class UserController {

	
	@Autowired
	private UserService userService;
	
	/**
	 * json数据交互
	 * GET测试
	 */
	@RequestMapping(value = "/jsonGet", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Users jsonGet() {
		Users user = userService.findUserById(1);
		return user;
	}
	
	/**
	 * json数据交互
	 * POST测试
	 */
	@RequestMapping(value = "/jsonPost", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String jsonPost(HttpServletRequest request) {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println("username: "+user+"\n password: "+pass);
		return "success";
	}
	
	
	/**
	 * 客户端登录控制
	 */
	@RequestMapping(value = "/clientLoginController", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int clientLoginController(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = userService.findUserByUserName(userName);
		if(user == null){
			return 3;//账号不存在
		}else {
			if (user.getPassword().equals(JavaMD5Util.encode(password, user.getSalt()))){
				return 1;//匹配成功
			}else {
				return 4;//密码错误
			}
		}
	}
	
	/**
	 * 客户端注册控制
	 */
	@RequestMapping(value = "/clientRegisterController", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int clientRegisterController(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = userService.findUserByUserName(userName);
		if(user == null){//账号不存在,可以注册
			user = new Users();
			user.setUserName(userName);
			user.setSalt(JavaMD5Util.generatorSalt());//生成盐值
			user.setPassword(JavaMD5Util.encode(password, user.getSalt()));//密码加密加盐
			user.setRole(2);//1：超级管理员，2：普通用户
			user.setHeadImage("default.jpg");
			user.setRegisteTime(new Date());
			user.setUserState(1);
			userService.addUser(user);//保存用户注册信息
			return 1;//返回注册成功标志
		}else {
			return 3;//账号已存在，无法注册
		}
	}
	
	
	/**
	 * 客户端忘记密码控制
	 */
	@RequestMapping(value = "/clientForgetController", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int clientForgetController(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = userService.findUserByUserName(userName);
		if(user != null){//账号存在
			user.setPassword(JavaMD5Util.encode(password, user.getSalt()));//更换新密码
			userService.updatePassword(user);//保存到数据库
			return 1;//返回更改成功标志
		}else {
			return 3;//账号不存在
		}
	}
	
	/**
	 * 用户登录 
	 * @param 
	 * @return 跳转到登录页面
	 */
	@RequestMapping(value = "/webLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToUserLogin() {
		return "login";
	}
	
	/**
	 * 超级管理员登录
	 * @param 
	 * @return 跳转到登录页面
	 */
	@RequestMapping(value = "/adminLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToAdminLogin() {
		return "admin/admin_login";
	}
	
	/**
	 * 后台用户管理
	 * @param 
	 * @return 跳转到用户管理页面
	 */
	@RequestMapping(value = "/adminUserManager", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToAdminUserManager() {
		//获取所有用户
		
		
		
		return "admin/admin_usermanage";
	}
	
	
	/**
	 * 后台图书管理
	 * @param 
	 * @return 跳转到图书管理页面
	 */
	@RequestMapping(value = "/adminBookManager", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToAdminBookManager() {
		//获取所有图书
		
		
		
		return "admin/admin_bookmanage";
	}

	/**
	 * 用户详细信息页面
	 * @param 
	 * @return 跳转到个人信息页面
	 */
	@RequestMapping(value = "/userDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToUserDetail() {
		
		
		
		
		return "admin/user_detail";
	}
	
	/**
	 * 个人信息页面
	 * @param 
	 * @return 跳转到个人信息页面
	 */
	@RequestMapping(value = "/myProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToMyProfile() {
		
		
		
		
		
		return "my_info";
	}
	

	
	
	/**
	 * 网页端登录控制
	 *  @param session 保存登录数据 
	 *  @param user 保存登录名密码
	 *  @param model 保存错误结果信息
	 *  @return 跳转到主页面
	 * */
	@RequestMapping(value = "/webLoginController", method = { RequestMethod.POST })
	public String webLoginController(HttpSession session, Users user, Model model) {
		int role = user.getRole();//是管理员登陆还是普通用户登录，1：超管，2：普通用户
		Users curUser = userService.findUserByUserNameAndPsw(user);
		if(curUser!=null){
			session.setAttribute("cur_user",curUser);//保存用户到session中，
			if(role == 1){
				return "redirect:/adminUserManager";
			}else{
				return "redirect:/myBook";
			}
		}else{
			if(role == 1){
				model.addAttribute("login_result", "用户名密码错误");
				return "admin/admin_login";
			}else{
				model.addAttribute("login_result", "用户名密码错误");
				return "login";
			}
		}
	
	}
	
	/**
	 * 网页端退出控制
	 *  @param session 保存登录数据 
	 *  @return 跳转到登录
	 * */
	@RequestMapping(value = "/webLogout", method = {RequestMethod.GET, RequestMethod.POST })
	public String webLogoutController(HttpSession session,String type,Model model) {
		session.removeAttribute("cur_user");
		session.removeAttribute("cur_user_books");
		if(type.equals("1")){
			return "admin_login";
		}else{
			return "login";
		}
	}
	
	/**
	 * 用户注册 用户从首页点击注册，跳转到注册页面
	 * @param 
	 * @return 跳转到注册页面
	 */
	@RequestMapping(value = "/webRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToUserRegister() {
		return "register";
	}
	
	/**
	 * 网页端注册控制  
	 *  @param session 保存注册数据 
	 *  @param user 保存注册名密码
	 *  @param model 保存错误结果信息
	 *  @return 跳转到主页面
	 * */
	@RequestMapping(value = "/webRegisterController", method = { RequestMethod.POST })
	public String webRegisterController(HttpSession session, Users user,String verCode, Model model) {
		System.out.println("register userName "+user.getUserName());
		System.out.println("register password "+user.getPassword());
		System.out.println("register verCode "+verCode);
		
		Users curUser = userService.findUserByUserName(user.getUserName());
		if(curUser!=null){//用户存在不能注册
			model.addAttribute("register_result", "用户已存在");
			System.out.println("register error");
			return "register";
		}else{
			if(verCode.equals(session.getAttribute(Constants.VER_CODE))){
				curUser = new Users();
				
				curUser.setUserNickName(user.getPassword());//记住明文密码以防忘记,待删除
				
				curUser.setUserName(user.getUserName());
				curUser.setSalt(JavaMD5Util.generatorSalt());//生成盐值
				curUser.setPassword(JavaMD5Util.encode(user.getPassword(), curUser.getSalt()));//密码加密加盐
				curUser.setRole(2);//普通用户注册
				curUser.setHeadImage("default.jpg");
				curUser.setRegisteTime(new Date());
				curUser.setUserState(1);
				userService.addUser(curUser);//保存用户注册信息
				
				model.addAttribute("login_result", "注册成功，请重新登录");
				return "login";
			}else{
				model.addAttribute("register_result", "注册失败");
				System.out.println("register error");
				return "register";
			}
		}
	
	}
	
	
	
	/**
	 * 网页端发送验证码控制
	 *  @return 跳转到主页面
	 * */
	@PostMapping("sendVerCodeController")
    @ResponseBody
	public String sendVerCodeController(HttpSession session,String userName) {
		session.removeAttribute(Constants.VER_CODE);//把旧的验证码清除
		
		String verCode = String.valueOf(SMSHttpManager.smsContentGenernator());//生成六位数字验证码
		
		String result = SMSHttpManager.postSMS(userName, verCode);//发送验证码
        JSONObject jsonObject = JSON.parseObject(result);

        System.out.println("验证码请求码："+jsonObject.getString("respCode"));
        
        //请求成功
        if (jsonObject.getString("respCode").equals("00000")) {
        	session.setAttribute(Constants.VER_CODE, verCode);//保存验证码到session中
        	System.out.println("保存验证码到session中");
        	//TimerTask实现5分钟后从session中删除VER_CODE
        	final Timer timer=new Timer();
        	timer.schedule(new TimerTask() {
        	    @Override
        	    public void run() {
        	    	session.removeAttribute(Constants.VER_CODE);
        	        System.out.println("VER_CODE删除成功");
        	        timer.cancel();
        	    }
        	},5*60*1000);
        	return "success";
        }
		return "error";
	}
	
}










