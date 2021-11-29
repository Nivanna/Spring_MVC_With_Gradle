package convertDateFomart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import convertDateFomart.model.DateFormat;

public class DateDAOImpl implements DateDAO {
	
	private JdbcTemplate jdbcTemplate;

	public DateDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<DateFormat> getListOfDate() {
	    List<DateFormat> listDate = jdbcTemplate.query("{call all_date_callingFunction}", new RowMapper<DateFormat>() {
	 
	        @Override
	        public DateFormat mapRow(ResultSet rs, int rowNum) throws SQLException {
	            DateFormat aDate = new DateFormat();
	            aDate.setId(rs.getInt("id"));
	            aDate.setDateresult(rs.getString("dateformat"));
	            return aDate;
	        }
	    });
	    return listDate;
	}

	@Override
	public void deleteDate(int id) {
		String sql = "DELETE FROM mydate WHERE id =?" ;
		jdbcTemplate.update(sql, id);
	}

	@Override
	public void addNewDate(DateFormat dateFormat) {
		String sql = "INSERT INTO mydate (dateformat)"
				+ "VALUES (?)";
        jdbcTemplate.update(sql, dateFormat.getDateconvertor());
		
	}

}
