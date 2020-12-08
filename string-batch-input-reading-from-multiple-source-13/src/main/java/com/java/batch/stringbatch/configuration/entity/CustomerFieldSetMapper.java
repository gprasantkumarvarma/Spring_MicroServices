package com.java.batch.stringbatch.configuration.entity;


import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer>{


	@Override
	public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
		
		return new Customer(fieldSet.readInt("id"), fieldSet.readString("firstname"),
				fieldSet.readString("lastname"), fieldSet.readString("email"));
	}

}
