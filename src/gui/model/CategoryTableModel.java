package gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Category;
import service.CategoryService;

public class CategoryTableModel extends AbstractTableModel{

	CategoryService cs = new CategoryService();
	String[] columnNames = new String[] {"分类名称","消费次数"};
	private List<Category> categorys = cs.list();
	Category c = null;
	
	public CategoryTableModel() {
		
	}
		
	// 返回一共有多少行
	public int getRowCount() {
		return categorys.size();
	}
	// 返回一共有多少列
	public int getColumnCount() {
		return columnNames.length;
	}
	// 获取每一列的名称
	public String getColumnName(int columnIndex) {
		
		return columnNames[columnIndex]; 
	}
	// 单元格是否可以修改
	public boolean isCellEditable(int rowIndex,int columnIndex) {
		return false;
	}
	// 每一个单元格里的值
	public Object getValueAt(int rowIndex,int columnIndex) {
		Category c = categorys.get(rowIndex);
		if(columnIndex == 0) {
			return c.getName();
		}
		if(columnIndex == 1) {
			return c.getNumber();
		}
		return null;
	}
	
	public Category getCategory(int rowIndex) {
		c= categorys.get(rowIndex);
		return c;
	}
	public void updateDate() {
		this.categorys = cs.list();
	}
	public List<Category> getList(){
		return categorys;
	}
}
