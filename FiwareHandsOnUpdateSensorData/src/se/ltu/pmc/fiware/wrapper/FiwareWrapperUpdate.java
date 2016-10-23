package se.ltu.pmc.fiware.wrapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;

import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateActionType;
import eu.neclab.iotplatform.ngsi.api.datamodel.UpdateContextRequest;

public class FiwareWrapperUpdate {
	public String createUpdaterequest(String entityName,String attributeName,String value){
		// creating entity Id
		EntityId entityId = new EntityId();
		entityId.setId(entityName);
		entityId.setIsPattern(false);
		URI type = null;
		try {
			type = new  URI("sensor");
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		entityId.setType(type);
		// creating attribute 
		ContextAttribute ca = new ContextAttribute();
		ca.setName(attributeName);
		ca.setContextValue(value);
		URI attrType = null;
		try {
			attrType = new  URI("float");
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		ca.setType(attrType);
		// creating Metadata 
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
		// adding Metadata to Attribute
		ca.setMetadata(cmdLst);
		List<ContextAttribute> crAttributelst = new ArrayList<>();
		crAttributelst.add(ca);
		// crating context Element
		 ContextElement ce = new ContextElement();
		 ce.setEntityId(entityId);
		 ce.setContextAttributeList(crAttributelst);
		 List<ContextElement> ceLst = new ArrayList<>();
		 ceLst.add(ce);
		 UpdateContextRequest ucr = new UpdateContextRequest();
		 ucr.setContextElement(ceLst);
		 ucr.setUpdateAction(UpdateActionType.APPEND);
		 
		return ucr.toJsonString();
		 //return ucr.toString();
		
	}
}
