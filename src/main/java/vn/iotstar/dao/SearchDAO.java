package vn.iotstar.dao;

import java.awt.dnd.InvalidDnDOperationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.connection.DBConnection;
import vn.iotstar.model.DonHangModels;
import vn.iotstar.model.ProductModels;
import vn.iotstar.model.StoreModels;
import vn.iotstar.model.UserModels;

public class SearchDAO extends DBConnection {

	public List<ProductModels> SearchProduct(int indexp, String key) {
		List<ProductModels> productList = new ArrayList<ProductModels>();
		String sql = "SELECT * FROM Products, Stores, Category \r\n"
				+ "where Products.storeid=stores.storeid and products.categoryid=category.categoryid and (Products.productID LIKE ? or Products.name LIKE ?)\r\n"
				+ "ORDER BY productID DESC OFFSET ?*8 rows fetch next 8 rows only";

		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(3, indexp);
			ps.setString(1,"%\"+key+\"%");
			ps.setString(2,"%\"+key+\"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModels product = new ProductModels();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setDescription(rs.getString(4));
				product.setPrice(rs.getInt(5));
				product.setQuantity(rs.getInt(7));
				product.setSold(rs.getInt(8));
				product.setIsActive(rs.getBoolean(9));
				product.setIsSelling(rs.getBoolean(10));
				product.setCategoryId(rs.getInt(11));
				product.setStoreId(rs.getInt(12));
				product.setRating(rs.getFloat(13));
				product.setCreateProDate(rs.getString(14));
				product.setUpdateProDate(rs.getString(15));
				product.setImg(rs.getString(16));
				product.setNameStore(rs.getString(18));
				product.setNameCategory(rs.getString(31));
				productList.add(product);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productList;
	}

	
	public List<UserModels> SearchUser (int indexp, String key){
		List<UserModels> userList = new ArrayList<UserModels>();
		String sql = "SELECT * FROM Users where userid like ? or name like ? or phone=? or email=? ORDER BY userID DESC OFFSET ?*6 rows fetch next 6 rows only";
		try {
			Connection conn= new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(5, indexp);
			ps.setString(1,"%\"+key+\"%");
			ps.setString(2,"%\"+key+\"%");
			ps.setString(3,"%\"+key+\"%");
			ps.setString(4,"%\"+key+\"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserModels user=new UserModels();
				user.setUserID(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setSalt(rs.getString(7));
				user.setHash_password(rs.getString(8));
				user.setRole(rs.getInt(9));
				user.setPoint(rs.getInt(11));
				user.setAvatar(rs.getString(10));
				userList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return userList;
	}
	
	
	public List<DonHangModels> SearchOrder(int indexp, String key)
	{
		List <DonHangModels> orderList= new ArrayList<DonHangModels>();
		String sql="Select * from [Order],Users where [Order].userid=Users.userid and [Order].orderid like ? ORDER BY orderID DESC OFFSET ?*6 rows fetch next 6 rows only";
		
		try {
			Connection conn=new DBConnection().getConnection();
			PreparedStatement ps= conn.prepareStatement(sql);

			ps.setInt(2, indexp);
			ps.setString(1,"%\"+key+\"%");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				DonHangModels order= new DonHangModels();
				order.setOderid(rs.getInt(1));
				order.setUserid(rs.getInt(2));
				order.setStoreid(rs.getInt(3));
				order.setDiachi(rs.getString(4));
				order.setTrangthai(rs.getString(7));
				order.setStatusTT(rs.getString(6));
				order.setPhone(rs.getString(5));
				order.setNgaynhap(rs.getString(10));
				order.setNgayup(rs.getString(11));
				order.setTong(rs.getInt(9));
				order.setShipperid(rs.getInt(8));
				order.setUsersName(rs.getString(13));
				orderList.add(order);		
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return orderList;
	}
	public List<StoreModels> SearchStore(int indexp, String key){
		List <StoreModels> stores= new ArrayList<StoreModels>();
		String sql="select Stores.storeID,  Stores.name, Stores.ownerID, Stores.isActive, Stores.isOpen, Stores.avatar, Stores.point, Stores.rating, Stores.createdAt, Stores.updatedAt, Users.name "
				+ "from Stores inner join Users on Stores.ownerID=Users.userID "
				+ "where Stores.storeid like ? or Stores.name like ? "
				+ "ORDER BY storeID DESC OFFSET ?*5 rows fetch next 5 rows only";
		try {
			Connection conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(3, indexp);
			ps.setString(1,"%\"+key+\"%");
			ps.setString(2,"%\"+key+\"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StoreModels store= new StoreModels();
				store.setStoreId(rs.getInt(1));
				store.setStoreName(rs.getString(2));
				store.setOwnerID(rs.getInt(3));
				store.setActive(rs.getBoolean(4));
				store.setOpen(rs.getBoolean(5));
				store.setAvatar(rs.getString(6));
				store.setPoint(rs.getInt(7));
				store.setRating(rs.getInt(8));
				store.setCreateAt(rs.getString(9));
				store.setUpdateAt(rs.getString(10));
				store.setOwnerName(rs.getString(11));
				stores.add(store);
			}
		} catch (Exception e) {

		}
		return stores;
	}
	public int countSearch(int list, String key) {
		String sql=null;
		if (list==1) sql="select count(*) from Products where productid like '%"+key+"%' or name like '%"+key+"%'";
		if (list==2) sql="select count(*) from Users where userid like '%"+key+"%' or name like '%"+key+"%' or email='"+key+"' or phone='"+key+"'";
		if (list==3) sql="select count(*) from [Order] where orderid like '%"+key+"%'";
		if (list==4) sql="select count(*) from Stores where storeid like '%"+key+"%' or name like '%"+key+"%'";
		
		try
		{
			Connection conn= new DBConnection().getConnection();
			PreparedStatement ps= conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);				
			}
			
			
		}catch(Exception e){
			
		}
		
		
		
		return 0;
	}
	
	
}
