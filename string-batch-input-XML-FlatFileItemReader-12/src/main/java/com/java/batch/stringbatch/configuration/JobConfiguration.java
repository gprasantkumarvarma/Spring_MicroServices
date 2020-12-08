package com.java.batch.stringbatch.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.java.batch.stringbatch.configuration.entity.Customer;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobbuilderfactory;

	@Autowired
	private StepBuilderFactory stepbuilderfactory;

	@Bean
	public StaxEventItemReader<Customer> staxeventitemReader() {

		XStreamMarshaller unmarshaller = new XStreamMarshaller();

		Map<String, Class> aliases = new HashMap<>();
		aliases.put("customer", Customer.class);

		unmarshaller.setAliases(aliases);

		StaxEventItemReader<Customer> reader = new StaxEventItemReader<>();
		reader.setResource(new ClassPathResource("/data/customer.xml"));
		reader.setFragmentRootElementName("customer");
		reader.setUnmarshaller(unmarshaller);
		return reader;

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
		return stepbuilderfactory.get("step1").<Customer, Customer>chunk(10).reader(staxeventitemReader())
				.writer(customerItemWriter()).build();
	}

	@Bean
	public Job xmlJob() {

		return jobbuilderfactory.get("xmlJob").start(step1()).build();
	}

}
