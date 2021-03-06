package com.java.batch.stringbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	
	@Autowired
	private JobBuilderFactory  jobbuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Bean
	public Step setp1() {
		return stepbuilderfactory.get("step1")
				.tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("JobConfiguration Hello Workd !!!");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
   public Job helloWorldJob() {
	   return jobbuilderfactory.get("helloWorkJob3")
			   .start(setp1()).build();
			   
   }
	
}
