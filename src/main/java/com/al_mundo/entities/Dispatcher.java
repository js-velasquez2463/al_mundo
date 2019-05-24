package com.al_mundo.entities;

public class Dispatcher {
	private EmployeeBuffer operators;
	private EmployeeBuffer supervisors;
	private EmployeeBuffer directors;

	/**
	 * constructor
	 */
	public Dispatcher() {
		this.operators = new EmployeeBuffer(5, "Operators");
		this.supervisors = new EmployeeBuffer(3, "Supervisors");
		this.directors = new EmployeeBuffer(2, "Directors");
	}
	
	/**
	 * starts the dispatcher
	 */
	public void startDispatcher() {
		this.operators.start();
		this.supervisors.start();
		this.directors.start();
	}
	
	/**
	 * stops the dispatcher
	 */
	public void stopDispatcher() {
		this.operators.setListenCalls(false);
		this.supervisors.setListenCalls(false);
		this.directors.setListenCalls(false);
	}
	
	/**
	 * dispatch a new call
	 * @param newCall dispatch a call to the available buffer of employees
	 * @return true if the call was dispatched
	 */
	public synchronized boolean dispatchCall(Call newCall) {
		boolean callDispatched= false;
		boolean operatorAttendedCall = this.operators.addCall(newCall);
		if (operatorAttendedCall) {
			callDispatched=true;
		} else {
			boolean supervisorAttendedCall = this.supervisors.addCall(newCall);
			if(supervisorAttendedCall)	{
				callDispatched=true;
			}else {
				return this.directors.addCall(newCall);
			}
		}
		return callDispatched;
	}
	
	/**
	 * gets the total quantity of calls being attended
	 * @return total calls attended
	 */
	public synchronized int getTotalCalls() {
		return this.operators.bufferCalls() + this.supervisors.bufferCalls() 
		  + this.directors.bufferCalls();
	}
	
	/**
	 * gets the total capacity of the employees buffers
	 * @return total capacity
	 */
	public synchronized int getTotalCapacity() {
		return this.operators.getCapacity() + this.supervisors.getCapacity() 
		  + this.directors.getCapacity();
	}
}
