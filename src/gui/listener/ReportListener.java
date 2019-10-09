package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Record;
import gui.panel.ReportPanel;
import service.ReportService;

public class ReportListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ReportService rs = new ReportService();
		ReportPanel rp = ReportPanel.getInstance();
		Record record = rp.getSelectedRecord();
		
		if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(rp, "确认删除？")) {
		rs.delete(record);
		JOptionPane.showMessageDialog(rp, "删除成功");
		}
		rp.update();
	}

}
