package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.MaHoa;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.UserModels;
import vn.iotstar.util.Constant;

@WebServlet("/admin/profile-edit")
public class EditProfileController extends HttpServlet {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("X-Content-Type-Options", "nosniff");
		HttpSession session = req.getSession();
		int idAdmin = 0;
		try {
			idAdmin = (int) session.getAttribute("AdminID");
			if (idAdmin == 0) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/login.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/login.jsp");
			dispatcher.forward(req, resp);

		}
		UserDAO userDao = new UserDAO();
		UserModels admin = userDao.get(idAdmin);
		req.setAttribute("admin", admin);
		req.getRequestDispatcher("/view/admin/profile/editProfile.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("X-Content-Type-Options", "nosniff");
		UserDAO userDao= new UserDAO();
		UserModels user=new UserModels();
		HttpSession session = req.getSession();
		int id=(int) session.getAttribute("id");
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setHeaderEncoding("UTF-8");
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("")) {
					user.setUserID(id);
				} else if (item.getFieldName().equals("name")) {
					user.setName(item.getString("UTF-8"));
				} else if (item.getFieldName().equals("images")) {
					if (item.getSize() > 0) {// neu co file d
						String originalFileName = item.getName();
						int index = originalFileName.lastIndexOf(".");
						String ext = originalFileName.substring(index + 1);
						String fileName = System.currentTimeMillis() + "." + ext;
						File file = new File(Constant.DIR + "/user/" + fileName);
						item.write(file);
						user.setAvatar("user/" + fileName);
					} else {
						user.setAvatar(null);
					}
				}else if (item.getFieldName().equals("phone")) {
					user.setPhone(item.getString("UTF-8"));
				}else if (item.getFieldName().equals("email")) {
					user.setEmail(item.getString("UTF-8"));
				}else if (item.getFieldName().equals("")) {
					user.setSalt(item.getString("UTF-8"));
				}else if (item.getFieldName().equals("")) {
					user.setHash_password(item.getString("UTF-8"));
				}else if (item.getFieldName().equals("")) {
					user.setPoint(Integer.parseInt(item.getString("UTF-8")));
				}
				
				
			}
			userDao.editAdmin(user,id);
			resp.sendRedirect(req.getContextPath() + "/admin/profile");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
