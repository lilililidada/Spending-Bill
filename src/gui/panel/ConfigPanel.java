package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ConfigDAO;
import gui.listener.ConfigListener;
import util.GUIUtil;

public class ConfigPanel extends WorkingPanel{

	private static final Color ColorUtil = null;
	private static ConfigPanel instance = new ConfigPanel();
	ConfigDAO dao = new ConfigDAO();
	JPanel p = new JPanel();
	JLabel tMonth = new JLabel("本月预算¥");
	JLabel Mysqlsrc = new JLabel("Mysql安装目录");
	public JTextField Monthbudget = new JTextField();
	public JTextField Mysqlpath = new JTextField();
	JButton bupdate = new JButton("更新");
	JPanel b = new JPanel();
	
	private ConfigPanel() {
		GUIUtil.setColor(ColorUtil, bupdate);
		b.add(bupdate);
		p.setLayout(new BorderLayout());
		p.add(this.north(),BorderLayout.NORTH);
		p.add(b,BorderLayout.CENTER);
		this.add(p);
		
		addListener();
	}
	private Component north() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,1,40,40));
		p.setPreferredSize(new Dimension(300,220));
		p.add(tMonth);
		p.add(Monthbudget);
		p.add(Mysqlsrc);
		p.add(Mysqlpath);
		
		return p;
	}
	public static ConfigPanel getConfigPanel() {
		return instance;
	}
	
	public void addListener() {
		ConfigListener listener = new ConfigListener();
		bupdate.addActionListener(listener);
	}
	@Override
	public void update() {
		String budget = dao.getByKey("Monthbudget").getValue();
		String path = dao.getByKey("Mysqlpath").getValue();
		Monthbudget.setText(budget);
		Mysqlpath.setText(path);
		instance.updateUI();
	}
}
