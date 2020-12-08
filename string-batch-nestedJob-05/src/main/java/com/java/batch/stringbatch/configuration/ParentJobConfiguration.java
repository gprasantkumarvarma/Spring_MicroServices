package com.java.batch.stringbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ParentJobConfiguration {
	
	@Autowired
	private JobBuilderFactory  jobbuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Autowired
	private Job childJob;

	@Autowired
	private JobLauncher jobLauncher;
	
	@Bean 
	public  Step parentStep() {
	    return stepbuilderfactory.get("parentStep")
	    		.tasklet((contribution,chunkContext)->{
	    			System.out.println("This is the parentStep tasklet");
	    			return RepeatStatus.FINISHED;
	    		}).build();
		
	}
	
	@Bean
	public Job parentJob(JobRepository repo, PlatformTransactionManager tran) {
		Step childJobStep = new JobStepBuilder(new StepBuilder("childJobStep"))
				.job(childJob)
				.launcher(jobLauncher)
				.repository(repo)
				.transactionManager(tran)
				.build();
		
		return jobbuilderfactory.get("parentJob3")
				.start(parentStep())
				.next(childJobStep)
		        .build();
		
		
	}

}
