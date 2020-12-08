package com.java.batch.stringbatch.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.batch.stringbatch.configuration.entity.Customer;
import com.java.batch.stringbatch.configuration.entity.CustomerRowMapper;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobbuilderfactory;

	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Autowired
	public DataSource dataSource;
	

	/*-
	  CursorItemReader is not thread safe
	   
	  @Bean 
	  
	  public JdbcCursorItemReader<Customer> cursiorItemReader(){
	  
	  JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<Customer>();
	  reader.setSql("select id, first_name, last_name, email from customer order by id desc"); 
	  reader.setDataSource(this.dataSource); reader.setRowMapper(new CustomerRowMapper());
      return reader;
	 
	  }
	 */
	@Bean 
	public JdbcPagingItemReader<Customer> pagingItemReader(){
		JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<Customer>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(10);
		reader.setRowMapper(new CustomerRowMapper());
		
		MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("id, first_name, last_name, email");
		queryProvider.setFromClause("from customer");
		
		Map<String, Order> sortkey= new HashMap<>(1);
		sortkey.put("id", Order.ASCENDING);
		
		queryProvider.setSortKeys(sortkey);
		reader.setQueryProvider(queryProvider);
		return reader;
	}
	
	
	
	
	
	@Bean
	public ItemWriter<Customer> customerItemWriter(){
		return items ->{
			for(Customer item: items) {
				System.out.println(item.toString());
			}
		};
	}
	
	
	
	

	@Bean
	public Step step1() {
		return stepbuilderfactory.get("step1DB")
				.<Customer,Customer>chunk(10)
				//.reader(cursiorItemReader())
				.reader(pagingItemReader())
				.writer(customerItemWriter())
				.build();
	}
	
	
	@Bean
	public Job jbdcJob() {
		
		return jobbuilderfactory.get("jbdcJob2")
				.start(step1())
				.build();
	}
	
	
	
}
