package service;

import javax.swing.JTextField;

import dao.ConfigDAO;
import entity.Config;
import util.GUIUtil;

public class ConfigService {

	private static final String budget = "Monthbudget";
	private static final String path = "Mysqlpath";
	private static final String default_budget = "500";
	
	static ConfigDAO dao = new ConfigDAO();
	static {
		init();
	}
	
	private static void init() {
		init(budget , default_budget);
		init(path , "");
	}
	
	private static void init(String key , String value) {
		
		Config config = dao.getByKey(key);
		if(config == null) {
			config = new Config();
			config.setKey(key);
			config.setValue(value);
			dao.add(config);
		}
	}
	
	public int getbudget() {
		Config config = dao.getByKey("Monthbudget");
		int value = Integer.parseInt(config.getValue());
		
		return value;
	}
	
	public String getMysqlPath() {
		Config config = dao.getByKey("Mysqlpath");
		String value = config.getValue();
		
		return value;
	}
	/*
	 * 下列方法中的if方法判断不太好，有可能出现没想到的情况也能执行else中的代码
	 * 有待优化
	 * 
	 * 
	 * */
    public  boolean update(JTextField budget , JTextField path) {
		if(!checkBudget(budget)) {
			return false;
		}else if(!checkpath(path)) {
			return false;
		}
		else {
			String Monthbuget = budget.getText().trim();
			String Mysqlpath = path.getText().trim();
			update("Monthbudget", Monthbuget);
			update("Mysqlpath" , Mysqlpath);
			
			return true;
		}	
	}
    
    private void update(String key , String value) {
    	Config config = dao.getByKey(key);
    	config.setValue(value);
    	dao.update(config);
    }
    
	private  boolean checkBudget(JTextField budget) {
		String input = "月预算";
		if(GUIUtil.checkEmpty(budget, input)) {
			return GUIUtil.checkZero(budget, input);
		}
		
		return false;
	}
	
	private  boolean checkpath(JTextField path) {
		if(GUIUtil.checkEmpty(path, "Mysql安装目录")) {
			return GUIUtil.checkPath(path);
		}
		
		return false;
	}
}
