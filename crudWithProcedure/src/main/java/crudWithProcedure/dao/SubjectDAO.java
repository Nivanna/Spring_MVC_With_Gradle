package crudWithProcedure.dao;

import java.util.List;

import crudWithProcedure.model.Subject;

public interface SubjectDAO {
	void save(Subject subject);
	void delete(int id);
	void update(Subject subject);
	List<Subject>  view();
	Subject get(int id);
	
}
