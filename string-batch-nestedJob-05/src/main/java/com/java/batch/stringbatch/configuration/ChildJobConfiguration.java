package com.java.batch.stringbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChildJobConfiguration {

	@Autowired
	private JobBuilderFactory  jobbuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;

	@Bean 
	public  Step childStep() {
	    return stepbuilderfactory.get("childStep")
	    		.tasklet((contribution,chunkContext)->{
	    			System.out.println("This is the childStep tasklet");
	    			return RepeatStatus.FINISHED;
	    		}).build();
		
	}
	
	@Bean
	public Job job() {
		return jobbuilderfactory.get("childJob3")
				.start(childStep())
				.build();
		
	}
	
}
