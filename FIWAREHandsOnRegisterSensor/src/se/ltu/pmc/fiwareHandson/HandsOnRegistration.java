package se.ltu.pmc.fiwareHandson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.ltu.pmc.fiware.wrapper.FiwareWrapper;
import se.ltu.pmc.fiware.wrapper.HttpHandler;

public class HandsOnRegistration {

	public static void main(String[] args) {
		//The URL of IoTDiscovery . It is registering the sensor
		String registrationUrl="http://ip_address/ngsi9/registerContext";
		//The URL of device which will provide sensor data. In our case the SRSS gateway
		String providerUrl="http://ip_address/se.ltu.ssr.webapp/rest/FiwareJSON/";
		
		//The  entity name EnviormentalSensor_S0
		List<String> entityNames = new ArrayList<>();
		entityNames.add("EnviormentalSensor_S0");
		//The Attribute  named NH3
		List<String> attributes = new ArrayList<>();
		attributes.add("NH3");
		
		// Creating RegisterContextRequest  
		FiwareWrapper fw = new FiwareWrapper();
		String request=fw.creatRegisterSensorRequest(entityNames, attributes, providerUrl);
		System.out.println("Sending Request-------------> = "+request);
		//Sending RegisterContextRequest to IoTDiscovery and getting response back
		HttpHandler httpHandler = new HttpHandler();
		String response="";
		try {
			response=httpHandler.sendrequest(registrationUrl,request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Receivied Response<------------- = "+response);

	}

}
