package com.al_mundo.entities;

import java.util.ArrayList;
import java.util.Date;


public class EmployeeBuffer extends Thread{

	/**
	 * attributes
	 */
	private ArrayList<Call> calls;
	private boolean listenCalls;
	private String type;
	private int capacity; 

	/**
	 * constructor
	 */
	public EmployeeBuffer(int capacity, String type) {
		this.capacity=capacity;
		this.type = type;
		this.calls=new ArrayList<Call>();
		listenCalls= true;
	}
	
	/**
	 * runs the buffer thread
	 */
	public void run() {
		System.out.println("Listening calls "+ type);
		while(listenCalls) {
			try {
				for (int i = 0; i < this.calls.size(); i++) {
					Call call = this.calls.get(i);
					if (!call.isRunning()) {
						this.calls.remove(call);
						System.out.println("Buffer "+ this.type + " now has "+ this.calls.size() + " calls"+"- "+ new Date() );
					}
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * the number of calls in the employee buffer
	 * @return calls array size
	 */
	public synchronized int bufferCalls(){
		return calls.size();
	}

	/**
	 * adds a call to the buffer
	 * @param newCall
	 * @return true if it was able to add the call to the employee buffer
	 */
	public synchronized boolean addCall(Call newCall){
		if (this.calls.size() < capacity) {
			this.calls.add(newCall);
			newCall.start();
			System.out.println("Buffer "+ this.type + " has "+ this.calls.size() + " calls" );
			return true;
		} else {
			System.out.println("Buffer "+ this.type +" is full");
			return false;
		}
	}
	
	/**
	 * the buffer listening calls variable
	 * @return true if the buffer is listening calls
	 */
	public synchronized boolean isListenCalls() {
		return listenCalls;
	}

	/**
	 * set the variable of listening calls
	 * @param listenCalls
	 */
	public synchronized void setListenCalls(boolean listenCalls) {
		this.listenCalls = listenCalls;
	}
	
	/**
	 * returns the capacity of the buffer
	 * @return buffer capacity
	 */
	public synchronized int getCapacity() {
		return this.capacity;
	}
}
