package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.MysqlUtil;

public class BackupListener implements ActionListener {

	

	@Override
	public void actionPerformed(ActionEvent e) {
		BackupPanel p = BackupPanel.getBackupPanel();
		String mysqlPath = new ConfigService().getMysqlPath();
		if(mysqlPath.length() == 0) {
			JOptionPane.showMessageDialog(p, "请事先配置mysql路径");
			MainPanel.getMainPanel().workingPanel.show(ConfigPanel.getConfigPanel());
			ConfigPanel.getConfigPanel().Mysqlpath.grabFocus();
			return;
		}
		
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("hutubill.sql"));
		fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return ".sql";
			}

			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				return f.getName().toLowerCase().endsWith(".sql");
			}
		});
		
		
		int returnVal = fc.showSaveDialog(p);
		File file = fc.getSelectedFile();
		System.out.println(file);
		if( returnVal == JFileChooser.APPROVE_OPTION) {
			//如果保存的文件名没有以.sql结尾，自动加上.sql
			System.out.println(file);
			if(!file.getName().toLowerCase().endsWith(".sql"))
				file = new File(file.getParent(), file.getName()+".sql");
			try {
				
				MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
				JOptionPane.showMessageDialog(p, "备份成功，备份文件位于:\r\n"+file.getAbsolutePath());
				
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(p, "备份失败\r\n"+e2.getMessage());
			}
		}
		
	}

}
