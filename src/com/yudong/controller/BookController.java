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
import java.text.SimpleDateFormat;
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
	public String goToMyBook(HttpSession session,Model model) {
		Users cur_user = (Users) session.getAttribute("cur_user");
		if(cur_user!=null){
			System.out.println("cur_user not null");
			List<Books> mybooks = bookService.getMyBooks(cur_user.getUserName());
			System.out.println("mybooks count is : "+mybooks.size());
			session.setAttribute("cur_user_books", mybooks);
			return "book_my";
		}
		return "book_my";
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
	 * 后台图书管理
	 * 图书详细信息页面
	 * @param 
	 * @return 跳转到图书信息页面
	 */
	@RequestMapping(value = "/bookDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToUserDetail() {
		
		
		
		
		
		return "admin/book_detail";
	}
	
	/**
	 * 图书信息页面
	 * @param 
	 * @return 跳转到图书信息页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/myBookInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToMyBookInfo(HttpSession session,Model model,int index) {
		if(index!=0){
			List<Books> myBooks = (List<Books>) session.getAttribute("cur_user_books");
			session.setAttribute("my_book_info", myBooks.get(index-1));
			return "book_info";
		}
		return "book_my";
	}
	
	/**
	 * 删除图书页面，状态设置为已删除
	 * @param 
	 * @return 跳转到图书信息页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteBook", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteBookController(HttpSession session,int index) {
		if(index!=0){
			List<Books> myBooks = (List<Books>) session.getAttribute("cur_user_books");
			Books delete_book = myBooks.get(index-1);
			delete_book.setBookState(3);
			if(bookService.updateBookState(delete_book)){
				myBooks.remove(index-1);
				session.setAttribute("cur_user_books", myBooks);
			}
			return "book_my";
		}
		return "book_my";
	}
	
	/**
	 * 获取已删除图书页面
	 * @param 
	 * @return 跳转到已删除图书信息页面
	 */
	@SuppressWarnings("unchecked")
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
	public String updateMyBookInfoController(HttpSession session,MultipartFile bookFile,Books book) {
		String path = session.getServletContext().getRealPath("static/books/");
		Books cur_book = (Books) session.getAttribute("my_book_info");
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
				if(bookService.saveBook(cur_book)){//保存到数据库
					session.setAttribute("my_book_info", cur_book);
				}
				return "book_info";
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "book_info";
		}
		return "book_info";
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
	public String bookUploadController(HttpSession session,Books book,MultipartFile bookFile,MultipartFile bookimgFile, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("static/books/");
		String imgpath = request.getSession().getServletContext().getRealPath("static/bookimg/");
		Users cur_user = (Users) session.getAttribute("cur_user");
		
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
			return "redirect:/uploadBook";
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/uploadBook";
	}

	/**
	 * 下载图书
	 * @throws IOException 
	 */
	@RequestMapping(value = "/bookDownController", method = { RequestMethod.GET})
	@ResponseBody
	public void bookDownController(HttpServletRequest request,HttpServletResponse response) throws IOException {

		String fileName = request.getSession().getServletContext().getRealPath("static/books")+"/test1.txt";  
        
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
          
        String filename = "bookName.txt";  
        
        filename = URLEncoder.encode(filename,"UTF-8");  
        
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
            
        response.setContentType("multipart/form-data");   
        
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();
        bis.close();
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
