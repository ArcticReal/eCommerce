package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model.CommunicationEventWorkEff;

public class CommunicationEventWorkEffMapper  {


	public static Map<String, Object> map(CommunicationEventWorkEff communicationeventworkeff) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(communicationeventworkeff.getWorkEffortId() != null ){
			returnVal.put("workEffortId",communicationeventworkeff.getWorkEffortId());
}

		if(communicationeventworkeff.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",communicationeventworkeff.getCommunicationEventId());
}

		if(communicationeventworkeff.getDescription() != null ){
			returnVal.put("description",communicationeventworkeff.getDescription());
}

		return returnVal;
}


	public static CommunicationEventWorkEff map(Map<String, Object> fields) {

		CommunicationEventWorkEff returnVal = new CommunicationEventWorkEff();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CommunicationEventWorkEff mapstrstr(Map<String, String> fields) throws Exception {

		CommunicationEventWorkEff returnVal = new CommunicationEventWorkEff();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CommunicationEventWorkEff map(GenericValue val) {

CommunicationEventWorkEff returnVal = new CommunicationEventWorkEff();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CommunicationEventWorkEff map(HttpServletRequest request) throws Exception {

		CommunicationEventWorkEff returnVal = new CommunicationEventWorkEff();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
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
