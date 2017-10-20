package com.skytala.eCommerce.domain.content.relations.content.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.model.role.ContentRole;

public class ContentRoleMapper  {


	public static Map<String, Object> map(ContentRole contentrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentrole.getContentId() != null ){
			returnVal.put("contentId",contentrole.getContentId());
}

		if(contentrole.getPartyId() != null ){
			returnVal.put("partyId",contentrole.getPartyId());
}

		if(contentrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",contentrole.getRoleTypeId());
}

		if(contentrole.getFromDate() != null ){
			returnVal.put("fromDate",contentrole.getFromDate());
}

		if(contentrole.getThruDate() != null ){
			returnVal.put("thruDate",contentrole.getThruDate());
}

		return returnVal;
}


	public static ContentRole map(Map<String, Object> fields) {

		ContentRole returnVal = new ContentRole();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ContentRole mapstrstr(Map<String, String> fields) throws Exception {

		ContentRole returnVal = new ContentRole();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
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
	public static ContentRole map(GenericValue val) {

ContentRole returnVal = new ContentRole();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ContentRole map(HttpServletRequest request) throws Exception {

		ContentRole returnVal = new ContentRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
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
