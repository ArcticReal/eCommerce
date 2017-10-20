package com.skytala.eCommerce.domain.party.relations.party.mapper.nameHistory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.nameHistory.PartyNameHistory;

public class PartyNameHistoryMapper  {


	public static Map<String, Object> map(PartyNameHistory partynamehistory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partynamehistory.getPartyId() != null ){
			returnVal.put("partyId",partynamehistory.getPartyId());
}

		if(partynamehistory.getChangeDate() != null ){
			returnVal.put("changeDate",partynamehistory.getChangeDate());
}

		if(partynamehistory.getGroupName() != null ){
			returnVal.put("groupName",partynamehistory.getGroupName());
}

		if(partynamehistory.getFirstName() != null ){
			returnVal.put("firstName",partynamehistory.getFirstName());
}

		if(partynamehistory.getMiddleName() != null ){
			returnVal.put("middleName",partynamehistory.getMiddleName());
}

		if(partynamehistory.getLastName() != null ){
			returnVal.put("lastName",partynamehistory.getLastName());
}

		if(partynamehistory.getPersonalTitle() != null ){
			returnVal.put("personalTitle",partynamehistory.getPersonalTitle());
}

		if(partynamehistory.getSuffix() != null ){
			returnVal.put("suffix",partynamehistory.getSuffix());
}

		return returnVal;
}


	public static PartyNameHistory map(Map<String, Object> fields) {

		PartyNameHistory returnVal = new PartyNameHistory();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("changeDate") != null) {
			returnVal.setChangeDate((Timestamp) fields.get("changeDate"));
}

		if(fields.get("groupName") != null) {
			returnVal.setGroupName((String) fields.get("groupName"));
}

		if(fields.get("firstName") != null) {
			returnVal.setFirstName((String) fields.get("firstName"));
}

		if(fields.get("middleName") != null) {
			returnVal.setMiddleName((String) fields.get("middleName"));
}

		if(fields.get("lastName") != null) {
			returnVal.setLastName((String) fields.get("lastName"));
}

		if(fields.get("personalTitle") != null) {
			returnVal.setPersonalTitle((String) fields.get("personalTitle"));
}

		if(fields.get("suffix") != null) {
			returnVal.setSuffix((String) fields.get("suffix"));
}


		return returnVal;
 } 
	public static PartyNameHistory mapstrstr(Map<String, String> fields) throws Exception {

		PartyNameHistory returnVal = new PartyNameHistory();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("changeDate") != null) {
String buf = fields.get("changeDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setChangeDate(ibuf);
}

		if(fields.get("groupName") != null) {
			returnVal.setGroupName((String) fields.get("groupName"));
}

		if(fields.get("firstName") != null) {
			returnVal.setFirstName((String) fields.get("firstName"));
}

		if(fields.get("middleName") != null) {
			returnVal.setMiddleName((String) fields.get("middleName"));
}

		if(fields.get("lastName") != null) {
			returnVal.setLastName((String) fields.get("lastName"));
}

		if(fields.get("personalTitle") != null) {
			returnVal.setPersonalTitle((String) fields.get("personalTitle"));
}

		if(fields.get("suffix") != null) {
			returnVal.setSuffix((String) fields.get("suffix"));
}


		return returnVal;
 } 
	public static PartyNameHistory map(GenericValue val) {

PartyNameHistory returnVal = new PartyNameHistory();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setChangeDate(val.getTimestamp("changeDate"));
		returnVal.setGroupName(val.getString("groupName"));
		returnVal.setFirstName(val.getString("firstName"));
		returnVal.setMiddleName(val.getString("middleName"));
		returnVal.setLastName(val.getString("lastName"));
		returnVal.setPersonalTitle(val.getString("personalTitle"));
		returnVal.setSuffix(val.getString("suffix"));


return returnVal;

}

public static PartyNameHistory map(HttpServletRequest request) throws Exception {

		PartyNameHistory returnVal = new PartyNameHistory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("changeDate"))  {
String buf = request.getParameter("changeDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setChangeDate(ibuf);
}
		if(paramMap.containsKey("groupName"))  {
returnVal.setGroupName(request.getParameter("groupName"));
}
		if(paramMap.containsKey("firstName"))  {
returnVal.setFirstName(request.getParameter("firstName"));
}
		if(paramMap.containsKey("middleName"))  {
returnVal.setMiddleName(request.getParameter("middleName"));
}
		if(paramMap.containsKey("lastName"))  {
returnVal.setLastName(request.getParameter("lastName"));
}
		if(paramMap.containsKey("personalTitle"))  {
returnVal.setPersonalTitle(request.getParameter("personalTitle"));
}
		if(paramMap.containsKey("suffix"))  {
returnVal.setSuffix(request.getParameter("suffix"));
}
return returnVal;

}
}
