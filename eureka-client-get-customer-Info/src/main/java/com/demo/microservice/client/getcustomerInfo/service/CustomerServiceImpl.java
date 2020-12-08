package com.demo.microservice.client.getcustomerInfo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.microservice.client.getcustomerInfo.deo.CustomerRepository;
import com.demo.microservice.client.getcustomerInfo.entity.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer dao
	private CustomerRepository customerDAO;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository thecustomerDAO) {
		customerDAO=thecustomerDAO;
	}
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.findAll();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerDAO.save(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		Customer theCustomer=null;
		Optional<Customer> result = customerDAO.findById(theId) ;
		
		if(result.isPresent()) {
			theCustomer = result.get();
			
		}else {
			throw new RuntimeException("Did not find employee id "+theId);
		}
		
		return theCustomer;
		
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDAO.deleteById(theId);
	}
}





