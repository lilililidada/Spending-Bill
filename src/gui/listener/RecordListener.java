package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import entity.Category;
import gui.panel.RecordPanel;
import service.RecordService;
import util.GUIUtil;

public class RecordListener implements ActionListener {

	RecordService rs = new RecordService();
	
	
	public RecordListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RecordPanel p = RecordPanel.getRecordPanel();
		Category category;
		String comment;
		Date date;
		int spend;
		if(GUIUtil.checkZero(p.bspend, "消费数额")) {
			spend = Integer.parseInt(p.bspend.getText().trim());
			category = p.getSelectCategory();
			comment = p.bnote.getText();
			date = p.datepick.getDate();
			if(date != null) {
				rs.add(spend, category, comment, date);
				p.update();
				JOptionPane.showMessageDialog(p, "添加成功");
			}
			else{
				JOptionPane.showMessageDialog(p, "请选择日期");
				return;	
			}
		}
		return;
	}

}
