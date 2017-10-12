package com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactListCommStatus.model.ContactListCommStatus;

public class ContactListCommStatusMapper  {


	public static Map<String, Object> map(ContactListCommStatus contactlistcommstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contactlistcommstatus.getContactListId() != null ){
			returnVal.put("contactListId",contactlistcommstatus.getContactListId());
}

		if(contactlistcommstatus.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",contactlistcommstatus.getCommunicationEventId());
}

		if(contactlistcommstatus.getContactMechId() != null ){
			returnVal.put("contactMechId",contactlistcommstatus.getContactMechId());
}

		if(contactlistcommstatus.getPartyId() != null ){
			returnVal.put("partyId",contactlistcommstatus.getPartyId());
}

		if(contactlistcommstatus.getMessageId() != null ){
			returnVal.put("messageId",contactlistcommstatus.getMessageId());
}

		if(contactlistcommstatus.getStatusId() != null ){
			returnVal.put("statusId",contactlistcommstatus.getStatusId());
}

		if(contactlistcommstatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",contactlistcommstatus.getChangeByUserLoginId());
}

		return returnVal;
}


	public static ContactListCommStatus map(Map<String, Object> fields) {

		ContactListCommStatus returnVal = new ContactListCommStatus();

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("messageId") != null) {
			returnVal.setMessageId((long) fields.get("messageId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static ContactListCommStatus mapstrstr(Map<String, String> fields) throws Exception {

		ContactListCommStatus returnVal = new ContactListCommStatus();

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("messageId") != null) {
String buf;
buf = fields.get("messageId");
long ibuf = Long.parseLong(buf);
			returnVal.setMessageId(ibuf);
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static ContactListCommStatus map(GenericValue val) {

ContactListCommStatus returnVal = new ContactListCommStatus();
		returnVal.setContactListId(val.getString("contactListId"));
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setMessageId(val.getLong("messageId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));


return returnVal;

}

public static ContactListCommStatus map(HttpServletRequest request) throws Exception {

		ContactListCommStatus returnVal = new ContactListCommStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contactListId")) {
returnVal.setContactListId(request.getParameter("contactListId"));
}

		if(paramMap.containsKey("communicationEventId"))  {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("messageId"))  {
String buf = request.getParameter("messageId");
Long ibuf = Long.parseLong(buf);
returnVal.setMessageId(ibuf);
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
return returnVal;

}
}
