package com.skytala.eCommerce.domain.party.relations.party.mapper.oldTaxInfo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.oldTaxInfo.OldPartyTaxInfo;

public class OldPartyTaxInfoMapper  {


	public static Map<String, Object> map(OldPartyTaxInfo oldpartytaxinfo) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(oldpartytaxinfo.getPartyId() != null ){
			returnVal.put("partyId",oldpartytaxinfo.getPartyId());
}

		if(oldpartytaxinfo.getGeoId() != null ){
			returnVal.put("geoId",oldpartytaxinfo.getGeoId());
}

		if(oldpartytaxinfo.getFromDate() != null ){
			returnVal.put("fromDate",oldpartytaxinfo.getFromDate());
}

		if(oldpartytaxinfo.getThruDate() != null ){
			returnVal.put("thruDate",oldpartytaxinfo.getThruDate());
}

		if(oldpartytaxinfo.getPartyTaxId() != null ){
			returnVal.put("partyTaxId",oldpartytaxinfo.getPartyTaxId());
}

		if(oldpartytaxinfo.getIsExempt() != null ){
			returnVal.put("isExempt",oldpartytaxinfo.getIsExempt());
}

		return returnVal;
}


	public static OldPartyTaxInfo map(Map<String, Object> fields) {

		OldPartyTaxInfo returnVal = new OldPartyTaxInfo();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("partyTaxId") != null) {
			returnVal.setPartyTaxId((String) fields.get("partyTaxId"));
}

		if(fields.get("isExempt") != null) {
			returnVal.setIsExempt((boolean) fields.get("isExempt"));
}


		return returnVal;
 } 
	public static OldPartyTaxInfo mapstrstr(Map<String, String> fields) throws Exception {

		OldPartyTaxInfo returnVal = new OldPartyTaxInfo();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("geoId") != null) {
			returnVal.setGeoId((String) fields.get("geoId"));
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

		if(fields.get("partyTaxId") != null) {
			returnVal.setPartyTaxId((String) fields.get("partyTaxId"));
}

		if(fields.get("isExempt") != null) {
String buf;
buf = fields.get("isExempt");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsExempt(ibuf);
}


		return returnVal;
 } 
	public static OldPartyTaxInfo map(GenericValue val) {

OldPartyTaxInfo returnVal = new OldPartyTaxInfo();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setGeoId(val.getString("geoId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPartyTaxId(val.getString("partyTaxId"));
		returnVal.setIsExempt(val.getBoolean("isExempt"));


return returnVal;

}

public static OldPartyTaxInfo map(HttpServletRequest request) throws Exception {

		OldPartyTaxInfo returnVal = new OldPartyTaxInfo();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("geoId"))  {
returnVal.setGeoId(request.getParameter("geoId"));
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
		if(paramMap.containsKey("partyTaxId"))  {
returnVal.setPartyTaxId(request.getParameter("partyTaxId"));
}
		if(paramMap.containsKey("isExempt"))  {
String buf = request.getParameter("isExempt");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsExempt(ibuf);
}
return returnVal;

}
}
