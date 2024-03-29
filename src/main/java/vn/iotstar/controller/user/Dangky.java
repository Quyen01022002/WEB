package vn.iotstar.controller.user;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.DAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.UserModels;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import util.MaHoa;

@WebServlet("/dangky")
public class Dangky extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("view/user/dangky.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("/text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String user = request.getParameter("email");
		String pass = request.getParameter("pass");
		String ten = request.getParameter("ten");
		pass = MaHoa.toSHA1(pass);
		String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";
		String sdt = request.getParameter("sdt");

		if (containsSpecialCharacter(ten)) {
			request.setAttribute("error", "Họ và tên không hợp lệ");
			request.getRequestDispatcher("view/user/dangky.jsp").forward(request, response);
		} else if (containsSpecialCharacter(sdt)) {
			request.setAttribute("error", "Số điện thoại không hợp lệ");
			request.getRequestDispatcher("view/user/dangky.jsp").forward(request, response);
		} else if (checkEmail(user)) {
			request.setAttribute("error", "Email không hợp lệ");
			request.getRequestDispatcher("view/user/dangky.jsp").forward(request, response);
		} else {
			UserDAO dao = new UserDAO();
			UserModels tk = dao.Sosachuser(user);
			UserModels tk2 = dao.Sosachsdt(sdt);
			if (tk == null) {
				if (tk2 == null) {
					dao.dangky(user, pass, ten, sdt, salt);
					UserModels user_cur = dao.Sosachuser(user);
					System.out.print(user_cur.getUserID());
					dao.taoGH(user_cur.getUserID());
					response.sendRedirect("login");
				} else {
					request.setAttribute("error", "Số điện thoại đã tồn tại!");
					request.getRequestDispatcher("view/user/dangky.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Email đã tồn tại!");
				request.getRequestDispatcher("view/user/dangky.jsp").forward(request, response);
			}
		}
	}

	public static boolean containsSpecialCharacter(String keyword) {
		// Xác định các ký tự đặc biệt bạn muốn tìm kiếm
		String specialCharacters = "[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]";

		// Tạo một biểu thức chính quy để kiểm tra
		Pattern pattern = Pattern.compile(specialCharacters);

		// Tạo một matcher khớp với đầu vào đã cho với mẫu
		Matcher matcher = pattern.matcher(keyword);

		// Trả về true nếu có ký tự đặc biệt, ngược lại trả về false
		return matcher.find();
	}

	public static boolean checkEmail(String keyword) {
		// Xác định các ký tự đặc biệt bạn muốn tìm kiếm
		String specialCharacters = "[!#$%^&*()+\\-=\\[\\]{};':\"\\\\|,<>\\/?]";

		// Tạo một biểu thức chính quy để kiểm tra
		Pattern pattern = Pattern.compile(specialCharacters);

		// Tạo một matcher khớp với đầu vào đã cho với mẫu
		Matcher matcher = pattern.matcher(keyword);

		// Trả về true nếu có ký tự đặc biệt, ngược lại trả về false
		return matcher.find();
	}
}