package com.skytala.eCommerce.domain.humanres.relations.partySkill.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.model.PartySkill;

public class PartySkillMapper  {


	public static Map<String, Object> map(PartySkill partyskill) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyskill.getPartyId() != null ){
			returnVal.put("partyId",partyskill.getPartyId());
}

		if(partyskill.getSkillTypeId() != null ){
			returnVal.put("skillTypeId",partyskill.getSkillTypeId());
}

		if(partyskill.getYearsExperience() != null ){
			returnVal.put("yearsExperience",partyskill.getYearsExperience());
}

		if(partyskill.getRating() != null ){
			returnVal.put("rating",partyskill.getRating());
}

		if(partyskill.getSkillLevel() != null ){
			returnVal.put("skillLevel",partyskill.getSkillLevel());
}

		if(partyskill.getStartedUsingDate() != null ){
			returnVal.put("startedUsingDate",partyskill.getStartedUsingDate());
}

		return returnVal;
}


	public static PartySkill map(Map<String, Object> fields) {

		PartySkill returnVal = new PartySkill();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
}

		if(fields.get("yearsExperience") != null) {
			returnVal.setYearsExperience((long) fields.get("yearsExperience"));
}

		if(fields.get("rating") != null) {
			returnVal.setRating((long) fields.get("rating"));
}

		if(fields.get("skillLevel") != null) {
			returnVal.setSkillLevel((long) fields.get("skillLevel"));
}

		if(fields.get("startedUsingDate") != null) {
			returnVal.setStartedUsingDate((Timestamp) fields.get("startedUsingDate"));
}


		return returnVal;
 } 
	public static PartySkill mapstrstr(Map<String, String> fields) throws Exception {

		PartySkill returnVal = new PartySkill();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
}

		if(fields.get("yearsExperience") != null) {
String buf;
buf = fields.get("yearsExperience");
long ibuf = Long.parseLong(buf);
			returnVal.setYearsExperience(ibuf);
}

		if(fields.get("rating") != null) {
String buf;
buf = fields.get("rating");
long ibuf = Long.parseLong(buf);
			returnVal.setRating(ibuf);
}

		if(fields.get("skillLevel") != null) {
String buf;
buf = fields.get("skillLevel");
long ibuf = Long.parseLong(buf);
			returnVal.setSkillLevel(ibuf);
}

		if(fields.get("startedUsingDate") != null) {
String buf = fields.get("startedUsingDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStartedUsingDate(ibuf);
}


		return returnVal;
 } 
	public static PartySkill map(GenericValue val) {

PartySkill returnVal = new PartySkill();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setSkillTypeId(val.getString("skillTypeId"));
		returnVal.setYearsExperience(val.getLong("yearsExperience"));
		returnVal.setRating(val.getLong("rating"));
		returnVal.setSkillLevel(val.getLong("skillLevel"));
		returnVal.setStartedUsingDate(val.getTimestamp("startedUsingDate"));


return returnVal;

}

public static PartySkill map(HttpServletRequest request) throws Exception {

		PartySkill returnVal = new PartySkill();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("skillTypeId"))  {
returnVal.setSkillTypeId(request.getParameter("skillTypeId"));
}
		if(paramMap.containsKey("yearsExperience"))  {
String buf = request.getParameter("yearsExperience");
Long ibuf = Long.parseLong(buf);
returnVal.setYearsExperience(ibuf);
}
		if(paramMap.containsKey("rating"))  {
String buf = request.getParameter("rating");
Long ibuf = Long.parseLong(buf);
returnVal.setRating(ibuf);
}
		if(paramMap.containsKey("skillLevel"))  {
String buf = request.getParameter("skillLevel");
Long ibuf = Long.parseLong(buf);
returnVal.setSkillLevel(ibuf);
}
		if(paramMap.containsKey("startedUsingDate"))  {
String buf = request.getParameter("startedUsingDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStartedUsingDate(ibuf);
}
return returnVal;

}
}
