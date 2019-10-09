package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecoverPanel;
import service.ConfigService;
import util.MysqlUtil;

public class RecoverListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		RecoverPanel p = RecoverPanel.getRecoverPanel();
		String mysqlPath = new ConfigService().getMysqlPath();
		
		if(mysqlPath.length() == 0) {
			JOptionPane.showMessageDialog(p, "请事先配置mysql路径");
			MainPanel.getMainPanel().workingPanel.show(ConfigPanel.getConfigPanel());
			ConfigPanel.getConfigPanel().Mysqlpath.grabFocus();
			return;
		}
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".sql");
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".sql";
			}
			
		});
		
		int returnVal = fc.showOpenDialog(p);
		
		File file = fc.getSelectedFile();
		System.out.println(file);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				MysqlUtil.recover(mysqlPath, file.getAbsolutePath());
				JOptionPane.showMessageDialog(p, "恢复成功");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(p, "恢复失败\r\n"+e2.getMessage());
			}
		}
		
		
	}

	

}
