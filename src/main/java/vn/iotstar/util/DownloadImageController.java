package vn.iotstar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/image") // ?fname=abc.png
public class DownloadImageController extends HttpServlet {
	
	
	/*
	 * @Override protected void doGet(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { String fileName =
	 * req.getParameter("fname"); File file = new File(Constant.DIR + "/" +
	 * fileName); resp.setContentType("image/jpeg"); if (file.exists()) {
	 * IOUtils.copy(new FileInputStream(file), resp.getOutputStream()); } }
	 * 
	 */
	
	
	  @Override 
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			  throws ServletException, IOException { 
		  String fileName =	  req.getParameter("fname"); 
		  Path filePath = Paths.get(Constant.DIR, fileName).normalize();
	  	  if (filePath.startsWith(Paths.get(Constant.DIR).normalize())) { 
	  		  File file =  filePath.toFile();
	  		  if (file.exists()) { 
	  			  resp.setContentType("image/jpeg"); 
	  			  IOUtils.copy(new  FileInputStream(file), resp.getOutputStream()); 
	  			  return; } 
	  		  }
	  // Xử lý trường hợp không tìm thấy hoặc không hợp lệ
	  resp.setStatus(HttpServletResponse.SC_NOT_FOUND); }
	 
	 
}