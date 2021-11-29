package crudWithProcedure.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import crudWithProcedure.model.Subject;

public class SubjectDAOImpl implements SubjectDAO{
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall subjectRecord;
	private SimpleJdbcCall allSubjectRecords;
	private SimpleJdbcCall deleteSubjectRecord;
	
	public SubjectDAOImpl(DataSource dataSource){
		
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    
	    jdbcTemplate.setResultsMapCaseInsensitive(true);
	    
		//read one record
		this.subjectRecord = new SimpleJdbcCall(dataSource).withProcedureName("read_subject");
		
	    //read all records
		this.allSubjectRecords = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("read_all_subjects")
				.returningResultSet("subjects",  
						BeanPropertyRowMapper.newInstance(Subject.class));
		
		//delete Subject record
		this.deleteSubjectRecord  = new SimpleJdbcCall(dataSource).withProcedureName("delete_subject");
		
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Subject> view() {
		Map m = allSubjectRecords.execute(new HashMap<String, Object>(0));
		return (List) m.get("subjects");
	}

	@Override
	public void save(Subject subject) {
		jdbcTemplate.update(
					"{ call insert_record(?,?) }",
					 subject.getSubjectName(),
					 subject.getTeacherName()
				);
		
    }

	@Override
	public Subject get(int id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
		@SuppressWarnings("rawtypes")
		Map out  = subjectRecord.execute(in);
		Subject subject = new Subject();
		subject.setId(id);
		subject.setSubjectName((String) out.get("out_subject_name"));
		subject.setTeacherName((String) out.get("out_teacher_name"));
		return subject;
	}


	@Override
	public void delete(int id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
		deleteSubjectRecord.execute(in); 
		
	}


	@Override
	public void update(Subject subject) {
		jdbcTemplate.update(
				"{ call update_record(?,?, ?) }",
				 subject.getId(),
				 subject.getSubjectName(),
				 subject.getTeacherName()
			);
		
	}


}
