package com.yudong.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yudong.entity.Books;
import com.yudong.service.BookService;

/**
 * 图书管理
 */
@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	/**
	 * 跳转到图书上传页面
	 */
	@RequestMapping(value = "/bookUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public String goToUserLogin() {
		return "book_upload";
	}
	
	/**
	 * 上传图书
	 */
	@RequestMapping(value = "/bookUploadController", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String bookUploadController(MultipartFile file,HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("static/books");  
		System.out.println("bookupload : path is ="+ path );
		String fileName = file.getOriginalFilename(); 
		System.out.println("bookupload : fileName is ="+ fileName );
        try {
            File dir = new File(path,fileName);          
            if(!dir.exists()){  
                dir.mkdirs();  
            } 
			file.transferTo(dir);
			return fileName;  
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return fileName;
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
	}
	
	
	/**
	 * 获取今日排行图书
	 *  @return 返回图书列表
	 * */
	@PostMapping("getTodayBookRank")
	@ResponseBody
	public List<Books> getTodayBookRankController(HttpSession session) {
		List<Books> booksList = new ArrayList<>();
		String[] books = {"book001.txt","book002.txt","book003.txt","book004.txt"};
		String[] bookNames = {"巴比伦尘封6000年的财富智慧","余华-兄弟","被毁灭的人","斗破苍穹"};
		String[] bookImgs = {"a.jpg","b.jpg","c.jpg","d.jpg"};
		String[] bookAuthors = {"Richest Man","余华","[美] 阿尔弗雷德·贝斯特   ","天蚕土豆 "};
		String[] bookIntros = {
				"几年前，当我在美国读书的时候，无意中发现了这样一本小册子--《The Richest Man in Babylon》，讲述的是关于巴比伦的理财故事。"
				,"我们刘镇的超级巨富李光头异想天开，打算花上两千万美元的买路钱，搭乘俄罗斯联盟号飞船上太空去游览一番。"
				,"无穷无尽的宇宙中万事因循旧轨，无异无新。渺不足道的人类自以为是巨变的奇迹，在上帝巨眼观照之下却只不过是必然发生的寻常事。"
				,"望着测验魔石碑上面闪亮得甚至有些刺眼的五个大字，少年面无表情，唇角有着一抹自嘲，紧握的手掌，因为大力，而导致略微尖锐的指甲深深的刺进了掌心之中。"};
		Integer[] bookDoanload = {3215,5410,7874,754};
		
		for(int i=0;i<books.length;i++){
			Books book = new Books();
			book.setBookName(bookNames[i]);
			book.setBookAuthor(bookAuthors[i]);
			book.setBookCoverPath(bookImgs[i]);
			book.setBookLocation(books[i]);
			book.setBookIntroduction(bookIntros[i]);
			book.setBookDownloads(bookDoanload[i]);
			booksList.add(book);
		}
		
		return booksList;
	}
	
}













