package com.skytala.eCommerce.domain.subscriptionResource.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.subscriptionResource.model.SubscriptionResource;

public class SubscriptionResourceMapper  {


	public static Map<String, Object> map(SubscriptionResource subscriptionresource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(subscriptionresource.getSubscriptionResourceId() != null ){
			returnVal.put("subscriptionResourceId",subscriptionresource.getSubscriptionResourceId());
}

		if(subscriptionresource.getParentResourceId() != null ){
			returnVal.put("parentResourceId",subscriptionresource.getParentResourceId());
}

		if(subscriptionresource.getDescription() != null ){
			returnVal.put("description",subscriptionresource.getDescription());
}

		if(subscriptionresource.getContentId() != null ){
			returnVal.put("contentId",subscriptionresource.getContentId());
}

		if(subscriptionresource.getWebSiteId() != null ){
			returnVal.put("webSiteId",subscriptionresource.getWebSiteId());
}

		if(subscriptionresource.getServiceNameOnExpiry() != null ){
			returnVal.put("serviceNameOnExpiry",subscriptionresource.getServiceNameOnExpiry());
}

		return returnVal;
}


	public static SubscriptionResource map(Map<String, Object> fields) {

		SubscriptionResource returnVal = new SubscriptionResource();

		if(fields.get("subscriptionResourceId") != null) {
			returnVal.setSubscriptionResourceId((String) fields.get("subscriptionResourceId"));
}

		if(fields.get("parentResourceId") != null) {
			returnVal.setParentResourceId((String) fields.get("parentResourceId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("serviceNameOnExpiry") != null) {
			returnVal.setServiceNameOnExpiry((String) fields.get("serviceNameOnExpiry"));
}


		return returnVal;
 } 
	public static SubscriptionResource mapstrstr(Map<String, String> fields) throws Exception {

		SubscriptionResource returnVal = new SubscriptionResource();

		if(fields.get("subscriptionResourceId") != null) {
			returnVal.setSubscriptionResourceId((String) fields.get("subscriptionResourceId"));
}

		if(fields.get("parentResourceId") != null) {
			returnVal.setParentResourceId((String) fields.get("parentResourceId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("serviceNameOnExpiry") != null) {
			returnVal.setServiceNameOnExpiry((String) fields.get("serviceNameOnExpiry"));
}


		return returnVal;
 } 
	public static SubscriptionResource map(GenericValue val) {

SubscriptionResource returnVal = new SubscriptionResource();
		returnVal.setSubscriptionResourceId(val.getString("subscriptionResourceId"));
		returnVal.setParentResourceId(val.getString("parentResourceId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setWebSiteId(val.getString("webSiteId"));
		returnVal.setServiceNameOnExpiry(val.getString("serviceNameOnExpiry"));


return returnVal;

}

public static SubscriptionResource map(HttpServletRequest request) throws Exception {

		SubscriptionResource returnVal = new SubscriptionResource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("subscriptionResourceId")) {
returnVal.setSubscriptionResourceId(request.getParameter("subscriptionResourceId"));
}

		if(paramMap.containsKey("parentResourceId"))  {
returnVal.setParentResourceId(request.getParameter("parentResourceId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("webSiteId"))  {
returnVal.setWebSiteId(request.getParameter("webSiteId"));
}
		if(paramMap.containsKey("serviceNameOnExpiry"))  {
returnVal.setServiceNameOnExpiry(request.getParameter("serviceNameOnExpiry"));
}
return returnVal;

}
}
