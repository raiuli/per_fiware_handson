package se.ltu.pmc.fiwareHandson;

import java.io.IOException;


import se.ltu.pmc.fiware.wrapper.FiwareWrapperUpdate;
import se.ltu.pmc.fiware.wrapper.HttpHandler;



public class HandsOnUpdateSensorData {
	public static void main(String[] args) {
		// The sensor values that will be updated 
		String entityName = "TempeSensor_S0";
		String attribute = "NH3";
		String value = "279.0";
		
		// The url of IoTBroker
		String updateUrl="http://ip_address/ngsi10/updateContext";
		
		//Creating Update Request
		FiwareWrapperUpdate fwq = new FiwareWrapperUpdate();
		String request=fwq.createUpdaterequest(entityName, attribute,value);
		System.out.println("------------->Request = "+request);
		
		//Sending Update Request to IoT Broker and getting response back
		HttpHandler httpHandler = new HttpHandler();
		String response="";
		try {
			response=httpHandler.sendrequest(updateUrl,request);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("<-------------Response = "+response);
	}
	
}
