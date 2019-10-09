package gui.panel;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class WorkingPanel extends JPanel {
	
	public abstract void update();
	public abstract void addListener();
}
