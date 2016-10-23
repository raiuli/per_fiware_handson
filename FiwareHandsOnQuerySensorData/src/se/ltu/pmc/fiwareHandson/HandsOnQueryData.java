package se.ltu.pmc.fiwareHandson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import se.ltu.pmc.fiware.wrapper.FiwareWrapperQuery;
import se.ltu.pmc.fiware.wrapper.HttpHandler;

public class HandsOnQueryData {
	public static void main(String[] args) {
		
		// Query for Entity  "EnviormentalSensor_S0" and attribute "NH3"
		List<String> entityNames = new ArrayList<>();
		entityNames.add("TempeSensor_S0");
		List<String> attributes = new ArrayList<>();
		attributes.add("NH3");
		
		// URL of Publish/Subscribe	Broker GE
		String queyUrl="http://ip_address/v1/queryContext";
		
		// Creating QueryContextRequest 
		FiwareWrapperQuery fwq = new FiwareWrapperQuery();
		String request=fwq.createQueryContextRequest(entityNames, attributes);
		System.out.println("Sending Request-------------> ="+request);
		String response="";
		//Sending QueryContextRequest to Publish/Subscribe	Broker GE and getting response back
		 HttpHandler httpHandler= new HttpHandler();
		 try {
			 response=httpHandler.sendrequest(queyUrl,  request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 System.out.println("Receivied Response<------------- = "+response); 
	}
}
