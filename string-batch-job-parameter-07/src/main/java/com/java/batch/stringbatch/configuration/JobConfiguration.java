package com.java.batch.stringbatch.configuration;

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

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobbuilderfactory;

	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Bean
	@StepScope /*-*/
	public Tasklet helloWorldTasklet(@Value("#{jobParameters['message']}")String message) {
		return (stepContribution, chunkContext)->{
			System.out.println(message);
			return RepeatStatus.FINISHED;
		};
	}
	
	@Bean
	public Step step1() {
		return stepbuilderfactory.get("step1")
				.tasklet(helloWorldTasklet(null))
				.build();
	}
	
	
	@Bean
	public Job jobParametersJob() {
		
		return jobbuilderfactory.get("jobParametersJob23")
				.start(step1())
				.build();
	}
	
	
	
}
