package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

public class RecordDAO {

	public void getTotal() {
		int total = 0;
		String sql = "select count(*) from record";
		try(Connection c = DBUtil.getConnection();
				Statement s = c.createStatement();) {
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
				
			}
			System.out.println("total:"+total);
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	
	public int getCategoryCount(int cid) {
		int count =0;
		String sql = "select count(*) from record where cid = ?";
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		return count;
	}
	
	public void add(Record record) {
		String sql = "insert into record value(null,?,?,?,?)";
		
		try(Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, record.getSpend());
			ps.setInt(2, record.getCid());
			ps.setString(3, record.getComment());
			ps.setDate(4, DateUtil.util2sql(record.getDate()));
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				record.setId(id);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		
	}
		public void delete(int id) {
			String sql = "delete from record where id= ?";
			
			try(Connection c = DBUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setInt(1, id);
				ps.execute();
				
				
			} catch (SQLException e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
		
		public void update(Record record) {
			String sql = "update record set spend = ?,cid = ?,comment = ?,date = ? where id = ?";
			
			try(Connection c = DBUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setInt(2, record.getCid());
				ps.setInt(1, record.getSpend());
				ps.setString(3, record.getComment());
				ps.setDate(4, DateUtil.util2sql(record.getDate()));
				ps.execute();
				
				
			} catch (SQLException e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
		
		public List<Record> list(){
			return list(0,Short.MAX_VALUE);
		}
		
		private List<Record> list(int start , int count){
			List<Record> records = new ArrayList<Record>();
			Record record = null;
			String sql = "select * from record order by id des limit ?,?";
			
			try(Connection c = DBUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setInt(1, start);
				ps.setInt(2, count);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					int spend = rs.getInt("spend");
					int cid = rs.getInt("cid");
					String comment = rs.getString("comment");
					Date date = rs.getDate("date");
					
					record = new Record(id,spend,cid,comment,date);
					
					records.add(record);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();// TODO: handle exception
			}
			
			
			return records;
		}
		
		public List<Record> listThisMonth(){
			return list(DateUtil.monthBegin(),DateUtil.monthEnd());
		}
		
		private List<Record> list(Date monthbegin, Date monthend){
			List<Record> records = new ArrayList<Record>();
			Record record = null;
			String sql = "select * from record where date >=? and date <=?";
			
			try(Connection c = DBUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setDate(1, DateUtil.util2sql(monthbegin));
				ps.setDate(2, DateUtil.util2sql(monthend));
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int id = rs.getInt("id");
					int spend = rs.getInt("spend");
					int cid = rs.getInt("cid");
					String comment = rs.getString("comment");
					Date date = rs.getDate("date");
					
					record = new Record(id,spend,cid,comment,date);
					
					records.add(record);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();// TODO: handle exception
			}
			
			return records;
		}
		
		public List<Record> listToday(){
			return list(DateUtil.today());
		}
		
		private List<Record> list(Date date){
			List<Record> records = new ArrayList<Record>();
			Record record = null;
			String sql = "select * from record where date = ?";
			
			try(Connection c = DBUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setDate(1, DateUtil.util2sql(date));
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int id = rs.getInt("id");
					int spend = rs.getInt("spend");
					int cid = rs.getInt("cid");
					String comment = rs.getString("comment");
					
					record = new Record(id,spend,cid,comment,date);
					
					records.add(record);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();// TODO: handle exception
			}
			
			return records;
		}
		
		public List<Record> list(int cid){
			List<Record> records = new ArrayList<Record>();
			String sql = "select * from record where cid = ?";
			Record record = null;
			
			try(Connection c = DBUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);) {
				ps.setInt(1, cid);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int id = rs.getInt("id");
					int spend = rs.getInt("spend");
					String comment = rs.getString("comment");
					Date date = rs.getDate("date");
					
					record = new Record(id,spend,cid,comment,date);
					
					records.add(record);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();// TODO: handle exception
			}
			
			return records;
		}
		
	    public RecordDAO() {
		
	}

}
