package com.demo.microservice.client.getcustomerInfo.deo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.microservice.client.getcustomerInfo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

