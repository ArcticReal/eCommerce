package com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.model.SubscriptionFulfillmentPiece;

public class SubscriptionFulfillmentPieceMapper  {


	public static Map<String, Object> map(SubscriptionFulfillmentPiece subscriptionfulfillmentpiece) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(subscriptionfulfillmentpiece.getSubscriptionActivityId() != null ){
			returnVal.put("subscriptionActivityId",subscriptionfulfillmentpiece.getSubscriptionActivityId());
}

		if(subscriptionfulfillmentpiece.getSubscriptionId() != null ){
			returnVal.put("subscriptionId",subscriptionfulfillmentpiece.getSubscriptionId());
}

		return returnVal;
}


	public static SubscriptionFulfillmentPiece map(Map<String, Object> fields) {

		SubscriptionFulfillmentPiece returnVal = new SubscriptionFulfillmentPiece();

		if(fields.get("subscriptionActivityId") != null) {
			returnVal.setSubscriptionActivityId((String) fields.get("subscriptionActivityId"));
}

		if(fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
}


		return returnVal;
 } 
	public static SubscriptionFulfillmentPiece mapstrstr(Map<String, String> fields) throws Exception {

		SubscriptionFulfillmentPiece returnVal = new SubscriptionFulfillmentPiece();

		if(fields.get("subscriptionActivityId") != null) {
			returnVal.setSubscriptionActivityId((String) fields.get("subscriptionActivityId"));
}

		if(fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
}


		return returnVal;
 } 
	public static SubscriptionFulfillmentPiece map(GenericValue val) {

SubscriptionFulfillmentPiece returnVal = new SubscriptionFulfillmentPiece();
		returnVal.setSubscriptionActivityId(val.getString("subscriptionActivityId"));
		returnVal.setSubscriptionId(val.getString("subscriptionId"));


return returnVal;

}

public static SubscriptionFulfillmentPiece map(HttpServletRequest request) throws Exception {

		SubscriptionFulfillmentPiece returnVal = new SubscriptionFulfillmentPiece();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("subscriptionActivityId")) {
returnVal.setSubscriptionActivityId(request.getParameter("subscriptionActivityId"));
}

		if(paramMap.containsKey("subscriptionId"))  {
returnVal.setSubscriptionId(request.getParameter("subscriptionId"));
}
return returnVal;

}
}
