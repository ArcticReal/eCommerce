package com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.role.PicklistRole;

public class PicklistRoleMapper  {


	public static Map<String, Object> map(PicklistRole picklistrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(picklistrole.getPicklistId() != null ){
			returnVal.put("picklistId",picklistrole.getPicklistId());
}

		if(picklistrole.getPartyId() != null ){
			returnVal.put("partyId",picklistrole.getPartyId());
}

		if(picklistrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",picklistrole.getRoleTypeId());
}

		if(picklistrole.getFromDate() != null ){
			returnVal.put("fromDate",picklistrole.getFromDate());
}

		if(picklistrole.getThruDate() != null ){
			returnVal.put("thruDate",picklistrole.getThruDate());
}

		if(picklistrole.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",picklistrole.getCreatedByUserLogin());
}

		if(picklistrole.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",picklistrole.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static PicklistRole map(Map<String, Object> fields) {

		PicklistRole returnVal = new PicklistRole();

		if(fields.get("picklistId") != null) {
			returnVal.setPicklistId((String) fields.get("picklistId"));
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

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static PicklistRole mapstrstr(Map<String, String> fields) throws Exception {

		PicklistRole returnVal = new PicklistRole();

		if(fields.get("picklistId") != null) {
			returnVal.setPicklistId((String) fields.get("picklistId"));
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

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static PicklistRole map(GenericValue val) {

PicklistRole returnVal = new PicklistRole();
		returnVal.setPicklistId(val.getString("picklistId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static PicklistRole map(HttpServletRequest request) throws Exception {

		PicklistRole returnVal = new PicklistRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("picklistId")) {
returnVal.setPicklistId(request.getParameter("picklistId"));
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
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
