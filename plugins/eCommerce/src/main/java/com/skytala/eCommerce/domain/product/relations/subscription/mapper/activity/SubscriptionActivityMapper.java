package com.skytala.eCommerce.domain.product.relations.subscription.mapper.activity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscription.model.activity.SubscriptionActivity;

public class SubscriptionActivityMapper  {


	public static Map<String, Object> map(SubscriptionActivity subscriptionactivity) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(subscriptionactivity.getSubscriptionActivityId() != null ){
			returnVal.put("subscriptionActivityId",subscriptionactivity.getSubscriptionActivityId());
}

		if(subscriptionactivity.getComments() != null ){
			returnVal.put("comments",subscriptionactivity.getComments());
}

		if(subscriptionactivity.getDateSent() != null ){
			returnVal.put("dateSent",subscriptionactivity.getDateSent());
}

		return returnVal;
}


	public static SubscriptionActivity map(Map<String, Object> fields) {

		SubscriptionActivity returnVal = new SubscriptionActivity();

		if(fields.get("subscriptionActivityId") != null) {
			returnVal.setSubscriptionActivityId((String) fields.get("subscriptionActivityId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("dateSent") != null) {
			returnVal.setDateSent((Timestamp) fields.get("dateSent"));
}


		return returnVal;
 } 
	public static SubscriptionActivity mapstrstr(Map<String, String> fields) throws Exception {

		SubscriptionActivity returnVal = new SubscriptionActivity();

		if(fields.get("subscriptionActivityId") != null) {
			returnVal.setSubscriptionActivityId((String) fields.get("subscriptionActivityId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("dateSent") != null) {
String buf = fields.get("dateSent");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateSent(ibuf);
}


		return returnVal;
 } 
	public static SubscriptionActivity map(GenericValue val) {

SubscriptionActivity returnVal = new SubscriptionActivity();
		returnVal.setSubscriptionActivityId(val.getString("subscriptionActivityId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setDateSent(val.getTimestamp("dateSent"));


return returnVal;

}

public static SubscriptionActivity map(HttpServletRequest request) throws Exception {

		SubscriptionActivity returnVal = new SubscriptionActivity();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("subscriptionActivityId")) {
returnVal.setSubscriptionActivityId(request.getParameter("subscriptionActivityId"));
}

		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("dateSent"))  {
String buf = request.getParameter("dateSent");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateSent(ibuf);
}
return returnVal;

}
}
