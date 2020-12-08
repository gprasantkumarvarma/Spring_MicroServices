package com.mesaging.integration.MQProcessing;

import java.io.Serializable;

public class SimpleMessage implements Serializable{

	
	transient private String name;
	private String discription;
	
	public SimpleMessage() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	@Override
	public String toString() {
		return "SimpleMessage [name=" + name + ", discription=" + discription + "]";
	}
	
	
	
}
