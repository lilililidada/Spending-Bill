package gui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.CategoryDAO;
import entity.Record;

import service.RecordService;
import service.ReportService;

public class ReportTableModel extends AbstractTableModel{
	private ReportService rs = new ReportService();
	private List<Record> records = rs.list();
	private Record record;
	private CategoryDAO categorydao = new CategoryDAO();
	private String[] columnNames = {"时间","开销","分类","ID"};
	
	public ReportTableModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return records.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	public String getColumnName(int columnIndex) {
		
		return columnNames[columnIndex];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		record = records.get(rowIndex);
		
		if(columnIndex == 0) {
			return record.getDate();
		}else if(columnIndex == 1) {
			return record.getSpend();
		}else if(columnIndex == 2) {
			return categorydao.getname(record.getCid());
		}else if(columnIndex == 3) {
			return record.getId();
		}
		return null;
	}

	public Record getRecord(int rowIndex) {
		record = records.get(rowIndex);
		return record;
	}
	
	public void updateDate() {
		records = rs.list();
	}
	public List<Record> getList(){
		return records;
	}
}
