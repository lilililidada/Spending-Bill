package gui.model;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import entity.Category;
import service.CategoryService;

public class CategoryBox implements ComboBoxModel<Category> {

	private List<Category> categorys = new CategoryService().list();
	private Category category;
	
	public CategoryBox() {
		if(!categorys.isEmpty())
			category = categorys.get(0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return categorys.size();
	}

	@Override
	public Category getElementAt(int index) {
		// TODO Auto-generated method stub
		return categorys.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedItem(Object anItem) {
		category = (Category) anItem;
		
	}

	@Override
	public Object getSelectedItem() {
		if(!categorys.isEmpty()) {
			return category;
		}
		return null;
	}

	public void update() {
		categorys = new CategoryService().list();
	}
	public List<Category> getList(){
		return categorys;
	}
}
