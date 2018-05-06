package com.yudong.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yudong.service.BookService;

/**
 * 图书相关控制类
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
	 * 图书上传
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
	 * 图书下载
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
	
	
	
}













