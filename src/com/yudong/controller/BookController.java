package com.yudong.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yudong.entity.Books;
import com.yudong.entity.Users;
import com.yudong.service.BookService;
import com.yudong.utils.FileUtils;
//import com.yudong.utils.FileUtils;
import com.yudong.utils.IPUtils;

/**
 * 图书管理
 */
@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	private Map<String,Integer> classificationMap = new HashMap<>();
	
	/**
	 * 我的图书
	 * @param 
	 * @return 跳转到我的图书页面
	 */
	@RequestMapping(value = "/myBook", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goToMyBook(HttpSession session,int pageNum) {
		ModelAndView mav = new ModelAndView("book_my");
		PageHelper.startPage(pageNum, 10);
		Users cur_user = (Users) session.getAttribute("cur_user");
		if(cur_user!=null){
			List<Books> mybooks = bookService.getMyBooks(cur_user.getUserName());
			PageInfo<Books> page=new PageInfo<Books>(mybooks);
			mav.addObject("page", page);
			return mav;
		}
		return mav;
	}
	
	/**
	 * 后台图书管理
	 * 获取所有图书
	 * @param 
	 * @return 跳转到图书管理页面
	 */
	@RequestMapping(value = "/adminBookManager", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goToAdminBookManager(HttpSession session,int pageNum) {
		ModelAndView mav = new ModelAndView("admin/admin_bookmanage");
		PageHelper.startPage(pageNum, 10);
		List<Books> admin_all_books = bookService.getAllBooks();
		PageInfo<Books> page=new PageInfo<Books>(admin_all_books);
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 后台图书管理
	 * 获取指定状态图书
	 * @param 
	 * @return 跳转到图书管理页面
	 */
	@RequestMapping(value = "/adminGetStateBook", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminGetStateBookContrller(HttpSession session,Model model,int bookState) {
		if(bookState != 0){
			List<Books> stateBooks = bookService.getStateBooks(bookState);
			session.setAttribute("admin_all_books", stateBooks);
		}
		return "admin/admin_bookmanage";
	}
	
	/**
	 * 后台图书管理
	 * 获取指定类型图书
	 * @param 
	 * @return 跳转到图书管理页面
	 */
	@RequestMapping(value = "/adminGetClassifyBook", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminGetClassifyBookController(HttpSession session,Model model,int bookClassificationId) {
		if(bookClassificationId != 0){
			List<Books> classificationBooks = bookService.getClassificationBooks(bookClassificationId);
			session.setAttribute("book_bookClassify_selected", bookClassificationId);
			session.setAttribute("admin_all_books", classificationBooks);
		}
		return "admin/admin_bookmanage";
	}
	
	/**
	 * 后台图书管理
	 * 根据图书名作者名搜索图书
	 * @param 
	 * @return 跳转到图书管理页面
	 */
	@RequestMapping(value = "/adminSearchBook", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminSearchBookController(HttpSession session,Model model,String searchName) {
		ModelAndView mav = new ModelAndView("admin/admin_bookmanage");
		if(!searchName.equals("")){
			if(searchName.equals("all")){
				mav.setViewName("redirect:/adminBookManager?pageNum=1");
				return mav;
			}else{
				PageHelper.startPage(1, 10);
				List<Books> searchBooks = bookService.searchBooks(searchName);
				PageInfo<Books> page=new PageInfo<Books>(searchBooks);
				mav.addObject("page", page);
			}
		}
		return mav;
	}
	
	/**
	 * 后台图书管理
	 * 图书详细信息页面
	 * @param 
	 * @return 跳转到图书信息页面
	 */
	@RequestMapping(value = "/bookDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goToUserDetail(HttpSession session,int bookId) {
		Books countBook = bookService.findBookById(bookId);
		ModelAndView mav = new ModelAndView("admin/book_detail");
		if(countBook!=null){
			mav.addObject("countBook", countBook);
		}
		return mav;
	}
	
	/**
	 * 后台图书管理
	 * 审核图书
	 * @param 
	 * @return 跳转到图书信息页面
	 */
	@RequestMapping(value = "/checkBook", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView checkBookController(HttpSession session,int bookState,int bookId) {
		Books checkBook = bookService.findBookById(bookId);
		ModelAndView mav = new ModelAndView("admin/book_detail");
		if(checkBook.getBookState()!=bookState){
			checkBook.setBookState(bookState);
			if(bookService.updateBookState(checkBook)){
				mav.addObject("countBook", checkBook);
			}
		}
		return mav;
	}
	
	/**
	 * 我的图书信息页面
	 * @param 
	 * @return 跳转到图书信息页面
	 */
	@RequestMapping(value = "/myBookInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToMyBookInfo(HttpSession session,Model model,int bookId) {
		if(bookId!=0){
			Books book = bookService.findBookById(bookId);
			session.setAttribute("my_book_info", book);
			return "book_info";
		}
		return "book_my";
	}
	
	/**
	 * 删除图书页面，状态设置为已删除
	 * @param 
	 * @return 跳转到图书信息页面
	 */
	@RequestMapping(value = "/deleteBook", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteBookController(HttpSession session,int bookId) {
		if(bookId!=0){
			Books delete_book = bookService.findBookById(bookId);
			delete_book.setBookState(3);//3：图书为删除状态
			bookService.updateBookState(delete_book);
			return "redirect:/myBook?pageNum=1";
		}
		return "redirect:/myBook?pageNum=1";
	}
	
	/**
	 * 获取已删除图书页面
	 * @param 
	 * @return 跳转到已删除图书信息页面
	 */
	@RequestMapping(value = "/myDeleteBooks", method = { RequestMethod.GET, RequestMethod.POST })
	public String getDeleteBook(HttpSession session) {
		Users cur_user = (Users) session.getAttribute("cur_user");
		List<Books> delete_books = bookService.getMyDeleteBooks(cur_user.getUserName());
		if(delete_books!=null && delete_books.size()!=0){
			session.setAttribute("cur_user_delete_books", delete_books);
			return "book_my_delete";
		}
		return "book_my_delete";
	}
	
	/**
	 * 恢复已删除图书页面
	 * @param 
	 * @return 跳转到已删除图书信息页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/recoveryDeleteBook", method = { RequestMethod.GET, RequestMethod.POST })
	public String recoveryDeleteBook(HttpSession session,int index) {
		List<Books> delete_books = (List<Books>) session.getAttribute("cur_user_delete_books");
		if(delete_books!=null && delete_books.size()!=0){
			Books recoveryBook = delete_books.get(index-1);
			recoveryBook.setBookState(1);//重新恢复为未审核状态
			session.setAttribute("cur_user_delete_books", delete_books);
			bookService.updateBookState(recoveryBook);
			delete_books.remove(index-1);
			session.setAttribute("cur_user_delete_books", delete_books);
			return "book_my_delete";
		}
		return "book_my_delete";
	}
	
	/**
	 * 网页端图片封面上传
	 * @param 
	 * @return 跳转到登录页面
	 */
	@PostMapping("uploadCoverPath")
    @ResponseBody
	public String personalUploadControll(HttpSession session,HttpServletRequest request,String img,Model model) {
		
		String serverPath = request.getSession().getServletContext().getRealPath("/");//获取项目运行路径  
        Base64 base64 = new Base64();
        Books book = (Books) session.getAttribute("my_book_info");
        if(book!=null){
        	try {  
                //实际的图片数据是从 data:image/jpeg;base64, 后开始的  
                byte[] k = base64.decode(img.substring("data:image/jpeg;base64,".length()));  
                InputStream is = new ByteArrayInputStream(k);  
    			String fileName = "static/bookimg/" + book.getBookCoverPath();//覆盖原来的图片
    			
    			String imgFilePath = serverPath  + fileName;//图片绝对路径  
                File file = new File(imgFilePath);
        		if(file.exists()){
        			file.delete();
        		}
        		BufferedImage image = ImageIO.read(is);   
                ImageIO.write(image, "jpg", new File(imgFilePath));//保存图片到本地
                return fileName;  
            } catch (Exception e) {  
                e.printStackTrace();
                return "error";  
            }
        }
        return "book_info";
	}
	
	/**
	 * 更新图书信息
	 * @param 
	 * @return 图书信息页面
	 */
	@RequestMapping(value = "/updateMyBookInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView updateMyBookInfoController(HttpSession session,MultipartFile bookFile,Books book) {
		String path = session.getServletContext().getRealPath("static/books/");
		Books cur_book = (Books) session.getAttribute("my_book_info");
		ModelAndView mav = new ModelAndView("book_info");
		if(cur_book != null){
			try {
				if(bookFile!=null){
					File dir = new File(path, cur_book.getBookLocation());
					if (!dir.exists()) {
						dir.mkdirs();
					}
					bookFile.transferTo(dir);
				}
				
				System.out.println(book.getBookName());
				System.out.println(book.getBookAuthor());
				System.out.println(book.getBookIntroduction());
				
				cur_book.setBookName(book.getBookName());
				cur_book.setBookAuthor(book.getBookAuthor());
				cur_book.setBookIntroduction(book.getBookIntroduction());
				if(bookService.updateBookState(cur_book)){//保存到数据库
					session.setAttribute("my_book_info", cur_book);
					mav.addObject("updateResult", 1);
				}
				return mav;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mav;
		}
		return mav;
	}
	
	
	/**
	 * 上传图书页面
	 * @param 
	 * @return 跳转到上传图书页面
	 */
	@RequestMapping(value = "/uploadBook", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToUploadBook() {
		return "book_upload";
	}

	/**
	 * 上传图书
	 */
	@RequestMapping(value = "/bookUploadController", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ModelAndView bookUploadController(HttpSession session,Books book,MultipartFile bookFile,MultipartFile bookimgFile, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("static/books/");
		String imgpath = request.getSession().getServletContext().getRealPath("static/bookimg/");
		Users cur_user = (Users) session.getAttribute("cur_user");
		
		ModelAndView mav = new ModelAndView("book_upload");
		
		long bookSize = bookFile.getSize();
		System.out.println("bookupload: bookSize is ==="+bookSize);
		book.setBookSize((float)bookSize);

		book.setUploadPerson(cur_user.getUserName());
		book.setUploadTime(new Date());
		book.setBookState(1);//1：未审核，2：审核，3：删除
		book.setBookDownloads(0);//初始下载量为0
		
		String fileName = FileUtils.getRandomFileName() + ".txt";
		String imgName = FileUtils.getRandomFileName() + ".jpg";
		
		book.setBookLocation(fileName);
		book.setBookCoverPath(imgName);
		
		System.out.println("bookupload: fileName is ==="+fileName);
		System.out.println("bookupload: imgName is ==="+imgName);
		
		try {
			File dir = new File(path, fileName);
			File imgdir = new File(imgpath, imgName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if(!imgdir.exists()){
				imgdir.mkdirs();
			}
			bookFile.transferTo(dir);
			bookimgFile.transferTo(imgdir);
			bookService.saveBook(book);//保存到数据库
			mav.addObject("upload_result", 1);
			return mav;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 下载图书
	 * @throws IOException 
	 */
	@RequestMapping(value = "/bookDownController", method = { RequestMethod.POST})
	@ResponseBody
	public void bookDownController(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String bookId = request.getParameter("bookId"); 
		Books book = bookService.findBookById(Integer.parseInt(bookId));
        //更新下载量
        book.setBookDownloads(book.getBookDownloads()+1);
        bookService.updateBookState(book);
	}

	/**
	 * 客户端获取今日排行图书
	 * 
	 * @return 返回图书列表
	 */
	@RequestMapping(value = "/getTodayBookRank", method = { RequestMethod.GET})
	@ResponseBody
	public List<Books> getTodayBookRankController(HttpServletRequest request,HttpSession session) {
		System.out.println("client ip is === "+IPUtils.getIpAddr(request));
		List<Books> booksList = bookService.getBooks();
		return booksList;
	}
	
	/**
	 * 客户端获取分类图书
	 * 
	 * @return 返回图书列表
	 */
	@RequestMapping(value = "/getClassificationBooks", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Books> getClassificationBooksController(HttpServletRequest request,HttpSession session) {
		System.out.println("client ip is === "+IPUtils.getIpAddr(request));
		String classificationName = request.getParameter("classificationName");
		initClassificationMap();
		List<Books> booksList = bookService.getClassificationBooks(classificationMap.get(classificationName));
		System.out.println("获取的 "+classificationMap.get(classificationName)+" 分类图书个数"+booksList.size());
		return booksList;
	}
	
	
	/**
	 * 客户端获取根据图书名模糊搜索的图书
	 * @return 返回图书列表
	 */
	@RequestMapping(value = "/getSearchBooks", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Books> getSearchBooksController(HttpServletRequest request,HttpSession session) {
		System.out.println("client ip is === "+IPUtils.getIpAddr(request));
		String searchBookName = request.getParameter("searchBookName");
		List<Books> booksList = bookService.searchBooks(searchBookName);
		return booksList;
	}
	
	/**
	 * 初始化分类表
	 * */
	private void initClassificationMap(){
		classificationMap.clear();
		classificationMap.put("小说", 1);
		classificationMap.put("文学", 2);
		classificationMap.put("传记", 3);
		classificationMap.put("历史", 4);
		classificationMap.put("经济", 5);
		classificationMap.put("管理", 6);
		classificationMap.put("励志", 7);
	}

}
