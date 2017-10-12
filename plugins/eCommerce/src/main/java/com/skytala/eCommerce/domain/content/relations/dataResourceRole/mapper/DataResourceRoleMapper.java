package com.skytala.eCommerce.domain.content.relations.dataResourceRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResourceRole.model.DataResourceRole;

public class DataResourceRoleMapper  {


	public static Map<String, Object> map(DataResourceRole dataresourcerole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(dataresourcerole.getDataResourceId() != null ){
			returnVal.put("dataResourceId",dataresourcerole.getDataResourceId());
}

		if(dataresourcerole.getPartyId() != null ){
			returnVal.put("partyId",dataresourcerole.getPartyId());
}

		if(dataresourcerole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",dataresourcerole.getRoleTypeId());
}

		if(dataresourcerole.getFromDate() != null ){
			returnVal.put("fromDate",dataresourcerole.getFromDate());
}

		if(dataresourcerole.getThruDate() != null ){
			returnVal.put("thruDate",dataresourcerole.getThruDate());
}

		return returnVal;
}


	public static DataResourceRole map(Map<String, Object> fields) {

		DataResourceRole returnVal = new DataResourceRole();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
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
	public static DataResourceRole mapstrstr(Map<String, String> fields) throws Exception {

		DataResourceRole returnVal = new DataResourceRole();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
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
	public static DataResourceRole map(GenericValue val) {

DataResourceRole returnVal = new DataResourceRole();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static DataResourceRole map(HttpServletRequest request) throws Exception {

		DataResourceRole returnVal = new DataResourceRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
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
