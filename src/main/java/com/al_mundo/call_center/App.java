package com.al_mundo.call_center;

import com.al_mundo.entities.Call;
import com.al_mundo.entities.CallCenter;

/**
 * Application main class
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
        	CallCenter callCenter = new CallCenter();
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
	        callCenter.processCall(call1);
			callCenter.processCall(call2);
	        callCenter.processCall(call3);
	        callCenter.processCall(call4);
	        callCenter.processCall(call5);
	        callCenter.processCall(call6);
	        callCenter.processCall(call7);
	        callCenter.processCall(call8);
	        callCenter.processCall(call9);
	        callCenter.processCall(call10);
	        callCenter.processCall(call11);
	        Thread.sleep(12000);
	        callCenter.stopCallCeter();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
