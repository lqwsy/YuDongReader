package com.yudong.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yudong.common.Constants;
import com.yudong.entity.Books;
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
	public Users clientLoginController(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = userService.findUserByUserName(userName);
		if(user == null){
			user = new Users();
			user.setUserState(3);
			System.out.println("账号不存在");
			return user;//账号不存在
		}else {
			if (user.getPassword().equals(JavaMD5Util.encode(password, user.getSalt()))){
				System.out.println("匹配成功");
				return user;//匹配成功
			}else {
				user.setUserState(4);
				System.out.println("密码错误");
				return user;//密码错误
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
	 * 客户端修改密码控制
	 */
	@RequestMapping(value = "/clientUpdatePasswordController", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int clientUpdatePasswordController(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		Users user = userService.findUserById(userId);
		if(user != null){//账号存在
			password  = JavaMD5Util.encode(password, user.getSalt());
			newPassword = JavaMD5Util.encode(newPassword, user.getSalt());
			if(password.equals(user.getPassword())){
				user.setPassword(newPassword);//更换新密码
				userService.updatePassword(user);//保存到数据库
				return 1;//返回更改成功标志
			}else{
				return 2;
			}
		}else {
			return 3;//账号不存在
		}
	}
	
	/**
	 * 客户端修改资料控制
	 */
	@RequestMapping(value = "/clientUpdateUserInfoController", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int clientUpdateUserInfoController(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String nickName = request.getParameter("nickName");
		
		System.out.println("UpdateUserInfo userid is === "+userId);
		System.out.println("UpdateUserInfo nickName is === "+nickName);
		
		Users user = userService.findUserById(userId);
		if(user != null){//账号存在
			user.setUserNickName(nickName);
			userService.updateUserInfo(user);
			return 1;//返回更改成功标志
		}else {
			return 2;//返回更改失败
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
	public ModelAndView goToAdminUserManager(HttpSession session,int role,int pageNum,Model model) {
		//获取所有用户
		ModelAndView mav = new ModelAndView("admin/admin_usermanage");
		PageHelper.startPage(pageNum, 10);
		if(role==1||role==2){
			List<Users> admin_all_users = userService.getUserByRold(role);
			PageInfo<Users> page=new PageInfo<Users>(admin_all_users);
			mav.addObject("page", page);
			mav.addObject("role", role);
		}else if(role==3){
			List<Users> admin_all_users = userService.getAllUsers();
			PageInfo<Users> page=new PageInfo<Users>(admin_all_users);
			mav.addObject("page", page);
			mav.addObject("role", role);
		}
		return mav;
	}

	/**
	 * 用户详细信息页面
	 * @param 
	 * @return 跳转到用户详细信息页面
	 */
	@RequestMapping(value = "/userDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goToUserDetail(HttpSession session,int userId) {
		Users countUser = userService.findUserById(userId);
		ModelAndView mav = new ModelAndView("admin/user_detail");
		if(countUser!=null){
			mav.addObject("countUser", countUser);
		}
		return mav;
	}
	
	
	/**
	 * 超级管理员更新用户状态
	 * @param 
	 * @return 跳转到用户详细信息页面
s	 */
	@RequestMapping(value = "/adminChangeUserInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminChangeUserInfoController(HttpSession session,int userState,int userId) {
		Users countUser = userService.findUserById(userId);
		ModelAndView mav = new ModelAndView("admin/user_detail");
		if(countUser!=null && countUser.getUserState()!=userState){
			countUser.setUserState(userState);
			if(userService.updateUserInfo(countUser)){
				mav.addObject("countUser", countUser);
			}
			return mav;
		}
		return mav;
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
	 * 更新个人信息
	 * @param 
	 * @return 跳转到个人信息页面
	 */
	@RequestMapping(value = "/updateMyProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateMyProfileController(HttpSession session, Users user) {
		Users cur_user = (Users) session.getAttribute("cur_user");
		
		System.out.println("nickName is ==="+user.getUserNickName());
		
		cur_user.setUserNickName(user.getUserNickName());
		if(userService.updateUserInfo(cur_user)){
			session.removeAttribute("cur_user");
			session.setAttribute("cur_user", cur_user);
		}
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
			if(session.getAttribute("cur_user")!=null){
				session.removeAttribute("cur_user");
				session.setAttribute("cur_user",curUser);//保存用户到session中，
			}else{
				session.setAttribute("cur_user",curUser);//保存用户到session中，
			}
			if(role == 1 && curUser.getRole()==1){
				return "redirect:/adminUserManager?role=3&pageNum=1";
			}else{
				return "redirect:/myBook?pageNum=1";
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
	public String webLogoutController(HttpSession session,int type,Model model) {
		session.removeAttribute("cur_user");
		session.removeAttribute("cur_user_books");
		session.removeAttribute("my_book_info");
		session.removeAttribute("cur_user_delete_books");
		session.removeAttribute("countUser");
		if(type==1){
			return "admin/admin_login";
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
				
//				curUser.setUserNickName(user.getPassword());//记住明文密码以防忘记,待删除
				curUser.setUserNickName(user.getUserName());//初始昵称为用户名
				
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
	
	/**
	 * 网页端用户头像上传
	 * @param 
	 * @return 跳转到登录页面
	 */
	@PostMapping("uploadCurUserHeadImg")
    @ResponseBody
	public String personalUploadControll(HttpSession session,HttpServletRequest request,String img,Model model) {
		
		String serverPath = request.getSession().getServletContext().getRealPath("/");//获取项目运行路径  
		Base64 base64 = new Base64();
        Users cur_user = (Users) session.getAttribute("cur_user");
        
        try {  
            //实际的图片数据是从 data:image/jpeg;base64, 后开始的  
            byte[] k = base64.decode(img.substring("data:image/jpeg;base64,".length()));  
            InputStream is = new ByteArrayInputStream(k); 
            String fileName = "";
			if(cur_user.getHeadImage().equals("default.jpg")){
				fileName = "static/img/" + cur_user.getUserName()+".jpg";//覆盖默认的图片
				cur_user.setHeadImage(cur_user.getUserName()+".jpg");
				userService.updateUserInfo(cur_user);//更新数据库头像名称
				session.removeAttribute("cur_user");
				session.setAttribute("cur_user", cur_user);
			}else{
				fileName = "static/img/" + cur_user.getHeadImage();//覆盖原来的图片
			}
			
			System.out.println("fileName is == "+fileName);
			
            File file = new File(serverPath + fileName);
    		if(file.exists()){
    			file.delete();
    		}
    		String imgFilePath = serverPath  + fileName;//图片绝对路径  
            BufferedImage image = ImageIO.read(is);   
            ImageIO.write(image, "jpg", new File(imgFilePath));//保存图片到本地
//            return "redirect:/myProfile";  
            return "my_info";  
        } catch (Exception e) {  
            e.printStackTrace();
            return "error";  
        }
	}
	
}










