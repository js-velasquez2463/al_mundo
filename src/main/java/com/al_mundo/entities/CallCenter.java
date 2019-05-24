package com.al_mundo.entities;

import java.util.ArrayList;

public class CallCenter extends Thread{

	/**
	 * attributes
	 */
	private Dispatcher dispatcher;
	private WaitingBuffer waitingBuffer;
	private boolean runningCallCenter;
	
	/**
	 * constants
	 */
	public final static String CALL_DISPATCHED = "Call dispatched";
	public final static String CALL_UNDISPATCHED = "Call dispatched";
	public final static String WAITING_CALL = "Call is waiting on buffer";
	

	/**
	 * constructor
	 */
	public CallCenter() {
		this.dispatcher = new Dispatcher();
		this.waitingBuffer = new WaitingBuffer(10);
		this.runningCallCenter = true;
	}
	
	/**
	 * processes a new call in the cal center
	 * @param newCall
	 * @return dispatched if the call is attended, waiting if it is holding on the waiting buffer
	 * or undispatched if it could not be processed
	 */
	public synchronized String processCall(Call newCall) {
		boolean callDispatched = dispatcher.dispatchCall(newCall);
		if (!callDispatched) {
			return waitingBuffer.addCall(newCall) ? WAITING_CALL : CALL_UNDISPATCHED;
		} else {
			return CALL_DISPATCHED;
		}
	}
	
	/**
	 * stops the call center
	 */
	public void stopCallCeter() {
		this.dispatcher.stopDispatcher();
		this.runningCallCenter= false;
	}
	
	/**
	 * gets the dispatcher class
	 * @return dispatcher
	 */
	public synchronized Dispatcher getDispatcher() {
		return this.dispatcher;
	}
	
	/**
	 * runs the call center thread
	 */
	public void run() {
		System.out.println("Receiving calls");
		try {
			dispatcher.startDispatcher();
			while(this.runningCallCenter) {
				if(waitingBuffer.getTotalCalls()>0) {
					int dispatcherCapacity = this.dispatcher.getTotalCapacity();
					int totalOfCalls = this.dispatcher.getTotalCalls();
					int redirectCalls = dispatcherCapacity - totalOfCalls;
					ArrayList<Call> callsToRedirect = waitingBuffer.getCalls(redirectCalls);
					for (int i = 0; i < callsToRedirect.size(); i++) {
						Call redirectCall= callsToRedirect.get(i);
						this.processCall(redirectCall);
					}
				}
				Thread.sleep(1000);
			}
			dispatcher.stopDispatcher();
		} catch (InterruptedException e) {
			e.printStackTrace();
			dispatcher.stopDispatcher();
			System.out.println("Call center interrupted");
		}
	}
}
