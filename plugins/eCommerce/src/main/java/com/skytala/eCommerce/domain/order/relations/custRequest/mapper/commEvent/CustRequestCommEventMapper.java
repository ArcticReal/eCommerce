package com.skytala.eCommerce.domain.order.relations.custRequest.mapper.commEvent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.commEvent.CustRequestCommEvent;

public class CustRequestCommEventMapper  {


	public static Map<String, Object> map(CustRequestCommEvent custrequestcommevent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestcommevent.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestcommevent.getCustRequestId());
}

		if(custrequestcommevent.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",custrequestcommevent.getCommunicationEventId());
}

		return returnVal;
}


	public static CustRequestCommEvent map(Map<String, Object> fields) {

		CustRequestCommEvent returnVal = new CustRequestCommEvent();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}


		return returnVal;
 } 
	public static CustRequestCommEvent mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestCommEvent returnVal = new CustRequestCommEvent();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}


		return returnVal;
 } 
	public static CustRequestCommEvent map(GenericValue val) {

CustRequestCommEvent returnVal = new CustRequestCommEvent();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));


return returnVal;

}

public static CustRequestCommEvent map(HttpServletRequest request) throws Exception {

		CustRequestCommEvent returnVal = new CustRequestCommEvent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("communicationEventId"))  {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}
return returnVal;

}
}
