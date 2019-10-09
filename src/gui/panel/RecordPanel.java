package gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryBox;
import util.ColorUtil;
import util.GUIUtil;

public class RecordPanel extends WorkingPanel{

	private static RecordPanel instance = new RecordPanel();
	JLabel spend = new JLabel("花费（¥）");
	JLabel category = new JLabel("分类");
	JLabel note = new JLabel("备注");
	JLabel date = new JLabel("日期");
	JButton b = new JButton("记一笔");
	public JTextField bspend = new JTextField();
	CategoryBox box = new CategoryBox();
	JComboBox<Category> categoryBox = new JComboBox<Category>(box) ;   //从数据库中提取分类    补全泛型类型！！！
	public JTextField bnote = new JTextField();
	public final JXDatePicker datepick = new JXDatePicker();
	
	private RecordPanel() {
		this.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(this.north());
		JPanel center = new JPanel();
		center.add(this.south());
		GUIUtil.setColor(ColorUtil.grayColor,spend,category,note,date);
		this.add(north,BorderLayout.NORTH);
		this.add(center,BorderLayout.CENTER);
		
		addListener();
		checkButton();
	}
	public static RecordPanel getRecordPanel() {
		return instance;
	}
	private Component north() {
		JPanel p =new JPanel();
		p.setLayout(new GridLayout(4,2,40,40));
		p.setPreferredSize(new Dimension(360,200));
		p.add(spend);
		p.add(bspend);
		p.add(category);
		p.add(categoryBox);
		p.add(note);
		p.add(bnote);
		p.add(date);
		p.add(datepick);
		
		return p;
	}
	private Component south() {
		b.setPreferredSize(new Dimension(40,40));
		
		return b;
	}
	
	private void reset() {
		bspend.setText("0");
		bnote.setText("");
		datepick.setDate(new Date());
	}
	
	private void checkButton() {
		if(box.getList().size() == 0) {
			b.setEnabled(false);
			JOptionPane.showMessageDialog(instance, "没有可选择的分类");
		}else {
			b.setEnabled(true);
			categoryBox.setSelectedIndex(0);
		}
	}
	
	@Override
	public void update() {
		box.update();
		categoryBox.updateUI();
		this.reset();
		this.updateUI();
		checkButton();
		
		
	}
	@Override
	public void addListener() {
		RecordListener listener = new RecordListener();
		b.addActionListener(listener);
		
	}
	
	public Category getSelectCategory() {
		return (Category) categoryBox.getSelectedItem();
	}
	
}
