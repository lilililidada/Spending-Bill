package gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entity.Record;
import gui.listener.ReportListener;
import gui.model.ReportTableModel;
import util.ColorUtil;
import util.GUIUtil;

public class ReportPanel extends WorkingPanel{

	private static ReportPanel instance = new ReportPanel();
	JPanel center = new JPanel();
	JPanel south  = new JPanel();
	JButton delete = new JButton("删除");
	ReportTableModel rtm = new ReportTableModel();
	JTable t = new JTable(rtm);;
	
	private ReportPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, delete);
		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		center.add(center());
		south.add(south());
		checkButton();
		addListener();
	}
	private void checkButton() {
		if(rtm.getList().size() == 0) {
			delete.setEnabled(false);
		}else {
			delete.setEnabled(true);
			t.getSelectionModel().addSelectionInterval(0, 0);
		}
		
	}
	private Component center() {
		JScrollPane sp = new JScrollPane(t);
		
		return sp;
	}
	private Component south() {
		JPanel p = new JPanel();
		p.add(delete);
		
		return p;
	}
	public static ReportPanel getInstance() {
		return instance;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		rtm.updateDate();
		t.updateUI();
		checkButton();
	}
	
	public Record getSelectedRecord() {
		int rowIndex= t.getSelectedRow();
		return rtm.getRecord(rowIndex);
	}
	
	@Override
	public void addListener() {
		ReportListener listener = new ReportListener();
		delete.addActionListener(listener);
		
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(instance);
	}
}
