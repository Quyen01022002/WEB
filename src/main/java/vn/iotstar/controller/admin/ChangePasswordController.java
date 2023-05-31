package vn.iotstar.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.DonHangDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.DonHangModels;
import vn.iotstar.model.UserModels;
import util.MaHoa;

/**
 * Servlet implementation class DoiMK
 */
@WebServlet("/admin/profile-changePw")
public class ChangePasswordController extends HttpServlet {
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
		
		req.getRequestDispatcher("/view/admin/profile/changePassword.jsp").forward(req, resp);
		 req.setCharacterEncoding("UTF-8");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 UserDAO dao=new UserDAO();
			String user=request.getParameter("password1");
			String pass=request.getParameter("password2");
			String repass=request.getParameter("password3");
			 HttpSession session=request.getSession();
			 int id= (int)session.getAttribute("AdminID");
		
			if(pass.equals(repass)==true)
			{
				user=MaHoa.toSHA1(user);
				if(dao.SosachMK(user)!=null)
				{
					request.setAttribute("sus", "Đã đổi thành công!");
					repass=MaHoa.toSHA1(repass);
					dao.DoiMk(repass,id);
					response.sendRedirect("profile");
				}
				else
				{
					request.setAttribute("error", "Mật khẩu cũ không chính xác!");
					 request.getRequestDispatcher("/view/admin/profile/changePassword.jsp").forward(request, response);
				}
			}
			else
			{
			request.setAttribute("error", "Mật khẩu không trùng nhau!");
			 request.getRequestDispatcher("/view/admin/profile/changePassword.jsp").forward(request, response);
			}
	}

}
