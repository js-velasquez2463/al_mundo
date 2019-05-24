package com.al_mundo.entities;

import java.util.Date;
import java.util.Random;

public class Call extends Thread{

	/**
	 * attributes
	 */
	private int duration;
	private long id;
	private boolean isRunning;
	private static int MIN_DURATION = 5;
	private static int MAX_DURATION = 10;

	/**
	 * constructor
	 */
	public Call(int id) {
		this.id = id;
		Random rand = new Random();
		this.duration = rand.nextInt((MAX_DURATION - MIN_DURATION) + 1) + MIN_DURATION;
		this.isRunning = true;
	}

	/**
	 * runs the call thread
	 */
	public void run() {
		System.out.println("Running call " + this.id + " duration: "+ this.duration + "mins");
		try {
			synchronized (this) {
				Thread.sleep(duration * 1000);
				this.setRunning(false);
				System.out.println("Call " +  this.id + " finished.");
				this.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Call " +  this.id + " interrupted.");
			this.setRunning(false);
		}
	}

	/**
	 * gets the call duration
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * sets the call duration
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public synchronized void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
