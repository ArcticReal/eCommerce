package com.skytala.eCommerce.domain.accounting.relations.finAccountRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.model.FinAccountRole;

public class FinAccountRoleMapper  {


	public static Map<String, Object> map(FinAccountRole finaccountrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccountrole.getFinAccountId() != null ){
			returnVal.put("finAccountId",finaccountrole.getFinAccountId());
}

		if(finaccountrole.getPartyId() != null ){
			returnVal.put("partyId",finaccountrole.getPartyId());
}

		if(finaccountrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",finaccountrole.getRoleTypeId());
}

		if(finaccountrole.getFromDate() != null ){
			returnVal.put("fromDate",finaccountrole.getFromDate());
}

		if(finaccountrole.getThruDate() != null ){
			returnVal.put("thruDate",finaccountrole.getThruDate());
}

		return returnVal;
}


	public static FinAccountRole map(Map<String, Object> fields) {

		FinAccountRole returnVal = new FinAccountRole();

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
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
	public static FinAccountRole mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountRole returnVal = new FinAccountRole();

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
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
	public static FinAccountRole map(GenericValue val) {

FinAccountRole returnVal = new FinAccountRole();
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static FinAccountRole map(HttpServletRequest request) throws Exception {

		FinAccountRole returnVal = new FinAccountRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountId")) {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
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
