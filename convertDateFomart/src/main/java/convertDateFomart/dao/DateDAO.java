package convertDateFomart.dao;

import java.util.List;

import convertDateFomart.model.DateFormat;

public interface DateDAO {
	
	List<DateFormat> getListOfDate();
	void deleteDate(int id);
	void addNewDate(DateFormat dateFormat);
}
