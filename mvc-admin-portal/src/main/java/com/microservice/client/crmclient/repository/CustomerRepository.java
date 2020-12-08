package com.microservice.client.crmclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.client.crmclient.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

