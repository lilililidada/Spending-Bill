package gui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.panel.MainPanel;
import gui.panel.SpendPanel;

public class MainFrame extends JFrame{

	private static MainFrame instance = new MainFrame();
	
	private MainFrame() {
		this.setSize(600,550);
		this.setTitle("一本糊涂账");
		this.setContentPane(MainPanel.getMainPanel());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static MainFrame getMainFrame() {
		return instance;
	}
	public static void main(String[] args) {
		instance.setVisible(true);
	}
}
