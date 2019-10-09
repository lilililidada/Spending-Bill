package service;

import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Category;
import entity.Record;
import util.DateUtil;

public class RecordService {

	Record record ;
	RecordDAO dao = new RecordDAO();
	List<Record> records;
	
	public void add(int spend , Category category , String comment ,Date date) {
		record = new Record();
		record.setSpend(spend);
		record.setCid(category.getId());
		record.setComment(comment);
		record.setDate(DateUtil.util2sql(date));
		dao.add(record);
	}
	
	
	public RecordService() {
		
	}

}
