package com.skytala.eCommerce.domain.order.relations.custRequest.mapper.party;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.party.CustRequestParty;

public class CustRequestPartyMapper  {


	public static Map<String, Object> map(CustRequestParty custrequestparty) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestparty.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestparty.getCustRequestId());
}

		if(custrequestparty.getPartyId() != null ){
			returnVal.put("partyId",custrequestparty.getPartyId());
}

		if(custrequestparty.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",custrequestparty.getRoleTypeId());
}

		if(custrequestparty.getFromDate() != null ){
			returnVal.put("fromDate",custrequestparty.getFromDate());
}

		if(custrequestparty.getThruDate() != null ){
			returnVal.put("thruDate",custrequestparty.getThruDate());
}

		return returnVal;
}


	public static CustRequestParty map(Map<String, Object> fields) {

		CustRequestParty returnVal = new CustRequestParty();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
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
	public static CustRequestParty mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestParty returnVal = new CustRequestParty();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
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
	public static CustRequestParty map(GenericValue val) {

CustRequestParty returnVal = new CustRequestParty();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static CustRequestParty map(HttpServletRequest request) throws Exception {

		CustRequestParty returnVal = new CustRequestParty();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
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
