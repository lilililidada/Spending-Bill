package gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.model.SpendPage;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

public class SpendPanel extends WorkingPanel{

	private static SpendPanel instance = new SpendPanel();
	JLabel lMonthSpend = new JLabel("本月消费");
	JLabel lTodaySpend = new JLabel("今日消费");
    JLabel lAvgSpendPerDay = new JLabel("日均消费");
    JLabel lMonthLeft = new JLabel("本月剩余");
    JLabel lDayAvgAvailable = new JLabel("日均可用");
    JLabel lMonthLeftDay = new JLabel("距离月末");
    SpendPage sp = new SpendPage();
    CircleProgressBar bar;
    
    JLabel vMonthSpend = null;
    JLabel vTodaySpend = null;
    JLabel vAvgSpendPerDay = null;
    JLabel vMonthAvailable = null;
    JLabel vDayAvgAvailable = null;
    JLabel vMonthLeftDay = null;
    
    
    private SpendPanel() {
    	bar = CircleProgressBar.getIntance();
    	bar.setProgress(50);
    	bar.setBackgroundColor(ColorUtil.blueColor);
    	vMonthSpend = new JLabel(sp.monthSpend());
        vTodaySpend = new JLabel(sp.todaySpend());
        vAvgSpendPerDay = new JLabel(sp.avgSpendPerDay());
        vMonthAvailable = new JLabel(sp.monthAvailable());
        vDayAvgAvailable = new JLabel(sp.dayAvgAvailable());
        vMonthLeftDay = new JLabel(sp.monthLeftDay());
    	
    	this.setLayout(new BorderLayout());
        
         
        GUIUtil.setColor(ColorUtil.grayColor,lMonthSpend,lTodaySpend,lAvgSpendPerDay,lMonthLeft
                ,lDayAvgAvailable,lMonthLeftDay,vAvgSpendPerDay,
                vMonthAvailable,vDayAvgAvailable,vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor,vMonthSpend,vTodaySpend);
         
        vMonthSpend.setFont(new Font("微软雅黑",Font.BOLD,16));
        vTodaySpend.setFont(new Font("微软雅黑",Font.BOLD,16));
         
        this.add(center(),BorderLayout.CENTER);
        this.add(south(),BorderLayout.SOUTH);
    }
    public static SpendPanel getSpendPanel() {
    	return instance;
    }
    private JPanel south() {
    	JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 4));
		p.add(lAvgSpendPerDay);
		p.add(lMonthLeft);
		p.add(lDayAvgAvailable);
		p.add(lMonthLeftDay);
		p.add(vAvgSpendPerDay);
		p.add(vMonthAvailable);
		p.add(vDayAvgAvailable);
		p.add(vMonthLeftDay);
		return p;
    }
    private JPanel center() {
    	JPanel p = new JPanel();
    	p.setLayout(new BorderLayout());
		p.add(west(), BorderLayout.WEST);
		p.add(center2(),BorderLayout.CENTER);
		return p;
    }
    private Component center2() {
    	return bar;
    }
    private Component west() {
    	JPanel p = new JPanel();
    	p.setLayout(new GridLayout(4, 1));
    	p.add(lMonthSpend);
		p.add(vMonthSpend);
		p.add(lTodaySpend);
		p.add(vTodaySpend);

    	return p;
    }
//    public static void main(String[] args) {
//		GUIUtil.showPanel(instance);
//	}
	@Override
	public void update() {
		vMonthSpend.setText(sp.monthSpend());
        vTodaySpend.setText(sp.todaySpend());
        vAvgSpendPerDay.setText(sp.avgSpendPerDay());
        vMonthAvailable.setText(sp.monthAvailable());
        vDayAvgAvailable.setText(sp.dayAvgAvailable());
        vMonthLeftDay.setText(sp.monthLeftDay());
        bar.setProgress(sp.usagePercentage());
        instance.updateUI();
		
	}
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}
}
