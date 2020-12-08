package com.java.batch.stringbatch.configuration;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class FlowConfiguration {
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Bean
	public Step setp11() {
		return stepbuilderfactory.get("stepFlow1")
				.tasklet((contribution,chunkContext)->{
					System.out.println("Hello Workd stepFlow1 !!!");
				return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step setp12() {
		return stepbuilderfactory.get("stepFlow2")
				.tasklet((contribution,chunkContext)->{
					System.out.println("Hello Workd stepFlow2 !!!");
				return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step setp13() {
		return stepbuilderfactory.get("stepFlow3")
				.tasklet((contribution,chunkContext)->{
					System.out.println("Hello Workd stepFlow3 !!!");
				return RepeatStatus.FINISHED;
				}).build();
	}
	
	/*- builder flow*/
	@Bean
	public Flow flowBuild() {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("myflow");
		flowBuilder.start(setp11())
		.next(setp12())
		.next(setp13())
		.end();
		
		return flowBuilder.build();
		
	}
	
	

}
