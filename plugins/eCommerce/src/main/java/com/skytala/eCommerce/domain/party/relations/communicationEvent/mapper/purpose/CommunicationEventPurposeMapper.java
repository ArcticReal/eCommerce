package com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.purpose;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.purpose.CommunicationEventPurpose;

public class CommunicationEventPurposeMapper  {


	public static Map<String, Object> map(CommunicationEventPurpose communicationeventpurpose) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(communicationeventpurpose.getCommunicationEventPrpTypId() != null ){
			returnVal.put("communicationEventPrpTypId",communicationeventpurpose.getCommunicationEventPrpTypId());
}

		if(communicationeventpurpose.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",communicationeventpurpose.getCommunicationEventId());
}

		if(communicationeventpurpose.getDescription() != null ){
			returnVal.put("description",communicationeventpurpose.getDescription());
}

		return returnVal;
}


	public static CommunicationEventPurpose map(Map<String, Object> fields) {

		CommunicationEventPurpose returnVal = new CommunicationEventPurpose();

		if(fields.get("communicationEventPrpTypId") != null) {
			returnVal.setCommunicationEventPrpTypId((String) fields.get("communicationEventPrpTypId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CommunicationEventPurpose mapstrstr(Map<String, String> fields) throws Exception {

		CommunicationEventPurpose returnVal = new CommunicationEventPurpose();

		if(fields.get("communicationEventPrpTypId") != null) {
			returnVal.setCommunicationEventPrpTypId((String) fields.get("communicationEventPrpTypId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CommunicationEventPurpose map(GenericValue val) {

CommunicationEventPurpose returnVal = new CommunicationEventPurpose();
		returnVal.setCommunicationEventPrpTypId(val.getString("communicationEventPrpTypId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CommunicationEventPurpose map(HttpServletRequest request) throws Exception {

		CommunicationEventPurpose returnVal = new CommunicationEventPurpose();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("communicationEventPrpTypId")) {
returnVal.setCommunicationEventPrpTypId(request.getParameter("communicationEventPrpTypId"));
}

		if(paramMap.containsKey("communicationEventId"))  {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
