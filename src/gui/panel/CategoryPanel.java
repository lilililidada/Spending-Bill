package gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import util.ColorUtil;
import util.GUIUtil;

public class CategoryPanel extends WorkingPanel{

	private static CategoryPanel instance = new CategoryPanel();
	JPanel center = new JPanel();
	JPanel south = new JPanel();
	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");
	CategoryTableModel ctm = new CategoryTableModel();
	JTable t = null;
	
	private CategoryPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bAdd,bEdit,bDelete);
		this.setLayout(new BorderLayout());
		center.add(this.center());
		south.add(this.south());
		this.add(center,BorderLayout.CENTER);
		this.add(south,BorderLayout.SOUTH);
		this.checkButton();
		
		addListener();
	}
	public static CategoryPanel getCategoryPanel() {
		return instance;
	}
	private JScrollPane center() {
		t = new JTable(ctm);
		JScrollPane sp = new JScrollPane(t);
		sp.setPreferredSize(new Dimension(350,280));
		
		return sp;
	}
	private Component south() {
		JPanel p = new JPanel();
		p.add(bAdd);
		p.add(bEdit);
		p.add(bDelete);
		
		return p;
	}
	public Category getSelectedCategory() {
		int rowIndex= t.getSelectedRow();
		return ctm.getCategory(rowIndex);
	}
	public void addListener() {
		CategoryListener cl = new CategoryListener();
		
		bAdd.addActionListener(cl);
		bEdit.addActionListener(cl);
		bDelete.addActionListener(cl);
	}
	
	private void checkButton() {
		if(ctm.getList().size() == 0) {
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
		}else {
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
			t.getSelectionModel().addSelectionInterval(0, 0);
		}
	}
	@Override
	public void update() {
		ctm.updateDate();
		t.updateUI();
		this.checkButton();
		
	}
}
