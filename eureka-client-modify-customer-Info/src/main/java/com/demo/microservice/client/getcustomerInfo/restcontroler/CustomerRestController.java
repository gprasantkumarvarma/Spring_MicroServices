package com.demo.microservice.client.getcustomerInfo.restcontroler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservice.client.getcustomerInfo.entity.Customer;
import com.demo.microservice.client.getcustomerInfo.service.CustomerService;


@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the customerService
	@Autowired
	private CustomerService customerservice;

	// add the mapping for GET /customers
	@GetMapping("/customers")
	public List<com.demo.microservice.client.getcustomerInfo.entity.Customer> getCustomers() {

		return customerservice.getCustomers();

	}

	// add mapping for GET /customers/{customerID}
	@GetMapping("/customers/{customerID}")
	public Customer getCustomer(@PathVariable int customerID) {

		Customer theCustomer = customerservice.getCustomer(customerID);

		if (theCustomer == null) {
			throw new CustomerNotFountException("Customer id not found - " + customerID);
		}

		return theCustomer;
	}

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		// also just in case the pass an id in JSON... set id to 0
		// this is force a save of new item...insted of updating
		theCustomer.setId(0);// if the id is 0 the DEO will insert the new customer.(hibernet will check if
								// the id is null or empty(0) then it will insert or else update the record)

		customerservice.saveCustomer(theCustomer);

		return theCustomer;
	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {

		customerservice.saveCustomer(theCustomer);

		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerID}")
	public String deleteCustomer(@PathVariable int customerID) {
		
		Customer tempCustomer = customerservice.getCustomer(customerID);
		
		if(tempCustomer == null) {
			
			throw new CustomerNotFountException("Customer not fount -"+customerID);
			
		}
		
		customerservice.deleteCustomer(customerID);
		
		return "Delected  Customer ID ::"+customerID;
	}

}
