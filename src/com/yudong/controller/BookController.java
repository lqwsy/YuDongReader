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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.yudong.service.BookService;
import com.yudong.utils.IPUtils;

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
	public String bookUploadController(MultipartFile book,MultipartFile bookimg, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("static/books");
		String imgpath = request.getSession().getServletContext().getRealPath("static/bookimg");
		System.out.println("bookupload : path is =" + path);
		System.out.println("bookupload : imgpath is =" + imgpath);
		String fileName = book.getOriginalFilename();
		String imgName = bookimg.getOriginalFilename();
		System.out.println("bookupload : imgName is =" + imgName);
		System.out.println("bookupload : fileName is =" + fileName);
		try {
			File dir = new File(path, fileName);
			File imgdir = new File(imgpath, imgName);
			if (!dir.exists() && !imgdir.exists()) {
				dir.mkdirs();
				imgdir.mkdirs();
			}
			book.transferTo(dir);
			bookimg.transferTo(imgdir);
			return fileName+imgName;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName+imgName;
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
	 * 获取今日排行图书
	 * 
	 * @return 返回图书列表
	 */
	@RequestMapping(value = "/getTodayBookRank", method = { RequestMethod.GET})
	@ResponseBody
	public List<Books> getTodayBookRankController(HttpServletRequest request,HttpSession session) {
		
		System.out.println("client ip is === "+IPUtils.getIpAddr(request));
		
		List<Books> booksList = new ArrayList<>();
		String[] books = {"book001.txt","book002.txt","book003.txt","book004.txt","book005.txt","book006.txt","book007.txt"};
		String[] bookNames = { "巴比伦尘封6000年的财富智慧", "余华-兄弟", "被毁灭的人", "斗破苍穹" , "大宋王侯" , "乱明" , "1855美国大亨" };
		String[] bookImgs = { "a.jpg", "b.jpg", "c.jpg", "d.jpg" , "e.jpg", "f.jpg", "g.jpg"};
		String[] bookAuthors = { "Richest Man", "余华", "[美] 阿尔弗雷德·贝斯特   ", "天蚕土豆 " , "九孔 ", "喻心 ", "奶瓶战斗机 "};
		String[] bookIntros = { "几年前，当我在美国读书的时候，无意中发现了这样一本小册子--《The Richest Man in Babylon》，讲述的是关于巴比伦的理财故事。",
				"我们刘镇的超级巨富李光头异想天开，打算花上两千万美元的买路钱，搭乘俄罗斯联盟号飞船上太空去游览一番。",
				"无穷无尽的宇宙中万事因循旧轨，无异无新。渺不足道的人类自以为是巨变的奇迹，在上帝巨眼观照之下却只不过是必然发生的寻常事。",
				"望着测验魔石碑上面闪亮得甚至有些刺眼的五个大字，少年面无表情，唇角有着一抹自嘲，紧握的手掌，因为大力，而导致略微尖锐的指甲深深的刺进了掌心之中。",
				"一块奇异玉佩，得以梦回北宋初期，鲜血浸染了边关，杀戮遍及南北，华夏江山四分五裂，异族的铁蹄占去了半壁江山，此恨何及？此憾何结？ 我的故事只有金戈铁马的热血豪情，江湖厮杀的精彩绝伦，官场争斗的惊心动魄，儿女情长的荡气回肠。",
				"身逢乱世，惨遭家门巨变，是提三尺剑斩妖除魔，还是苦苦寻觅万世太平之策……",
				"1855，这是最好的时代，延伸的铁路，轰鸣的机车，流淌的黄金铸成了高耸入云的通天塔；这是一个最坏的时代，在通天塔那浓黑的阴影里，南北对立，贫富悬殊，弱肉强食。不想留在通天塔的阴影里任人践踏，就必须攀上它辉煌的塔顶，将一切踩在脚下。"};
		Integer[] bookDoanload = { 3215, 5410, 7874, 754 ,323,556,245};

		for (int i = 0; i < 7; i++) {
			Books book = new Books();
			book.setBookName(bookNames[i]);
			System.out.println(book.getBookName());
			book.setBookAuthor(bookAuthors[i]);
			book.setBookCoverPath(bookImgs[i]);
			book.setBookLocation(books[i]);
			book.setBookIntroduction(bookIntros[i]);
			book.setBookDownloads(bookDoanload[i]);
			booksList.add(book);
		}

		return booksList;
	}
	
	/**
	 * 图片上传
	 * @param 
	 * @return 跳转到登录页面
	 */
	@PostMapping("uploadImage")
    @ResponseBody
	public String personalUploadControll(HttpSession session,HttpServletRequest request,String img,Model model) {
		
		String serverPath = request.getSession().getServletContext().getRealPath("/");//获取项目运行路径  
        Base64 base64 = new Base64();
        
        try {  
            //实际的图片数据是从 data:image/jpeg;base64, 后开始的  
            byte[] k = base64.decode(img.substring("data:image/jpeg;base64,".length()));  
            InputStream is = new ByteArrayInputStream(k);  
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");// 图片上传日期
			String fileName = "static/bookimg/" + formatter.format(new Date()) + ".jpg";//用日期来作为图片唯一名称
			
			String imgFilePath = serverPath  + fileName;//图片绝对路径  
            BufferedImage image = ImageIO.read(is);   
            ImageIO.write(image, "jpg", new File(imgFilePath));//保存图片到本地
            File file = new File(serverPath + fileName);
    		if(file.exists()){
    			file.delete();
    		}
            return fileName;  
        } catch (Exception e) {  
            e.printStackTrace();
            return "error";  
        }
	}

}
