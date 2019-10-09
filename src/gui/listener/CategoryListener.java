package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

public class CategoryListener implements ActionListener{
	

	public CategoryListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		CategoryPanel p = CategoryPanel.getCategoryPanel();
		CategoryService cs = new CategoryService();
		
		if(b == p.bAdd) {
			String name = JOptionPane.showInputDialog(null);
			if(name.length() == 0) {
				JOptionPane.showMessageDialog(p, "分类名不能为空");
			}
			cs.add(name);
		}
		
		if(b == p.bEdit) {
			Category c = p.getSelectedCategory();
			String name = JOptionPane.showInputDialog(null);
			if(name.length() == 0) {
				JOptionPane.showMessageDialog(p, "消费名称不能为空");
				return;
			}
			c.setName(name);
			
			cs.update(c);
		}
		if(b == p.bDelete) {
			Category c = p.getSelectedCategory();
			if(c.getNumber() != 0) {
				JOptionPane.showMessageDialog(p, "本分类下有消费记录存在，不能删除");
				return;
			}
			if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认删除？")) {
				return;
			}
			
			int id = c.getId();
			cs.delete(id);
		}
		
		p.update();
	}

}
