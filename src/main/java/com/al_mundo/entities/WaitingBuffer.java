package com.al_mundo.entities;

import java.util.ArrayList;


public class WaitingBuffer {

	/**
	 * attributes
	 */
	private ArrayList<Call> calls;
	private int capacity; 

	/**
	 * constructor
	 */
	public WaitingBuffer(int capacity) {
		this.capacity=capacity;
		this.calls=new ArrayList<Call>();
	}
	
	/**
	 * add a waiting call
	 * @param newCall
	 * @return true if it was able to add the call to the buffer
	 */
	public boolean addCall(Call newCall){
		if (this.calls.size() < capacity) {
			this.calls.add(newCall);
			System.out.println("Waiting call "+ newCall.getId());
			return true;
		} else {
			System.out.println(" Waiting Buffer is full");
			return false;
		}
	}
	
	/**
	 * get the total number of calls
	 * @return calls size
	 */
	public int getTotalCalls() {
		return this.calls.size();
	}
	
	/**
	 * get the first n waiting calls array
	 * @param numberOfCalls
	 * @return the first nth waiting calls
	 */
	public ArrayList<Call> getCalls(int numberOfCalls) {
		ArrayList<Call> removeCalls = new ArrayList<Call>();
		for (int i = 0; i < this.calls.size() && i < numberOfCalls; i++) {
			Call removeCall = this.calls.get(0);
			removeCalls.add(removeCall);
			this.calls.remove(removeCall);
		}
		return removeCalls;
	}
}
