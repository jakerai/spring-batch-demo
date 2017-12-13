package com.spring.springbatchdemo.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.spring.springbatchdemo.listener.JobCompletionNotificationListener;
import com.spring.springbatchdemo.model.Person;
import com.spring.springbatchdemo.steps.PersonItemProcessor;
import com.spring.springbatchdemo.tasklets.TaskletStep;

/**
 * @author VishalCRai
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
	private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private DataSource dataSource;

	@Autowired
	private TaskletStep taskletStep;
	
	
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
	public Job personJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("personJob").incrementer(new RunIdIncrementer()).listener(listener).start(step1())
				.next(step2()).build();
	}

	@Bean
	public ItemReader<Person> reader() {
		logger.info("read() invoked...");
		String sql = "select * from people where status=0";
		JdbcCursorItemReader<Person> jdbcReader = new JdbcCursorItemReader<>();
		jdbcReader.setDataSource(dataSource);
		jdbcReader.setSql(sql);
		jdbcReader.setRowMapper(new BeanPropertyRowMapper<>(Person.class));
		return jdbcReader;
	}

	@Bean
	public JdbcBatchItemWriter<Person> writer() {
		logger.info("writer()...invoked");
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
	
		writer.setSql(
				"INSERT INTO people_report (id,full_name, creation_date_time) VALUES (:id,:firstName,now())");
		writer.setDataSource(dataSource);
		
		// put your data into stepexecution context
		//ExecutionContext stepContext = this.stepExecution.getExecutionContext();
		//stepContext.put("id",);
		
		
		return writer;
	}
	
	


	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet(taskletStep).build();
	}

	
}
