package vn.iotstar.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.DAO;
import vn.iotstar.dao.ReviewDAO;
import vn.iotstar.dao.StoreDAO;
import vn.iotstar.dao.Theodoi_STDAO;
import vn.iotstar.model.ProductsModels;
import vn.iotstar.model.StoreModels;
import vn.iotstar.model.*;

/**
 * Servlet implementation class chitiet
 */
@WebServlet("/locsao")
public class locsao extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("X-Content-Type-Options", "nosniff");//response.setContentType("text/html;charset=UTF-8");
		String sao = request.getParameter("sao");
		DAO dao = new DAO();
		int s = Integer.parseInt(sao);
		
		HttpSession session = request.getSession();
		int idsp=(int)session.getAttribute("idsp");
		try {
			int userid = (int) session.getAttribute("id");
			ProductsModels sp = dao.getIdSanpham(idsp);
			request.setAttribute("sp", sp);
			session.setAttribute("idsp", idsp);
			Theodoi_STDAO tddao = new Theodoi_STDAO();
			StoreDAO stdao = new StoreDAO();
			
			StoreModels st=stdao.get(sp.getStoreID());
			UserfollowModels flowed = tddao.check_theodoi(userid, sp.getStoreID());
			
			request.setAttribute("st", st);
			if (flowed.getNgaytg() != null) {
				System.out.println(sp.getRating());
				request.setAttribute("flowed", flowed);
			}
			ReviewDAO rvdao=new ReviewDAO();
			int slfollow=tddao.count_NTD(sp.getStoreID());
			request.setAttribute("slflow", slfollow);
			int slsp=tddao.count_SP(sp.getStoreID());
			request.setAttribute("slsp", slsp);
			request.setAttribute("shop", st);
			List<CommentVM> comment=rvdao.ds_comment(idsp,s);
			request.setAttribute("cmt", comment);
			request.getRequestDispatcher("/view/user/ChiTiet.jsp").forward(request,response);
	
			
		} catch (Exception e) {

			ProductsModels sp = dao.getIdSanpham(idsp);
			StoreDAO stdao = new StoreDAO();
			session.setAttribute("idsp", idsp);
			StoreModels st=stdao.get(sp.getStoreID());
			request.setAttribute("sp", sp);
			System.out.println(sp.getStoreID());
			request.setAttribute("st", st);
			ReviewDAO rvdao=new ReviewDAO();
			List<CommentVM> comment=rvdao.ds_comment(idsp,s);
			request.setAttribute("cmt", comment);
			request.getRequestDispatcher("/view/user/ChiTiet.jsp").forward(request,response);

		}



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
