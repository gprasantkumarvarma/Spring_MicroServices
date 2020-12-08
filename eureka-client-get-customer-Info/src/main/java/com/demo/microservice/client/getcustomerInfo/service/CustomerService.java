package com.demo.microservice.client.getcustomerInfo.service;

import java.util.List;

import com.demo.microservice.client.getcustomerInfo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
