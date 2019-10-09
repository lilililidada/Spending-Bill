package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.panel.BackupPanel;
import gui.panel.CategoryPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.RecoverPanel;
import gui.panel.ReportPanel;
import gui.panel.SpendPanel;

public class ToolBarListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		MainPanel p = MainPanel.getMainPanel();
		JButton b = (JButton) e.getSource();
		if(b == p.bSpend) {
//			p.show(SpendPanel.getSpendPanel());
			p.workingPanel.show(SpendPanel.getSpendPanel());
		}else if(b == p.bBackup) {
//			p.show(BackupPanel.getBackupPanel());
			p.workingPanel.show(BackupPanel.getBackupPanel());
		}else if(b == p.bCategory) {
//			p.show(CategoryPanel.getCategoryPanel());
			p.workingPanel.show(CategoryPanel.getCategoryPanel());
		}else if(b == p.bConfig) {
//			p.show(ConfigPanel.getConfigPanel());
			p.workingPanel.show(ConfigPanel.getConfigPanel());
		}else if(b == p.bRecord) {
//			p.show(RecordPanel.getRecordPanel());
			p.workingPanel.show(RecordPanel.getRecordPanel());
		}else if(b == p.bRecover) {
//			p.show(RecoverPanel.getRecoverPanel());
			p.workingPanel.show(RecoverPanel.getRecoverPanel());
		}else if(b == p.bReport) {
//			p.show(ReportPanel.getInstance());
			p.workingPanel.show(ReportPanel.getInstance());
		}
		
	}

}
