package gui.model;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import entity.Record;
import service.RecordService;
import service.ReportService;

public class ReportComboBox implements ComboBoxModel<Record>{
	private ReportService rs = new ReportService();
	private List<Record> records = rs.list();
	private Record record;

	public ReportComboBox() {
		if(records!=null) {
			record = records.get(0);
		}
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return records.size();
	}

	@Override
	public Record getElementAt(int index) {
		// TODO Auto-generated method stub
		return records.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedItem(Object anItem) {
		record = (Record)anItem;
		
	}

	@Override
	public Object getSelectedItem() {
		if(records!=null) {
			return record;
		}
		return null;
	}

}
