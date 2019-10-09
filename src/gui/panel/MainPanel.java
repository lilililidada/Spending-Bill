package gui.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import gui.listener.ToolBarListener;
import util.GUIUtil;

public class MainPanel extends JPanel{
	
	private static MainPanel instance = new MainPanel();
	JToolBar tb = new JToolBar();
	public JButton bSpend = new JButton();
	public JButton bRecord = new JButton();
	public JButton bCategory = new JButton();
	public JButton bReport = new JButton();
	public JButton bConfig = new JButton();
	public JButton bBackup = new JButton();
	public JButton bRecover = new JButton();
//	public JPanel workingPanel = new JPanel();
	public CenterPanel workingPanel;
	
	private MainPanel() {
	
	
	GUIUtil.setImageIcon(bSpend, "消费一览.png", "消费一览");
	GUIUtil.setImageIcon(bRecord, "记一笔.png", "记一笔");
	GUIUtil.setImageIcon(bCategory, "消费分类.png", "消费分类");
	GUIUtil.setImageIcon(bReport, "月消费报表.png", "月消费报表");
	GUIUtil.setImageIcon(bConfig, "设置.png", "设置");
	GUIUtil.setImageIcon(bBackup, "备份.png", "备份");
	GUIUtil.setImageIcon(bRecover, "恢复.png", "恢复");

//	JButton bSpend = new JButton(new ImageIcon("E:/Project/yibenhutu/iamge/iamge/消费一览.png");
//    JButton bRecord = new JButton(new ImageIcon("E:/Project/yibenhutu/iamge/iamge/记一笔.png"));
//    JButton bCategory = new JButton(new ImageIcon("E:/Project/yibenhutu/iamge/iamge/消费分类.png"));
//    JButton bReport = new JButton(new ImageIcon("E:/Project/yibenhutu/iamge/iamge/月消费报表.png"));
//    JButton bConfig = new JButton(new ImageIcon("E:/Project/yibenhutu/iamge/iamge/设置.png"));
//    JButton bBackup = new JButton(new ImageIcon("E:/Project/yibenhutu/iamge/iamge/备份.png"));
//    JButton bRecover = new JButton(new ImageIcon("E:/Project/yibenhutu/iamge/iamge/恢复.png"));
    workingPanel = new CenterPanel(0.8);
    
    tb.add(bSpend);
    tb.add(bRecord);
    tb.add(bCategory);
    tb.add(bReport);
    tb.add(bConfig);
    tb.add(bBackup);
    tb.add(bRecover);
    tb.setFloatable(false);
    
    setLayout(new BorderLayout());
    add(tb, BorderLayout.NORTH);
    add(workingPanel,BorderLayout.CENTER);
    addListener();
    
    }
	public static MainPanel getMainPanel() {
		return instance;
	}
	private void addListener() {
		ToolBarListener listener = new ToolBarListener();
		
		bSpend.addActionListener(listener);
		bRecord.addActionListener(listener);
		bCategory.addActionListener(listener);
		bReport.addActionListener(listener);
		bConfig.addActionListener(listener);
		bBackup.addActionListener(listener);
		bRecover.addActionListener(listener);
	}
//	public void show(JPanel p) {
//		workingPanel.removeAll();
//		workingPanel.add(p);
//		workingPanel.invalidate();
//		repaint();
//	}
}
