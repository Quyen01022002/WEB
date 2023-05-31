package vn.iotstar.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.DAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.ProductsModels;
import vn.iotstar.model.UserModels;
import util.MaHoa;

/**
 * Servlet implementation class chitiet
 */
@WebServlet("/login")
public class Dangnhap extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("X-Content-Type-Options", "nosniff");
		request.getRequestDispatcher("/view/user/dangnhap.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("X-Content-Type-Options", "nosniff");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		pass=MaHoa.toSHA1(pass);
		UserDAO dao=new UserDAO();
		UserModels usercur= dao.SosachId(user, pass);
		 HttpSession session=request.getSession();
		if(usercur==null)
		{
			request.setAttribute("error", "Thông tin đăng nhập không đúng!");
			request.getRequestDispatcher("/view/user/dangnhap.jsp").forward(request, response);
		}
		else
		{
			if(usercur.getRole()==2)
			{
				 session.setAttribute("idshipper", usercur.getUserID());
					response.sendRedirect("homeshipper");
			}
			else if(usercur.getRole()==1)
			{
			 session.setAttribute("id", usercur.getUserID());
			response.sendRedirect("home");
			}
		}
		
	}

}
