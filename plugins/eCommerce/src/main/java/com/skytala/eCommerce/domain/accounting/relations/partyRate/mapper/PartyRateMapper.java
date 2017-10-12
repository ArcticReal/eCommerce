package com.skytala.eCommerce.domain.accounting.relations.partyRate.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;

public class PartyRateMapper  {


	public static Map<String, Object> map(PartyRate partyrate) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyrate.getPartyId() != null ){
			returnVal.put("partyId",partyrate.getPartyId());
}

		if(partyrate.getRateTypeId() != null ){
			returnVal.put("rateTypeId",partyrate.getRateTypeId());
}

		if(partyrate.getDefaultRate() != null ){
			returnVal.put("defaultRate",partyrate.getDefaultRate());
}

		if(partyrate.getPercentageUsed() != null ){
			returnVal.put("percentageUsed",partyrate.getPercentageUsed());
}

		if(partyrate.getFromDate() != null ){
			returnVal.put("fromDate",partyrate.getFromDate());
}

		if(partyrate.getThruDate() != null ){
			returnVal.put("thruDate",partyrate.getThruDate());
}

		return returnVal;
}


	public static PartyRate map(Map<String, Object> fields) {

		PartyRate returnVal = new PartyRate();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("defaultRate") != null) {
			returnVal.setDefaultRate((boolean) fields.get("defaultRate"));
}

		if(fields.get("percentageUsed") != null) {
			returnVal.setPercentageUsed((BigDecimal) fields.get("percentageUsed"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static PartyRate mapstrstr(Map<String, String> fields) throws Exception {

		PartyRate returnVal = new PartyRate();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("defaultRate") != null) {
String buf;
buf = fields.get("defaultRate");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setDefaultRate(ibuf);
}

		if(fields.get("percentageUsed") != null) {
String buf;
buf = fields.get("percentageUsed");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentageUsed(bd);
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
	public static PartyRate map(GenericValue val) {

PartyRate returnVal = new PartyRate();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRateTypeId(val.getString("rateTypeId"));
		returnVal.setDefaultRate(val.getBoolean("defaultRate"));
		returnVal.setPercentageUsed(val.getBigDecimal("percentageUsed"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static PartyRate map(HttpServletRequest request) throws Exception {

		PartyRate returnVal = new PartyRate();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("rateTypeId"))  {
returnVal.setRateTypeId(request.getParameter("rateTypeId"));
}
		if(paramMap.containsKey("defaultRate"))  {
String buf = request.getParameter("defaultRate");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setDefaultRate(ibuf);
}
		if(paramMap.containsKey("percentageUsed"))  {
String buf = request.getParameter("percentageUsed");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentageUsed(bd);
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
