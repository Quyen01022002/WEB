package vn.iotstar.controller.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.CateDAO;
import vn.iotstar.dao.DAO;
import vn.iotstar.dao.ProductDAO;
import vn.iotstar.dao.ReviewDAO;
import vn.iotstar.dao.StoreDAO;
import vn.iotstar.model.CategoryModels;
import vn.iotstar.model.CommentVM;
import vn.iotstar.model.ProductModels;
import vn.iotstar.model.ProductsModels;
import vn.iotstar.model.StoreModels;

/**
 * Servlet implementation class chitiet
 */
@WebServlet("/detail")
public class chitiet extends HttpServlet {
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setHeader("X-Content-Type-Options", "nosniff");
			//response.setContentType("text/html;charset=UTF-8");
			String id=request.getParameter("masp");
			ProductDAO dao =new ProductDAO();
			int idsp=Integer.parseInt (id);
			ProductModels sp=dao.get(idsp);
			request.setAttribute("sp", sp);
			ReviewDAO rvdao=new ReviewDAO();
			CateDAO catedao=new CateDAO();
			CategoryModels cate=catedao.get(sp.getCategoryId());
			request.setAttribute("catename", cate.getNamecate());
			StoreDAO stdao=new StoreDAO();
			StoreModels st=stdao.getstore_SP(sp.getStoreId());
			request.setAttribute("shopname", st.getStoreName());
			List<CommentVM> comment=rvdao.ds_comment(idsp);
			request.setAttribute("cmt", comment);
			int slcmt=rvdao.count_rv(idsp);
			request.setAttribute("slcmt", slcmt);
			HttpSession session = request.getSession();
			int idst = (int) session.getAttribute("storeid");
			StoreDAO userDao= new StoreDAO();
			StoreModels admin= userDao.get(idst);
			request.setAttribute("store", admin);
			request.setAttribute("stid", idst);
			request.getRequestDispatcher("/view/seller/product/detail.jsp").forward(request, response);
			
			
		}
	
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			doGet(request, response);
		}

}
