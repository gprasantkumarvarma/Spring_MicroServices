package com.java.batch.stringbatch.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.batch.stringbatch.configuration.reader.StatelessItemReader;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobbuilderfactory;

	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Bean 
	public StatelessItemReader statelessitemreader() {
		List<String> data = new ArrayList<String>(3);
		
		data.add("one");
		data.add("two");
		data.add("three");
		
		return new StatelessItemReader(data);
		
	}
	
	

	@Bean
	public Step step1() {
		return stepbuilderfactory.get("step1")
				.<String,String>chunk(2)
				.reader(statelessitemreader())
				.writer(list-> {
					for (String curItem : list) {
					System.out.println("curItem :: "+curItem);	
					}
				})
				.build();
	}
	
	
	@Bean
	public Job jobParametersJob() {
		
		return jobbuilderfactory.get("interfacesJob5")
				.start(step1())
				.build();
	}
	
	
	
}
