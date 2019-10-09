package gui.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.listener.RecoverListener;

public class RecoverPanel extends WorkingPanel{

	private static RecoverPanel instance = new RecoverPanel();
	JButton b = new JButton("恢复");
	
	private RecoverPanel() {
		add(b);
		addListener();
	}
	public static RecoverPanel getRecoverPanel() {
		return instance;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		b.addActionListener(new RecoverListener());
	}
}
