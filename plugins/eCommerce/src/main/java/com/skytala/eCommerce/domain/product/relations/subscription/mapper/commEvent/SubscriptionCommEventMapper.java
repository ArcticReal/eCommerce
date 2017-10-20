package com.skytala.eCommerce.domain.product.relations.subscription.mapper.commEvent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscription.model.commEvent.SubscriptionCommEvent;

public class SubscriptionCommEventMapper  {


	public static Map<String, Object> map(SubscriptionCommEvent subscriptioncommevent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(subscriptioncommevent.getSubscriptionId() != null ){
			returnVal.put("subscriptionId",subscriptioncommevent.getSubscriptionId());
}

		if(subscriptioncommevent.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",subscriptioncommevent.getCommunicationEventId());
}

		return returnVal;
}


	public static SubscriptionCommEvent map(Map<String, Object> fields) {

		SubscriptionCommEvent returnVal = new SubscriptionCommEvent();

		if(fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}


		return returnVal;
 } 
	public static SubscriptionCommEvent mapstrstr(Map<String, String> fields) throws Exception {

		SubscriptionCommEvent returnVal = new SubscriptionCommEvent();

		if(fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}


		return returnVal;
 } 
	public static SubscriptionCommEvent map(GenericValue val) {

SubscriptionCommEvent returnVal = new SubscriptionCommEvent();
		returnVal.setSubscriptionId(val.getString("subscriptionId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));


return returnVal;

}

public static SubscriptionCommEvent map(HttpServletRequest request) throws Exception {

		SubscriptionCommEvent returnVal = new SubscriptionCommEvent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("subscriptionId")) {
returnVal.setSubscriptionId(request.getParameter("subscriptionId"));
}

		if(paramMap.containsKey("communicationEventId"))  {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}
return returnVal;

}
}
