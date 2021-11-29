package convertDateFomart.model;

import java.util.Date;

public class DateFormat {
	private int id;
	private Date dateconvertor;
	private String dateresult;
	public String getDateresult() {
		return dateresult;
	}
	public void setDateresult(String dateresult) {
		this.dateresult = dateresult;
	}
	public DateFormat() {}
	public DateFormat(Date dateconvertor) {
		super();
		this.dateconvertor = dateconvertor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateconvertor() {
		return dateconvertor;
	}
	public void setDateconvertor(Date currentDate) {
		this.dateconvertor = currentDate;
	}
	
}
