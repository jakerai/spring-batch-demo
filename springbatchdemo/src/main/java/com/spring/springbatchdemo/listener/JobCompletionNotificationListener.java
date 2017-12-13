package com.spring.springbatchdemo.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.spring.springbatchdemo.model.Person;
/**
 * @author VishalCRai
 *
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("afterJob(JobExecution jobExecution)...invoked");
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info("!!! JOB FINISHED! Time to verify the results");

			List<Person> results = jdbcTemplate.query("SELECT * FROM people",
					new RowMapper<Person>() {
						@Override
						public Person mapRow(ResultSet rs, int row) throws SQLException {
							return new Person(rs.getLong(1),rs.getString(2), rs.getString(3),rs.getInt(4),rs.getDate(5));
						}
					});

			for (Person person : results) {
				logger.info("Found <" + person + "> in the database.");
			}

		}
	}
	
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
	logger.info("before job execution XXXXXXXXXXXXX");
	}
	

}
