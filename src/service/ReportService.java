package service;

import java.util.List;

import dao.RecordDAO;
import entity.Record;

public class ReportService {

	private List<Record> records;
	private RecordDAO dao = new RecordDAO();
	
	public ReportService() {
		// TODO Auto-generated constructor stub
	}

	public List<Record> list(){
		records = dao.listThisMonth();
		return records;
	}
	
	public void delete(Record record) {
		dao.delete(record.getId());
	}
	
}
