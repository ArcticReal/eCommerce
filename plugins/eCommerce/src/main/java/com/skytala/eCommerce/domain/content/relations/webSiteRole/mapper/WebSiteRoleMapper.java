package com.skytala.eCommerce.domain.content.relations.webSiteRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.model.WebSiteRole;

public class WebSiteRoleMapper  {


	public static Map<String, Object> map(WebSiteRole websiterole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(websiterole.getPartyId() != null ){
			returnVal.put("partyId",websiterole.getPartyId());
}

		if(websiterole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",websiterole.getRoleTypeId());
}

		if(websiterole.getWebSiteId() != null ){
			returnVal.put("webSiteId",websiterole.getWebSiteId());
}

		if(websiterole.getFromDate() != null ){
			returnVal.put("fromDate",websiterole.getFromDate());
}

		if(websiterole.getThruDate() != null ){
			returnVal.put("thruDate",websiterole.getThruDate());
}

		if(websiterole.getSequenceNum() != null ){
			returnVal.put("sequenceNum",websiterole.getSequenceNum());
}

		return returnVal;
}


	public static WebSiteRole map(Map<String, Object> fields) {

		WebSiteRole returnVal = new WebSiteRole();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static WebSiteRole mapstrstr(Map<String, String> fields) throws Exception {

		WebSiteRole returnVal = new WebSiteRole();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("webSiteId") != null) {
			returnVal.setWebSiteId((String) fields.get("webSiteId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static WebSiteRole map(GenericValue val) {

WebSiteRole returnVal = new WebSiteRole();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setWebSiteId(val.getString("webSiteId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static WebSiteRole map(HttpServletRequest request) throws Exception {

		WebSiteRole returnVal = new WebSiteRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("webSiteId"))  {
returnVal.setWebSiteId(request.getParameter("webSiteId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
