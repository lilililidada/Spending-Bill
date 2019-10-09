package gui.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.listener.BackupListener;
import util.GUIUtil;

public class BackupPanel extends WorkingPanel
{

	private static BackupPanel instance = new BackupPanel();
	JButton b = new JButton("备份");
	
	private BackupPanel() {
		add(b);
		addListener();
	}
	public static BackupPanel getBackupPanel() {
		return instance;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addListener() {
		b.addActionListener(new BackupListener());
		
	}

}
