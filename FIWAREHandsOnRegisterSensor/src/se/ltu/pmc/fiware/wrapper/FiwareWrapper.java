package se.ltu.pmc.fiware.wrapper;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.RegisterContextRequest;

public class FiwareWrapper {
 public String creatRegisterSensorRequest(List<String> EntityNames,List<String> Attributes,String SensorUrl){
	//Creating EntityID
	List<EntityId> EntityIdLst = new ArrayList<>();
	for(String EntityName:EntityNames){
		EntityId entityId = new EntityId();
		entityId.setId(EntityName);
		entityId.setIsPattern(false);
		URI type = null;
		try {
			type = new  URI("sensor");
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		entityId.setType(type);
		EntityIdLst.add(entityId);
	}
	
	//Creating Attributes
	List<ContextRegistrationAttribute> crAttributelst = new ArrayList<>();
	for(String Attribute:Attributes){
		ContextRegistrationAttribute crAttribute = new ContextRegistrationAttribute();
		crAttribute.setName(Attribute);
		URI type = null;
		try {
			type = new  URI("float");
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		crAttribute.setType(type);
		crAttribute.setIsDomain(false);
		//Creating Metadata
		List<ContextMetadata> cmdLst = new ArrayList<>();
		ContextMetadata cmd = new ContextMetadata();
		cmd.setName("unit");
		cmd.setValue("ppm");
		try {
			type = new  URI("string");
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		cmd.setType(type);
		cmdLst.add(cmd);
		crAttribute.setMetaData(cmdLst);
		crAttributelst.add(crAttribute);
	}

	URI providingApplication= null;
	try {
		providingApplication = new  URI(SensorUrl);
	} catch (URISyntaxException e) {

		e.printStackTrace();
	}
	//Creating Context Registration
	ContextRegistration cr= new  ContextRegistration();
	cr.setListEntityId(EntityIdLst);
	cr.setListContextRegistrationAttribute(crAttributelst);
	cr.setProvidingApplication(providingApplication);
	
	
	
	List<ContextRegistration> crLst = new ArrayList<>();
	crLst.add(cr);
	// Setting  duration of one month registraion. How long the sensor will be available
	Duration duration=null;
	try {
		duration = DatatypeFactory.newInstance().newDuration("P1M");
	} catch (DatatypeConfigurationException e) {
		
		e.printStackTrace();
	}
	//Creating RegisterContextRequest
	RegisterContextRequest rcr = new RegisterContextRequest();
	rcr.setContextRegistrationList(crLst);
	rcr.setDuration(duration);
	// Setting up registrarion ID
	rcr.setRegistrationId("1");
	 return cr.toJsonString();
	 //return rcr.toString();
 } 
}
