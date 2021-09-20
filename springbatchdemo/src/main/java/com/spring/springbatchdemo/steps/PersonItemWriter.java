package com.spring.springbatchdemo.steps;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.springbatchdemo.model.Person;

/**
 * @author Vishal Rai
 *
 */
public class PersonItemWriter implements ItemWriter<Person> {
	private static final Logger logger = LoggerFactory.getLogger(PersonItemWriter.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void write(List<? extends Person> items) throws Exception {
		logger.info("write(List<? extends Person> items size:"+items.size()+")...invoked");
		
		jdbcTemplate.batchUpdate("INSERT INTO people_report (id,full_name, creation_date_time) VALUES (?,?,now())",new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Person person = items.get(i);
			    ps.setLong(1, person.getId());
			    ps.setString(2,person.getFirstName()+" "+person.getLastName());
				
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return items.size();
			}
		});
	}

}
