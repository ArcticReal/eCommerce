package com.skytala.eCommerce.domain.order.relations.communicationEventOrder.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model.CommunicationEventOrder;

public class CommunicationEventOrderMapper  {


	public static Map<String, Object> map(CommunicationEventOrder communicationeventorder) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(communicationeventorder.getOrderId() != null ){
			returnVal.put("orderId",communicationeventorder.getOrderId());
}

		if(communicationeventorder.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",communicationeventorder.getCommunicationEventId());
}

		return returnVal;
}


	public static CommunicationEventOrder map(Map<String, Object> fields) {

		CommunicationEventOrder returnVal = new CommunicationEventOrder();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}


		return returnVal;
 } 
	public static CommunicationEventOrder mapstrstr(Map<String, String> fields) throws Exception {

		CommunicationEventOrder returnVal = new CommunicationEventOrder();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}


		return returnVal;
 } 
	public static CommunicationEventOrder map(GenericValue val) {

CommunicationEventOrder returnVal = new CommunicationEventOrder();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));


return returnVal;

}

public static CommunicationEventOrder map(HttpServletRequest request) throws Exception {

		CommunicationEventOrder returnVal = new CommunicationEventOrder();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("communicationEventId"))  {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}
return returnVal;

}
}
