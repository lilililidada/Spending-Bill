package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;

public class CategoryService {

	CategoryDAO dao = new CategoryDAO();
	Category c = null;
	List<Category> categorys = null;
	
	public void add(String name) {
		c = new Category();
		c.setName(name);
		dao.add(c);
	}
	
	public void update(Category category) {
		dao.update(category);
	}
	
	public void delete(int id) {
		dao.delete(id);
	}
	
	public List<Category> list(){
		categorys = dao.list();
		for(Category c:categorys) {
			int number = new RecordDAO().getCategoryCount(c.getId());
			c.setNumber(number);
		}
		Collections.sort(categorys, (c1,c2) ->c2.getNumber()-c1.getNumber());
		
		return categorys;
	}
	
	
	public CategoryService() {
		// TODO Auto-generated constructor stub
	}

}
