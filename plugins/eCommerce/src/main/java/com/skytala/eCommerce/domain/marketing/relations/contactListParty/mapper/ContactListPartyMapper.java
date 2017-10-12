package com.skytala.eCommerce.domain.marketing.relations.contactListParty.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.model.ContactListParty;

public class ContactListPartyMapper  {


	public static Map<String, Object> map(ContactListParty contactlistparty) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactlistparty.getContactListId() != null ){
			returnVal.put("contactListId",contactlistparty.getContactListId());
}

		if(contactlistparty.getPartyId() != null ){
			returnVal.put("partyId",contactlistparty.getPartyId());
}

		if(contactlistparty.getFromDate() != null ){
			returnVal.put("fromDate",contactlistparty.getFromDate());
}

		if(contactlistparty.getThruDate() != null ){
			returnVal.put("thruDate",contactlistparty.getThruDate());
}

		if(contactlistparty.getStatusId() != null ){
			returnVal.put("statusId",contactlistparty.getStatusId());
}

		if(contactlistparty.getPreferredContactMechId() != null ){
			returnVal.put("preferredContactMechId",contactlistparty.getPreferredContactMechId());
}

		return returnVal;
}


	public static ContactListParty map(Map<String, Object> fields) {

		ContactListParty returnVal = new ContactListParty();

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("preferredContactMechId") != null) {
			returnVal.setPreferredContactMechId((String) fields.get("preferredContactMechId"));
}


		return returnVal;
 } 
	public static ContactListParty mapstrstr(Map<String, String> fields) throws Exception {

		ContactListParty returnVal = new ContactListParty();

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
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

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("preferredContactMechId") != null) {
			returnVal.setPreferredContactMechId((String) fields.get("preferredContactMechId"));
}


		return returnVal;
 } 
	public static ContactListParty map(GenericValue val) {

ContactListParty returnVal = new ContactListParty();
		returnVal.setContactListId(val.getString("contactListId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPreferredContactMechId(val.getString("preferredContactMechId"));


return returnVal;

}

public static ContactListParty map(HttpServletRequest request) throws Exception {

		ContactListParty returnVal = new ContactListParty();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactListId")) {
returnVal.setContactListId(request.getParameter("contactListId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
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
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("preferredContactMechId"))  {
returnVal.setPreferredContactMechId(request.getParameter("preferredContactMechId"));
}
return returnVal;

}
}
