package com.al_mundo.exceptions;

public class EmployeeFullException extends Exception{

	/**
	 * exception thrown when the employee is full
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeFullException(){
		System.out.println("Employee cannot receive a call");
	}
}
