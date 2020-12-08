package com.stackroute.keepnote.eurekaregistry;

public class InterviewQuestion {
	public static void main(String[] args) throws Exception {
		String s1 = new String("Prasant");
		String s2 = new String("Prasant");
		System.out.println(s1.equals(s2));
		
		StringBuffer s3 = new StringBuffer("prasant");
		StringBuffer s4 = new StringBuffer("prasant");
		System.out.println(s3.equals(s4));
		
		
		StringBuilder s5 = new StringBuilder("prasant");
		StringBuilder s6 = new StringBuilder("prasant");
		System.out.println(s5.equals(s6));
		try {
			
			
		}catch(Exception e) {
			
			throw new  Exception();
			
		}finally {
			System.out.println("Finally");
		}
		
	}

}
