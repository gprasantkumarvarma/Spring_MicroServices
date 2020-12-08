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
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.java.batch.stringbatch.configuration.entity.Customer;
import com.java.batch.stringbatch.configuration.entity.CustomerFieldSetMapper;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobbuilderfactory;

	@Autowired
	private StepBuilderFactory stepbuilderfactory;


	@Bean
	@StepScope
	public StatefulItemReader itemReader() {
		List<String> items = new ArrayList<>(100);
		
		for (int i = 0; i <= 100; i++) {
			items.add(String.valueOf(i));
		}
		
	return	new StatefulItemReader(items);
	}
	
	

	
	@Bean
	public ItemWriter<Customer> customerItemWriter() {
		return items -> {
			for (Customer item : items) {
				System.out.println(item.toString());
			}
		};
	}

	@Bean
	public Step step1() {
		return stepbuilderfactory.get("step1")
				.<Customer, Customer>chunk(10)
				.reader(multiResourceItemReader())
				.writer(customerItemWriter()).build();
	}

	@Bean
	public Job multiSourceflatFileJob() {

		return jobbuilderfactory.get("multiSourceflatFileJob").start(step1()).build();
	}

}
