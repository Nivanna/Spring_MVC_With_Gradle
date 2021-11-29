package crudWithProcedure.model;


public class Subject {
	
	private int id;
	private String subjectName;
	private String teacherName;
	
	
	public Subject() {}
	public Subject(String subjectName, String teacherName) {
		super();
		this.subjectName = subjectName;
		this.teacherName = teacherName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}
