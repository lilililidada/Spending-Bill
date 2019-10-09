package startup;

import javax.swing.SwingUtilities;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;

public class Bootstrap {
	public static void main(String[] args) throws Exception{
		
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				MainFrame.getMainFrame().setVisible(true);
				MainPanel.getMainPanel().workingPanel.show(SpendPanel.getSpendPanel());
			}
		});
	}

}
