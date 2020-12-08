package com.microservice.client.crmclient.service;

import java.util.List;

import com.microservice.client.crmclient.model.Customer;



public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
