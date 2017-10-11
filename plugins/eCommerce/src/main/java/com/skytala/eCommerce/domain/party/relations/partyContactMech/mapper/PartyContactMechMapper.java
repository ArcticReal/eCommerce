package com.skytala.eCommerce.domain.party.relations.partyContactMech.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyContactMech.model.PartyContactMech;

public class PartyContactMechMapper  {


	public static Map<String, Object> map(PartyContactMech partycontactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partycontactmech.getPartyId() != null ){
			returnVal.put("partyId",partycontactmech.getPartyId());
}

		if(partycontactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",partycontactmech.getContactMechId());
}

		if(partycontactmech.getFromDate() != null ){
			returnVal.put("fromDate",partycontactmech.getFromDate());
}

		if(partycontactmech.getThruDate() != null ){
			returnVal.put("thruDate",partycontactmech.getThruDate());
}

		if(partycontactmech.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",partycontactmech.getRoleTypeId());
}

		if(partycontactmech.getAllowSolicitation() != null ){
			returnVal.put("allowSolicitation",partycontactmech.getAllowSolicitation());
}

		if(partycontactmech.getExtension() != null ){
			returnVal.put("extension",partycontactmech.getExtension());
}

		if(partycontactmech.getVerified() != null ){
			returnVal.put("verified",partycontactmech.getVerified());
}

		if(partycontactmech.getComments() != null ){
			returnVal.put("comments",partycontactmech.getComments());
}

		if(partycontactmech.getYearsWithContactMech() != null ){
			returnVal.put("yearsWithContactMech",partycontactmech.getYearsWithContactMech());
}

		if(partycontactmech.getMonthsWithContactMech() != null ){
			returnVal.put("monthsWithContactMech",partycontactmech.getMonthsWithContactMech());
}

		return returnVal;
}


	public static PartyContactMech map(Map<String, Object> fields) {

		PartyContactMech returnVal = new PartyContactMech();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("allowSolicitation") != null) {
			returnVal.setAllowSolicitation((boolean) fields.get("allowSolicitation"));
}

		if(fields.get("extension") != null) {
			returnVal.setExtension((String) fields.get("extension"));
}

		if(fields.get("verified") != null) {
			returnVal.setVerified((boolean) fields.get("verified"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("yearsWithContactMech") != null) {
			returnVal.setYearsWithContactMech((long) fields.get("yearsWithContactMech"));
}

		if(fields.get("monthsWithContactMech") != null) {
			returnVal.setMonthsWithContactMech((long) fields.get("monthsWithContactMech"));
}


		return returnVal;
 } 
	public static PartyContactMech mapstrstr(Map<String, String> fields) throws Exception {

		PartyContactMech returnVal = new PartyContactMech();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
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

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("allowSolicitation") != null) {
String buf;
buf = fields.get("allowSolicitation");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAllowSolicitation(ibuf);
}

		if(fields.get("extension") != null) {
			returnVal.setExtension((String) fields.get("extension"));
}

		if(fields.get("verified") != null) {
String buf;
buf = fields.get("verified");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setVerified(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("yearsWithContactMech") != null) {
String buf;
buf = fields.get("yearsWithContactMech");
long ibuf = Long.parseLong(buf);
			returnVal.setYearsWithContactMech(ibuf);
}

		if(fields.get("monthsWithContactMech") != null) {
String buf;
buf = fields.get("monthsWithContactMech");
long ibuf = Long.parseLong(buf);
			returnVal.setMonthsWithContactMech(ibuf);
}


		return returnVal;
 } 
	public static PartyContactMech map(GenericValue val) {

PartyContactMech returnVal = new PartyContactMech();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setAllowSolicitation(val.getBoolean("allowSolicitation"));
		returnVal.setExtension(val.getString("extension"));
		returnVal.setVerified(val.getBoolean("verified"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setYearsWithContactMech(val.getLong("yearsWithContactMech"));
		returnVal.setMonthsWithContactMech(val.getLong("monthsWithContactMech"));


return returnVal;

}

public static PartyContactMech map(HttpServletRequest request) throws Exception {

		PartyContactMech returnVal = new PartyContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
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
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("allowSolicitation"))  {
String buf = request.getParameter("allowSolicitation");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAllowSolicitation(ibuf);
}
		if(paramMap.containsKey("extension"))  {
returnVal.setExtension(request.getParameter("extension"));
}
		if(paramMap.containsKey("verified"))  {
String buf = request.getParameter("verified");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setVerified(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("yearsWithContactMech"))  {
String buf = request.getParameter("yearsWithContactMech");
Long ibuf = Long.parseLong(buf);
returnVal.setYearsWithContactMech(ibuf);
}
		if(paramMap.containsKey("monthsWithContactMech"))  {
String buf = request.getParameter("monthsWithContactMech");
Long ibuf = Long.parseLong(buf);
returnVal.setMonthsWithContactMech(ibuf);
}
return returnVal;

}
}
