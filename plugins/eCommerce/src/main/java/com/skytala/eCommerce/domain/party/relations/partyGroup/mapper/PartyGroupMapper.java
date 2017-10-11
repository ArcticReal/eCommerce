package com.skytala.eCommerce.domain.party.relations.partyGroup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyGroup.model.PartyGroup;

public class PartyGroupMapper  {


	public static Map<String, Object> map(PartyGroup partygroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partygroup.getPartyId() != null ){
			returnVal.put("partyId",partygroup.getPartyId());
}

		if(partygroup.getGroupName() != null ){
			returnVal.put("groupName",partygroup.getGroupName());
}

		if(partygroup.getGroupNameLocal() != null ){
			returnVal.put("groupNameLocal",partygroup.getGroupNameLocal());
}

		if(partygroup.getOfficeSiteName() != null ){
			returnVal.put("officeSiteName",partygroup.getOfficeSiteName());
}

		if(partygroup.getAnnualRevenue() != null ){
			returnVal.put("annualRevenue",partygroup.getAnnualRevenue());
}

		if(partygroup.getNumEmployees() != null ){
			returnVal.put("numEmployees",partygroup.getNumEmployees());
}

		if(partygroup.getTickerSymbol() != null ){
			returnVal.put("tickerSymbol",partygroup.getTickerSymbol());
}

		if(partygroup.getComments() != null ){
			returnVal.put("comments",partygroup.getComments());
}

		if(partygroup.getLogoImageUrl() != null ){
			returnVal.put("logoImageUrl",partygroup.getLogoImageUrl());
}

		return returnVal;
}


	public static PartyGroup map(Map<String, Object> fields) {

		PartyGroup returnVal = new PartyGroup();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("groupName") != null) {
			returnVal.setGroupName((String) fields.get("groupName"));
}

		if(fields.get("groupNameLocal") != null) {
			returnVal.setGroupNameLocal((String) fields.get("groupNameLocal"));
}

		if(fields.get("officeSiteName") != null) {
			returnVal.setOfficeSiteName((String) fields.get("officeSiteName"));
}

		if(fields.get("annualRevenue") != null) {
			returnVal.setAnnualRevenue((BigDecimal) fields.get("annualRevenue"));
}

		if(fields.get("numEmployees") != null) {
			returnVal.setNumEmployees((long) fields.get("numEmployees"));
}

		if(fields.get("tickerSymbol") != null) {
			returnVal.setTickerSymbol((String) fields.get("tickerSymbol"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("logoImageUrl") != null) {
			returnVal.setLogoImageUrl((String) fields.get("logoImageUrl"));
}


		return returnVal;
 } 
	public static PartyGroup mapstrstr(Map<String, String> fields) throws Exception {

		PartyGroup returnVal = new PartyGroup();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("groupName") != null) {
			returnVal.setGroupName((String) fields.get("groupName"));
}

		if(fields.get("groupNameLocal") != null) {
			returnVal.setGroupNameLocal((String) fields.get("groupNameLocal"));
}

		if(fields.get("officeSiteName") != null) {
			returnVal.setOfficeSiteName((String) fields.get("officeSiteName"));
}

		if(fields.get("annualRevenue") != null) {
String buf;
buf = fields.get("annualRevenue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAnnualRevenue(bd);
}

		if(fields.get("numEmployees") != null) {
String buf;
buf = fields.get("numEmployees");
long ibuf = Long.parseLong(buf);
			returnVal.setNumEmployees(ibuf);
}

		if(fields.get("tickerSymbol") != null) {
			returnVal.setTickerSymbol((String) fields.get("tickerSymbol"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("logoImageUrl") != null) {
			returnVal.setLogoImageUrl((String) fields.get("logoImageUrl"));
}


		return returnVal;
 } 
	public static PartyGroup map(GenericValue val) {

PartyGroup returnVal = new PartyGroup();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setGroupName(val.getString("groupName"));
		returnVal.setGroupNameLocal(val.getString("groupNameLocal"));
		returnVal.setOfficeSiteName(val.getString("officeSiteName"));
		returnVal.setAnnualRevenue(val.getBigDecimal("annualRevenue"));
		returnVal.setNumEmployees(val.getLong("numEmployees"));
		returnVal.setTickerSymbol(val.getString("tickerSymbol"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setLogoImageUrl(val.getString("logoImageUrl"));


return returnVal;

}

public static PartyGroup map(HttpServletRequest request) throws Exception {

		PartyGroup returnVal = new PartyGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("groupName"))  {
returnVal.setGroupName(request.getParameter("groupName"));
}
		if(paramMap.containsKey("groupNameLocal"))  {
returnVal.setGroupNameLocal(request.getParameter("groupNameLocal"));
}
		if(paramMap.containsKey("officeSiteName"))  {
returnVal.setOfficeSiteName(request.getParameter("officeSiteName"));
}
		if(paramMap.containsKey("annualRevenue"))  {
String buf = request.getParameter("annualRevenue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAnnualRevenue(bd);
}
		if(paramMap.containsKey("numEmployees"))  {
String buf = request.getParameter("numEmployees");
Long ibuf = Long.parseLong(buf);
returnVal.setNumEmployees(ibuf);
}
		if(paramMap.containsKey("tickerSymbol"))  {
returnVal.setTickerSymbol(request.getParameter("tickerSymbol"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("logoImageUrl"))  {
returnVal.setLogoImageUrl(request.getParameter("logoImageUrl"));
}
return returnVal;

}
}
