package com.skytala.eCommerce.domain.content.relations.content.mapper.webSite;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.model.webSite.WebSiteContent;

public class WebSiteContentMapper  {


	public static Map<String, Object> map(WebSiteContent websitecontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(websitecontent.getWebSiteId() != null ){
			returnVal.put("webSiteId",websitecontent.getWebSiteId());
}

		if(websitecontent.getContentId() != null ){
			returnVal.put("contentId",websitecontent.getContentId());
}

		if(websitecontent.getWebSiteContentTypeId() != null ){
			returnVal.put("webSiteContentTypeId",websitecontent.getWebSiteContentTypeId());
}

		if(websitecontent.getFromDate() != null ){
			returnVal.put("fromDate",websitecontent.getFromDate());
}

		if(websitecontent.getThruDate() != null ){
			returnVal.put("thruDate",websitecontent.getThruDate());
}

		return returnVal;
}


	public static WebSiteContent map(Map<String, Object> fields) {

		WebSiteContent returnVal = new WebSiteContent();

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("webSiteContentTypeId") != null) {
			returnVal.setWebSiteContentTypeId((String) fields.get("webSiteContentTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static WebSiteContent mapstrstr(Map<String, String> fields) throws Exception {

		WebSiteContent returnVal = new WebSiteContent();

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("webSiteContentTypeId") != null) {
			returnVal.setWebSiteContentTypeId((String) fields.get("webSiteContentTypeId"));
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
	public static WebSiteContent map(GenericValue val) {

WebSiteContent returnVal = new WebSiteContent();
		returnVal.setWebSiteId(val.getString("webSiteId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setWebSiteContentTypeId(val.getString("webSiteContentTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static WebSiteContent map(HttpServletRequest request) throws Exception {

		WebSiteContent returnVal = new WebSiteContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("webSiteId")) {
returnVal.setWebSiteId(request.getParameter("webSiteId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("webSiteContentTypeId"))  {
returnVal.setWebSiteContentTypeId(request.getParameter("webSiteContentTypeId"));
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
