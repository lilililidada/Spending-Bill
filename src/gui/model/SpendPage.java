package gui.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.CategoryDAO;
import dao.ConfigDAO;
import dao.RecordDAO;
import entity.Config;
import entity.Record;
import util.DateUtil;

public class SpendPage {

	CategoryDAO categorydao = new CategoryDAO();
	ConfigDAO configdao = new ConfigDAO();
	RecordDAO recorddao = new RecordDAO();
	
	
	public SpendPage() {
		// TODO Auto-generated constructor stub
	}

	public String monthSpend() {
		List<Record> records = recorddao.listThisMonth();
		int total = 0;
		if(!records.isEmpty()) {
			for(Record record:records) {
				total = total +record.getSpend();
			}
		}
		return String.valueOf(total);
	}
	
	public String todaySpend() {
		List<Record> records = recorddao.listToday();
		int total = 0;
		if(!records.isEmpty()) {
			for(Record record:records) {
				total = total + record.getSpend();
			}
		}
		return String.valueOf(total);
	}
	
	public String avgSpendPerDay() {
		int spend = Integer.parseInt(this.monthSpend());
		int average =0;
		if(spend != 0) {
		average = spend/DateUtil.thisMonthToday();
		}
		return String.valueOf(average);
	}
	
	public String monthAvailable() {
		Config config = configdao.getByKey("Monthbudget");
		int budget = Integer.parseInt(config.getValue());
		int spend = Integer.parseInt(this.monthSpend());
		int available = budget - spend;
		if(available < 0) {
			available = -available;
			return String.format("超支%d", available);
		}
		return String.valueOf(available);
	}
	
	public String dayAvgAvailable() {
		int averageAva = 0;
		if(!isOverSpend()) {
		int available = Integer.parseInt(this.monthAvailable());
		averageAva = available/DateUtil.thisMonthLeftDay();
		}
		return String.valueOf(averageAva);
	}
	
	public String monthLeftDay() {
		return String.valueOf(DateUtil.thisMonthLeftDay());
	}
	
	public int usagePercentage() {
		int percentage = 0;
		int spend = Integer.parseInt(monthSpend());
		Config config = configdao.getByKey("Monthbudget");
		int budget = Integer.parseInt(config.getValue());
		if(spend != 0) {
			percentage = spend * 100 /budget;
		}
		return percentage;
	}
	
	public boolean isOverSpend() {
		Config config = configdao.getByKey("Monthbudget");
		int budget = Integer.parseInt(config.getValue());
		int spend = Integer.parseInt(this.monthSpend());
		int available = budget - spend;
		if(available < 0) {
			return true;
		}
		return false;
	}
}
