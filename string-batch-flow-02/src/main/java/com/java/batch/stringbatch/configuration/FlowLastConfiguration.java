package com.java.batch.stringbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowLastConfiguration {


	@Autowired
	private JobBuilderFactory  jobbuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Bean
	public Step mylastStep() {
		return stepbuilderfactory.get("mylastStep")
				.tasklet((contribution,chunkContext)->{
					System.out.println("Hello Workd mylastStep !!!");
				return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	   public Job flowLastJob(Flow flow) {
		   return jobbuilderfactory.get("flowLastJob")
				   .start(mylastStep())
				   .on("COMPLETED").to(flow)
				   .end()
				   .build();
				   
	   }


}
