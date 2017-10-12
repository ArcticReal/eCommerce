package com.skytala.eCommerce.domain.humanres.relations.partyResume.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.model.PartyResume;

public class PartyResumeMapper  {


	public static Map<String, Object> map(PartyResume partyresume) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyresume.getResumeId() != null ){
			returnVal.put("resumeId",partyresume.getResumeId());
}

		if(partyresume.getPartyId() != null ){
			returnVal.put("partyId",partyresume.getPartyId());
}

		if(partyresume.getContentId() != null ){
			returnVal.put("contentId",partyresume.getContentId());
}

		if(partyresume.getResumeDate() != null ){
			returnVal.put("resumeDate",partyresume.getResumeDate());
}

		if(partyresume.getResumeText() != null ){
			returnVal.put("resumeText",partyresume.getResumeText());
}

		return returnVal;
}


	public static PartyResume map(Map<String, Object> fields) {

		PartyResume returnVal = new PartyResume();

		if(fields.get("resumeId") != null) {
			returnVal.setResumeId((String) fields.get("resumeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("resumeDate") != null) {
			returnVal.setResumeDate((Timestamp) fields.get("resumeDate"));
}

		if(fields.get("resumeText") != null) {
			returnVal.setResumeText((String) fields.get("resumeText"));
}


		return returnVal;
 } 
	public static PartyResume mapstrstr(Map<String, String> fields) throws Exception {

		PartyResume returnVal = new PartyResume();

		if(fields.get("resumeId") != null) {
			returnVal.setResumeId((String) fields.get("resumeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("resumeDate") != null) {
String buf = fields.get("resumeDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setResumeDate(ibuf);
}

		if(fields.get("resumeText") != null) {
			returnVal.setResumeText((String) fields.get("resumeText"));
}


		return returnVal;
 } 
	public static PartyResume map(GenericValue val) {

PartyResume returnVal = new PartyResume();
		returnVal.setResumeId(val.getString("resumeId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setResumeDate(val.getTimestamp("resumeDate"));
		returnVal.setResumeText(val.getString("resumeText"));


return returnVal;

}

public static PartyResume map(HttpServletRequest request) throws Exception {

		PartyResume returnVal = new PartyResume();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("resumeId")) {
returnVal.setResumeId(request.getParameter("resumeId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("resumeDate"))  {
String buf = request.getParameter("resumeDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setResumeDate(ibuf);
}
		if(paramMap.containsKey("resumeText"))  {
returnVal.setResumeText(request.getParameter("resumeText"));
}
return returnVal;

}
}
