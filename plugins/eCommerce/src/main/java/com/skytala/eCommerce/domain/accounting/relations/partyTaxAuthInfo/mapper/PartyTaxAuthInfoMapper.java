package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;

public class PartyTaxAuthInfoMapper  {


	public static Map<String, Object> map(PartyTaxAuthInfo partytaxauthinfo) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partytaxauthinfo.getPartyId() != null ){
			returnVal.put("partyId",partytaxauthinfo.getPartyId());
}

		if(partytaxauthinfo.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",partytaxauthinfo.getTaxAuthGeoId());
}

		if(partytaxauthinfo.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",partytaxauthinfo.getTaxAuthPartyId());
}

		if(partytaxauthinfo.getFromDate() != null ){
			returnVal.put("fromDate",partytaxauthinfo.getFromDate());
}

		if(partytaxauthinfo.getThruDate() != null ){
			returnVal.put("thruDate",partytaxauthinfo.getThruDate());
}

		if(partytaxauthinfo.getPartyTaxId() != null ){
			returnVal.put("partyTaxId",partytaxauthinfo.getPartyTaxId());
}

		if(partytaxauthinfo.getIsExempt() != null ){
			returnVal.put("isExempt",partytaxauthinfo.getIsExempt());
}

		if(partytaxauthinfo.getIsNexus() != null ){
			returnVal.put("isNexus",partytaxauthinfo.getIsNexus());
}

		return returnVal;
}


	public static PartyTaxAuthInfo map(Map<String, Object> fields) {

		PartyTaxAuthInfo returnVal = new PartyTaxAuthInfo();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
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

		if(fields.get("isNexus") != null) {
			returnVal.setIsNexus((boolean) fields.get("isNexus"));
}


		return returnVal;
 } 
	public static PartyTaxAuthInfo mapstrstr(Map<String, String> fields) throws Exception {

		PartyTaxAuthInfo returnVal = new PartyTaxAuthInfo();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
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

		if(fields.get("isNexus") != null) {
String buf;
buf = fields.get("isNexus");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsNexus(ibuf);
}


		return returnVal;
 } 
	public static PartyTaxAuthInfo map(GenericValue val) {

PartyTaxAuthInfo returnVal = new PartyTaxAuthInfo();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPartyTaxId(val.getString("partyTaxId"));
		returnVal.setIsExempt(val.getBoolean("isExempt"));
		returnVal.setIsNexus(val.getBoolean("isNexus"));


return returnVal;

}

public static PartyTaxAuthInfo map(HttpServletRequest request) throws Exception {

		PartyTaxAuthInfo returnVal = new PartyTaxAuthInfo();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("taxAuthGeoId"))  {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}
		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
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
		if(paramMap.containsKey("isNexus"))  {
String buf = request.getParameter("isNexus");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsNexus(ibuf);
}
return returnVal;

}
}
