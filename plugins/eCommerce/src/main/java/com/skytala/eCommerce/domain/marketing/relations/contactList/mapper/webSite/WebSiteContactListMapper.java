package com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.webSite;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.webSite.WebSiteContactList;

public class WebSiteContactListMapper  {


	public static Map<String, Object> map(WebSiteContactList websitecontactlist) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(websitecontactlist.getWebSiteId() != null ){
			returnVal.put("webSiteId",websitecontactlist.getWebSiteId());
}

		if(websitecontactlist.getContactListId() != null ){
			returnVal.put("contactListId",websitecontactlist.getContactListId());
}

		if(websitecontactlist.getFromDate() != null ){
			returnVal.put("fromDate",websitecontactlist.getFromDate());
}

		if(websitecontactlist.getThruDate() != null ){
			returnVal.put("thruDate",websitecontactlist.getThruDate());
}

		return returnVal;
}


	public static WebSiteContactList map(Map<String, Object> fields) {

		WebSiteContactList returnVal = new WebSiteContactList();

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static WebSiteContactList mapstrstr(Map<String, String> fields) throws Exception {

		WebSiteContactList returnVal = new WebSiteContactList();

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static WebSiteContactList map(GenericValue val) {

WebSiteContactList returnVal = new WebSiteContactList();
		returnVal.setWebSiteId(val.getString("webSiteId"));
		returnVal.setContactListId(val.getString("contactListId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static WebSiteContactList map(HttpServletRequest request) throws Exception {

		WebSiteContactList returnVal = new WebSiteContactList();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("webSiteId")) {
returnVal.setWebSiteId(request.getParameter("webSiteId"));
}

		if(paramMap.containsKey("contactListId"))  {
returnVal.setContactListId(request.getParameter("contactListId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
