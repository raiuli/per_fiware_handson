package se.ltu.pmc.fiware.wrapper;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextRequest;

public class FiwareWrapperQuery {
	String response="";
	public String createQueryContextRequest(List<String> entityNames,List<String> attributes){
		List<EntityId> entityIdLst = new ArrayList<>();
		for(String entityName:entityNames){
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
			entityIdLst.add(entityId);
		}
		QueryContextRequest qcr = new QueryContextRequest();
		qcr.setEntityIdList(entityIdLst);
		qcr.setAttributeList(attributes);
		return qcr.toJsonString();
		//return qcr.toString();
		
	}
}
