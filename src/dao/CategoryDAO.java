package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import util.DBUtil;

public class CategoryDAO {

	public int getTotal() {
		int total=0;
		String sql = "select count(*) from category";
		try(Connection c = DBUtil.getConnection();
				Statement s = c.createStatement();) {
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total++;
			}
			System.out.println("total:"+total);
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		return total;
	}
	
	public void add(Category category) {
		String sql = "insert into category value(null,?)";
		
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, category.getName());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				category.setId(id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	
	public void delete(int id) {
		String sql = "delete from category where id = ?";
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	
	public void update(Category category) {
		String sql = "update category set name=? where id = ?";
		
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	
	public List<Category> list(){
		return list(0,Short.MAX_VALUE);
	}
	
	private List<Category> list(int start,int count) {
		List<Category> categorys = new ArrayList<Category>();
		Category category = null;
		String sql = "select * from category order by id desc limit ?,?";
		
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				category = new Category(id,name);
				
				categorys.add(category);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		return categorys;
	}

	public String getname(int cid) {
		String name = "";
		String sql = "select * from category where id = ?";
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
}
