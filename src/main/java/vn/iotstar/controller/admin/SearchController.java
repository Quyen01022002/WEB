package vn.iotstar.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.SearchDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.DonHangModels;
import vn.iotstar.model.ProductModels;
import vn.iotstar.model.StoreModels;
import vn.iotstar.model.UserModels;

@WebServlet("/admin/search")
public class SearchController extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

	SearchDAO searchDao = new SearchDAO();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

		String key = req.getParameter("keysearch");
		req.setAttribute("keySearch", key);

		if (key == null || containsSpecialCharacter(key))
			key = "Không hợp lệ";

		String indexPro = req.getParameter("index_p");
		String indexUsr = req.getParameter("index_u");
		String indexOrd = req.getParameter("index_o");
		String indexSto = req.getParameter("index_s");

		if (indexPro == null)
			indexPro = "1";
		if (indexUsr == null)
			indexUsr = "1";
		if (indexOrd == null)
			indexOrd = "1";
		if (indexSto == null)
			indexSto = "1";

		int indexp = Integer.parseInt(indexPro);
		int indexu = Integer.parseInt(indexUsr);
		int indexo = Integer.parseInt(indexOrd);
		int indexs = Integer.parseInt(indexSto);

		int countP = searchDao.countSearch(1, key);
		int countU = searchDao.countSearch(2, key);
		int countO = searchDao.countSearch(3, key);
		int countS = searchDao.countSearch(4, key);

		int endP = countP / 8;
		int endU = countU / 6;
		int endO = countO / 6;
		int endS = countS / 8;

		if (countP / 8 != 0)
			endP++;
		if (countU / 6 != 0)
			endU++;
		if (countO / 6 != 0)
			endO++;
		if (countS / 8 != 0)
			endS++;

		List<ProductModels> productListNew = searchDao.SearchProduct(indexp - 1, key);
		req.setAttribute("products", productListNew);
		req.setAttribute("endP", endP);
		req.setAttribute("tagP", indexp);
		req.setAttribute("countP", countP);

		List<UserModels> userListNew = searchDao.SearchUser(indexu - 1, key);
		req.setAttribute("users", userListNew);
		req.setAttribute("endU", endU);
		req.setAttribute("tagU", indexu);
		req.setAttribute("countU", countU);

		List<DonHangModels> orderListNew = searchDao.SearchOrder(indexo - 1, key);
		req.setAttribute("orders", orderListNew);
		req.setAttribute("endO", endO);
		req.setAttribute("tagO", indexo);
		req.setAttribute("countO", countO);

		List<StoreModels> storeListNew = searchDao.SearchStore(indexs - 1, key);
		req.setAttribute("stores", storeListNew);
		req.setAttribute("endS", endS);
		req.setAttribute("tagS", indexs);
		req.setAttribute("countS", countS);

		req.setAttribute("keyss", key);

		req.getRequestDispatcher("/view/admin/search/search-result.jsp").forward(req, resp);
	}

	// fix Special Character
	public static boolean containsSpecialCharacter(String keyword) {
		// Xác định các ký tự đặc biệt bạn muốn tìm kiếm
		String specialCharacters = "[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]";

		// Tạo một biểu thức chính quy để kiểm tra
		Pattern pattern = Pattern.compile(specialCharacters);
		Matcher matcher = pattern.matcher(keyword);

		// Trả về true nếu có ký tự đặc biệt, ngược lại trả về false
		return matcher.find();
	}
}


