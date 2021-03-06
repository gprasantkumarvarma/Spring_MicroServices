package com.java.batch.stringbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class BatchConfiguration {
	
	@Autowired
	private JobBuilderFactory  jobbuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;

	@Bean 
	public  Step startStep() {
	    return stepbuilderfactory.get("startStep")
	    		.tasklet((contribution,chunkContext)->{
	    			System.out.println("This is the start tasklet");
	    			return RepeatStatus.FINISHED;
	    		}).build();
		
	}
	
	@Bean 
	public  Step evenStep() {
	    return stepbuilderfactory.get("evenStep")
	    		.tasklet((contribution,chunkContext)->{
	    			System.out.println("This is the even tasklet");
	    			return RepeatStatus.FINISHED;
	    		}).build();
		
	}
	
	@Bean 
	public  Step oddStep() {
	    return stepbuilderfactory.get("oddStep")
	    		.tasklet((contribution,chunkContext)->{
	    			System.out.println("This is the odd tasklet");
	    			return RepeatStatus.FINISHED;
	    		}).build();
		
	}
	
	
	@Bean
	public JobExecutionDecider decider() {
		return new OddDecider();
	}
	
	
	@Bean
	public Job job() {
		return jobbuilderfactory.get("job3")
				.start(startStep())
				.next(decider())
				.from(decider()).on("ODD").to(oddStep())
				.from(decider()).on("EVEN").to(evenStep())
				.from(oddStep()).on("*").to(decider())
				.from(decider()).on("ODD").to(oddStep())
				.from(decider()).on("EVEN").to(evenStep())
				.end()
				.build();
		
	}
	
	
	
	
	
	public static class CountingTasklet implements Tasklet {

		@Override
		public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
			System.out.println(String.format("\"%s has been exicuted on thread %s\"", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
			return RepeatStatus.FINISHED;
		}

	}
	
}
