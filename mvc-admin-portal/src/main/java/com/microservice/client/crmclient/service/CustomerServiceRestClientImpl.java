package com.microservice.client.crmclient.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.microservice.client.crmclient.model.Customer;
import com.microservice.client.crmclient.repository.CustomerRepository;


@Service
public class CustomerServiceRestClientImpl implements CustomerService {
	
	

	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// need to inject customer dao
	@Autowired
	private CustomerRepository customerDAO;
	

	/*
	 * @Autowired public CustomerServiceRestClientImpl(RestTemplate theRestTemplate,
	 * 
	 * @Value("${crm.rest.url}") String
	 * theCustomerInfo,@Value("${crm.rest.modify.url}") String theModifyCustInfoUrl)
	 * { restTemplate = theRestTemplate; getCustomerInfoURL = theCustomerInfo;
	 * modifyCustomerInfoURL= theModifyCustInfoUrl;
	 * logger.info("Loaded property:  crm.rest.url=" + theCustomerInfo); }
	 */
	
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers =null;
		
	
			customers= customerDAO.findAll();

		return customers;
	}

	@Override
	public Customer getCustomer(int theId) {

		Customer theCustomer =  null;
				
			Optional<Customer> result = customerDAO.findById(theId) ;
			
			if(result.isPresent()) {
				theCustomer = result.get();
				
			}else {
				throw new RuntimeException("Did not find employee id "+theId);
			}
		
		
		return theCustomer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		int employeeId = theCustomer.getId();


			
			if (employeeId == 0) {
				// add employee
				customerDAO.save(theCustomer);	
			
			} else {
				// update employee
				customerDAO.save(theCustomer);
			}
			

		logger.info("in saveCustomer(): success");	
	}

	@Override
	public void deleteCustomer(int theId) {


			customerDAO.deleteById(theId);

		logger.info("in deleteCustomer(): deleted customer theId=" + theId);
	}

}
