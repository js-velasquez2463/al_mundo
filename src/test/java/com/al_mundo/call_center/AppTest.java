package com.al_mundo.call_center;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.al_mundo.entities.Call;
import com.al_mundo.entities.CallCenter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests
 */
public class AppTest 
    extends TestCase
{
	
	public final static String CALL_DISPATCHED = CallCenter.CALL_DISPATCHED;
	public final static String CALL_UNDISPATCHED = CallCenter.CALL_UNDISPATCHED;
	public final static String WAITING_CALL = CallCenter.WAITING_CALL;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Tests 10 simultaneous calls and 1 waiting call is saved in a waiting buffer
     * while all the employees are busy
     */
   public void testSimultaneousCalls()
    {
    	CallCenter callCenter = new CallCenter();
        try {
        	callCenter.start();
	    	Call call1 = new Call(1);
	        Call call2 = new Call(2);
	        Call call3 = new Call(3);
	        Call call4 = new Call(4);
	        Call call5 = new Call(5);
	        Call call6 = new Call(6);
	        Call call7 = new Call(7);
	        Call call8 = new Call(8);
	        Call call9 = new Call(9);
	        Call call10 = new Call(10);
	        Call call11 = new Call(11);
			Thread.sleep(1000);
			assertEquals(callCenter.processCall(call1), CALL_DISPATCHED);
			assertEquals(callCenter.processCall(call2), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call3), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call4), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call5), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call6), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call7), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call8), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call9), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call10), CALL_DISPATCHED);
	        assertEquals(callCenter.processCall(call11), WAITING_CALL);
	        Thread.sleep(1000);
	        int totalCalls = callCenter.getDispatcher().getTotalCalls();
	        assertEquals(totalCalls, 10);
	        Thread.sleep(12000);
	        System.out.println("Finished Test 1");
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			callCenter.stopCallCeter();
		}
    }
    
    /**
     * Tests 10 simultaneous calls and the duration of each call
     */
    public void testCallsDuration()
    {
    	CallCenter callCenter = new CallCenter();
        try {
        	callCenter.start();
        	HashMap<Integer, Integer> minutesHash = new HashMap<Integer, Integer>();
        	minutesHash.put(5, 0);
        	minutesHash.put(6, 0);
        	minutesHash.put(7, 0);
        	minutesHash.put(8, 0);
        	minutesHash.put(9, 0);
        	minutesHash.put(10, 0);
	    	for (int i = 0; i < 10; i++) {
				Call newCall = new Call(i);
				minutesHash.put(newCall.getDuration(),
						minutesHash.get(newCall.getDuration()) + 1);
				callCenter.processCall(newCall);
			}
			Thread.sleep(5100);
			int activeCalls = 10;
			for (int i = 5; i <= 10; i++) {
				int callCenterCalls = callCenter.getDispatcher().getTotalCalls();
				int callsPerMinute = minutesHash.get(i);
				activeCalls -= callsPerMinute;
				assertEquals(callCenterCalls, activeCalls);
				Thread.sleep(1000);
			}
	        System.out.println("Finished Test 2");
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			callCenter.stopCallCeter();
		}
    }
}
