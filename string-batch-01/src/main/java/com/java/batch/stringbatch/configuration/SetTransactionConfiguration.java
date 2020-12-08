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
public class SetTransactionConfiguration {

	
	@Autowired
	private JobBuilderFactory  jobbuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Bean
	public Step setp11() {
		return stepbuilderfactory.get("step11")
				.tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Hello Workd !!!");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	
	@Bean
	public Step setp12() {
		return stepbuilderfactory.get("step12")
				.tasklet((contribution,chunkContext)->{
					System.out.println("Hello Workd step12 !!!");
				return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step setp13() {
		return stepbuilderfactory.get("step13")
				.tasklet((contribution,chunkContext)->{
					System.out.println("Hello Workd step13 !!!");
				return RepeatStatus.FINISHED;
				}).build();
	}

	@Bean
   public Job helloWorldJob2() {
	   return jobbuilderfactory.get("NewSetTransactionConfig5")
			   .start(setp11())
			   .on("COMPLETED").to(setp12())
			  // .from(setp12()).on("COMPLETED").stopAndRestart(setp13())
			   //.from(setp12()).on("COMPLETED").fail()
			   .from(setp12()).on("COMPLETED").to(setp13())
			   .from(setp13()).end()
			   .build();
			   
   }
	


}
