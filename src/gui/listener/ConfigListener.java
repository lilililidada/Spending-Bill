package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.panel.ConfigPanel;
import service.ConfigService;

public class ConfigListener implements ActionListener {

	public ConfigListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ConfigPanel p = ConfigPanel.getConfigPanel();
		ConfigService cs = new ConfigService();
		
		if(cs.update(p.Monthbudget, p.Mysqlpath)) {
		JOptionPane.showMessageDialog(p, "设置修改成功");
		p.update();
		}
		else
			return;
	}

}
