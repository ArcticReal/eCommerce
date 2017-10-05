package com.skytala.eCommerce.domain.glJournal.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.glJournal.model.GlJournal;

public class GlJournalMapper  {


	public static Map<String, Object> map(GlJournal gljournal) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(gljournal.getGlJournalId() != null ){
			returnVal.put("glJournalId",gljournal.getGlJournalId());
}

		if(gljournal.getGlJournalName() != null ){
			returnVal.put("glJournalName",gljournal.getGlJournalName());
}

		if(gljournal.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",gljournal.getOrganizationPartyId());
}

		if(gljournal.getIsPosted() != null ){
			returnVal.put("isPosted",gljournal.getIsPosted());
}

		if(gljournal.getPostedDate() != null ){
			returnVal.put("postedDate",gljournal.getPostedDate());
}

		return returnVal;
}


	public static GlJournal map(Map<String, Object> fields) {

		GlJournal returnVal = new GlJournal();

		if(fields.get("glJournalId") != null) {
			returnVal.setGlJournalId((String) fields.get("glJournalId"));
}

		if(fields.get("glJournalName") != null) {
			returnVal.setGlJournalName((String) fields.get("glJournalName"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("isPosted") != null) {
			returnVal.setIsPosted((boolean) fields.get("isPosted"));
}

		if(fields.get("postedDate") != null) {
			returnVal.setPostedDate((Timestamp) fields.get("postedDate"));
}


		return returnVal;
 } 
	public static GlJournal mapstrstr(Map<String, String> fields) throws Exception {

		GlJournal returnVal = new GlJournal();

		if(fields.get("glJournalId") != null) {
			returnVal.setGlJournalId((String) fields.get("glJournalId"));
}

		if(fields.get("glJournalName") != null) {
			returnVal.setGlJournalName((String) fields.get("glJournalName"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("isPosted") != null) {
String buf;
buf = fields.get("isPosted");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPosted(ibuf);
}

		if(fields.get("postedDate") != null) {
String buf = fields.get("postedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPostedDate(ibuf);
}


		return returnVal;
 } 
	public static GlJournal map(GenericValue val) {

GlJournal returnVal = new GlJournal();
		returnVal.setGlJournalId(val.getString("glJournalId"));
		returnVal.setGlJournalName(val.getString("glJournalName"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setIsPosted(val.getBoolean("isPosted"));
		returnVal.setPostedDate(val.getTimestamp("postedDate"));


return returnVal;

}

public static GlJournal map(HttpServletRequest request) throws Exception {

		GlJournal returnVal = new GlJournal();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glJournalId")) {
returnVal.setGlJournalId(request.getParameter("glJournalId"));
}

		if(paramMap.containsKey("glJournalName"))  {
returnVal.setGlJournalName(request.getParameter("glJournalName"));
}
		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("isPosted"))  {
String buf = request.getParameter("isPosted");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsPosted(ibuf);
}
		if(paramMap.containsKey("postedDate"))  {
String buf = request.getParameter("postedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPostedDate(ibuf);
}
return returnVal;

}
}
