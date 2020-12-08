package com.microservice.client.crmclient.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.client.crmclient.model.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class CustomerServiceRestClientImpl implements CustomerService {
	
	private RestTemplate restTemplate;

	private String getCustomerInfoURL;
	
	private String modifyCustomerInfoURL;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public CustomerServiceRestClientImpl(RestTemplate theRestTemplate, 
										@Value("${crm.rest.url}") String theCustomerInfo,@Value("${crm.rest.modify.url}") String theModifyCustInfoUrl) {
		restTemplate = theRestTemplate;
		getCustomerInfoURL = theCustomerInfo;
		modifyCustomerInfoURL=	theModifyCustInfoUrl;	
		logger.info("Loaded property:  crm.rest.url=" + theCustomerInfo);
	}
	
	@Override
	public List<Customer> getCustomers() {
		
		logger.info("in getCustomers(): Calling REST API " + getCustomerInfoURL);

		// make REST call
		ResponseEntity<List<Customer>> responseEntity = 
											restTemplate.exchange(getCustomerInfoURL, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<Customer>>() {});

		// get the list of customers from response
		List<Customer> customers = responseEntity.getBody();

		logger.info("in getCustomers(): customers" + customers);
		
		return customers;
	}

	@Override
	public Customer getCustomer(int theId) {

		logger.info("in getCustomer(): Calling REST API " + getCustomerInfoURL);

		// make REST call
		Customer theCustomer = 
				restTemplate.getForObject(getCustomerInfoURL + "/" + theId, 
										  Customer.class);

		logger.info("in saveCustomer(): theCustomer=" + theCustomer);
		
		return theCustomer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		logger.info("in saveCustomer(): Calling REST API " + getCustomerInfoURL);
		
		int employeeId = theCustomer.getId();

		// make REST call
		if (employeeId == 0) {
			// add employee
			restTemplate.postForEntity(modifyCustomerInfoURL, theCustomer, String.class);			
		
		} else {
			// update employee
			restTemplate.put(modifyCustomerInfoURL, theCustomer);
		}

		logger.info("in saveCustomer(): success");	
	}

	@Override
	public void deleteCustomer(int theId) {

		logger.info("in deleteCustomer(): Calling REST API " + getCustomerInfoURL);

		// make REST call
		restTemplate.delete(modifyCustomerInfoURL + "/" + theId);

		logger.info("in deleteCustomer(): deleted customer theId=" + theId);
	}

}
