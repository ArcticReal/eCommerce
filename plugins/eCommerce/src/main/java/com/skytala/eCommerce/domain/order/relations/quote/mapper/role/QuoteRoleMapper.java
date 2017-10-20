package com.skytala.eCommerce.domain.order.relations.quote.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.model.role.QuoteRole;

public class QuoteRoleMapper  {


	public static Map<String, Object> map(QuoteRole quoterole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quoterole.getQuoteId() != null ){
			returnVal.put("quoteId",quoterole.getQuoteId());
}

		if(quoterole.getPartyId() != null ){
			returnVal.put("partyId",quoterole.getPartyId());
}

		if(quoterole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",quoterole.getRoleTypeId());
}

		if(quoterole.getFromDate() != null ){
			returnVal.put("fromDate",quoterole.getFromDate());
}

		if(quoterole.getThruDate() != null ){
			returnVal.put("thruDate",quoterole.getThruDate());
}

		return returnVal;
}


	public static QuoteRole map(Map<String, Object> fields) {

		QuoteRole returnVal = new QuoteRole();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
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
	public static QuoteRole mapstrstr(Map<String, String> fields) throws Exception {

		QuoteRole returnVal = new QuoteRole();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
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
	public static QuoteRole map(GenericValue val) {

QuoteRole returnVal = new QuoteRole();
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static QuoteRole map(HttpServletRequest request) throws Exception {

		QuoteRole returnVal = new QuoteRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteId")) {
returnVal.setQuoteId(request.getParameter("quoteId"));
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
