package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Config;
import util.DBUtil;

public class ConfigDAO {

	public int getTotal() {
		int total=0;
		String sql = "select count(*) from config";
		
		try (
			Connection c = DBUtil.getConnection();
			Statement s = c.createStatement();)
			{
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total=rs.getInt("id");
			}
			System.out.println("total:"+total);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	public void add(Config config) {
		String sql = "insert into config values(null,?,?)";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, config.getKey());
			ps.setString(2, config.getValue());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();//数据持久化（暂时不懂）
			if(rs.next()) {                      //
				int id = rs.getInt(1);           //
				config.setId(id);                //
			}                                    //
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	public void delete(int id) {
		String sql = "delete from config where id = ?";
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(Config config) {
		String sql = "update config set key_ = ?,value_=? where id = ?";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, config.getKey());
			ps.setString(2, config.getValue());
			ps.setInt(3, config.getId());;
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	public Config get(int id) {
		Config config = null;
		String sql = "select * from config where id = ?";
		try(Connection c= DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				config = new Config();
				String key = rs.getString("key_");
				String value = rs.getString("value_");
				config.setId(id);
				config.setKey(key);
				config.setValue(value);
			}
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		return config;
	}
	public List<Config> list(){
		return list(0,Short.MAX_VALUE);
	}
	private List<Config> list(int start, int count) {
		// TODO Auto-generated method stub
		String sql = "select*from config order by id desc limit ? ,?";
		Config config = null;
		List<Config> configs = new ArrayList<Config>();
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String key = rs.getString("key_");
				String value = rs.getString("value_");
				
				config = new Config(id,key,value);
				
				configs.add(config);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		return configs;
	}
	
	public Config getByKey(String key) {
		String sql = "select * from config where key_= ?";
		Config config = null;
		
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				config = new Config();
				int id = rs.getInt("id");
				String value = rs.getString("value_");
				config.setId(id);
				config.setKey(key);
				config.setValue(value);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		return config;
	}
}
