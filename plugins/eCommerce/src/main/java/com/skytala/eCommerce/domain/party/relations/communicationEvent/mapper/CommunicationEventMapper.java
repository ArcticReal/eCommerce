package com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.CommunicationEvent;

public class CommunicationEventMapper  {


	public static Map<String, Object> map(CommunicationEvent communicationevent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(communicationevent.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",communicationevent.getCommunicationEventId());
}

		if(communicationevent.getCommunicationEventTypeId() != null ){
			returnVal.put("communicationEventTypeId",communicationevent.getCommunicationEventTypeId());
}

		if(communicationevent.getOrigCommEventId() != null ){
			returnVal.put("origCommEventId",communicationevent.getOrigCommEventId());
}

		if(communicationevent.getParentCommEventId() != null ){
			returnVal.put("parentCommEventId",communicationevent.getParentCommEventId());
}

		if(communicationevent.getStatusId() != null ){
			returnVal.put("statusId",communicationevent.getStatusId());
}

		if(communicationevent.getContactMechTypeId() != null ){
			returnVal.put("contactMechTypeId",communicationevent.getContactMechTypeId());
}

		if(communicationevent.getContactMechIdFrom() != null ){
			returnVal.put("contactMechIdFrom",communicationevent.getContactMechIdFrom());
}

		if(communicationevent.getContactMechIdTo() != null ){
			returnVal.put("contactMechIdTo",communicationevent.getContactMechIdTo());
}

		if(communicationevent.getRoleTypeIdFrom() != null ){
			returnVal.put("roleTypeIdFrom",communicationevent.getRoleTypeIdFrom());
}

		if(communicationevent.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",communicationevent.getRoleTypeIdTo());
}

		if(communicationevent.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",communicationevent.getPartyIdFrom());
}

		if(communicationevent.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",communicationevent.getPartyIdTo());
}

		if(communicationevent.getEntryDate() != null ){
			returnVal.put("entryDate",communicationevent.getEntryDate());
}

		if(communicationevent.getDatetimeStarted() != null ){
			returnVal.put("datetimeStarted",communicationevent.getDatetimeStarted());
}

		if(communicationevent.getDatetimeEnded() != null ){
			returnVal.put("datetimeEnded",communicationevent.getDatetimeEnded());
}

		if(communicationevent.getSubject() != null ){
			returnVal.put("subject",communicationevent.getSubject());
}

		if(communicationevent.getContentMimeTypeId() != null ){
			returnVal.put("contentMimeTypeId",communicationevent.getContentMimeTypeId());
}

		if(communicationevent.getContent() != null ){
			returnVal.put("content",communicationevent.getContent());
}

		if(communicationevent.getNote() != null ){
			returnVal.put("note",communicationevent.getNote());
}

		if(communicationevent.getReasonEnumId() != null ){
			returnVal.put("reasonEnumId",communicationevent.getReasonEnumId());
}

		if(communicationevent.getContactListId() != null ){
			returnVal.put("contactListId",communicationevent.getContactListId());
}

		if(communicationevent.getHeaderString() != null ){
			returnVal.put("headerString",communicationevent.getHeaderString());
}

		if(communicationevent.getFromString() != null ){
			returnVal.put("fromString",communicationevent.getFromString());
}

		if(communicationevent.getToString() != null ){
			returnVal.put("toString",communicationevent.getToString());
}

		if(communicationevent.getCcString() != null ){
			returnVal.put("ccString",communicationevent.getCcString());
}

		if(communicationevent.getBccString() != null ){
			returnVal.put("bccString",communicationevent.getBccString());
}

		if(communicationevent.getMessageId() != null ){
			returnVal.put("messageId",communicationevent.getMessageId());
}

		return returnVal;
}


	public static CommunicationEvent map(Map<String, Object> fields) {

		CommunicationEvent returnVal = new CommunicationEvent();

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("communicationEventTypeId") != null) {
			returnVal.setCommunicationEventTypeId((String) fields.get("communicationEventTypeId"));
}

		if(fields.get("origCommEventId") != null) {
			returnVal.setOrigCommEventId((String) fields.get("origCommEventId"));
}

		if(fields.get("parentCommEventId") != null) {
			returnVal.setParentCommEventId((String) fields.get("parentCommEventId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("contactMechIdFrom") != null) {
			returnVal.setContactMechIdFrom((String) fields.get("contactMechIdFrom"));
}

		if(fields.get("contactMechIdTo") != null) {
			returnVal.setContactMechIdTo((String) fields.get("contactMechIdTo"));
}

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("entryDate") != null) {
			returnVal.setEntryDate((Timestamp) fields.get("entryDate"));
}

		if(fields.get("datetimeStarted") != null) {
			returnVal.setDatetimeStarted((Timestamp) fields.get("datetimeStarted"));
}

		if(fields.get("datetimeEnded") != null) {
			returnVal.setDatetimeEnded((Timestamp) fields.get("datetimeEnded"));
}

		if(fields.get("subject") != null) {
			returnVal.setSubject((String) fields.get("subject"));
}

		if(fields.get("contentMimeTypeId") != null) {
			returnVal.setContentMimeTypeId((String) fields.get("contentMimeTypeId"));
}

		if(fields.get("content") != null) {
			returnVal.setContent((String) fields.get("content"));
}

		if(fields.get("note") != null) {
			returnVal.setNote((String) fields.get("note"));
}

		if(fields.get("reasonEnumId") != null) {
			returnVal.setReasonEnumId((String) fields.get("reasonEnumId"));
}

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("headerString") != null) {
			returnVal.setHeaderString((String) fields.get("headerString"));
}

		if(fields.get("fromString") != null) {
			returnVal.setFromString((String) fields.get("fromString"));
}

		if(fields.get("toString") != null) {
			returnVal.setToString((String) fields.get("toString"));
}

		if(fields.get("ccString") != null) {
			returnVal.setCcString((String) fields.get("ccString"));
}

		if(fields.get("bccString") != null) {
			returnVal.setBccString((String) fields.get("bccString"));
}

		if(fields.get("messageId") != null) {
			returnVal.setMessageId((long) fields.get("messageId"));
}


		return returnVal;
 } 
	public static CommunicationEvent mapstrstr(Map<String, String> fields) throws Exception {

		CommunicationEvent returnVal = new CommunicationEvent();

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("communicationEventTypeId") != null) {
			returnVal.setCommunicationEventTypeId((String) fields.get("communicationEventTypeId"));
}

		if(fields.get("origCommEventId") != null) {
			returnVal.setOrigCommEventId((String) fields.get("origCommEventId"));
}

		if(fields.get("parentCommEventId") != null) {
			returnVal.setParentCommEventId((String) fields.get("parentCommEventId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("contactMechTypeId") != null) {
			returnVal.setContactMechTypeId((String) fields.get("contactMechTypeId"));
}

		if(fields.get("contactMechIdFrom") != null) {
			returnVal.setContactMechIdFrom((String) fields.get("contactMechIdFrom"));
}

		if(fields.get("contactMechIdTo") != null) {
			returnVal.setContactMechIdTo((String) fields.get("contactMechIdTo"));
}

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("entryDate") != null) {
String buf = fields.get("entryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEntryDate(ibuf);
}

		if(fields.get("datetimeStarted") != null) {
String buf = fields.get("datetimeStarted");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDatetimeStarted(ibuf);
}

		if(fields.get("datetimeEnded") != null) {
String buf = fields.get("datetimeEnded");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDatetimeEnded(ibuf);
}

		if(fields.get("subject") != null) {
			returnVal.setSubject((String) fields.get("subject"));
}

		if(fields.get("contentMimeTypeId") != null) {
			returnVal.setContentMimeTypeId((String) fields.get("contentMimeTypeId"));
}

		if(fields.get("content") != null) {
			returnVal.setContent((String) fields.get("content"));
}

		if(fields.get("note") != null) {
			returnVal.setNote((String) fields.get("note"));
}

		if(fields.get("reasonEnumId") != null) {
			returnVal.setReasonEnumId((String) fields.get("reasonEnumId"));
}

		if(fields.get("contactListId") != null) {
			returnVal.setContactListId((String) fields.get("contactListId"));
}

		if(fields.get("headerString") != null) {
			returnVal.setHeaderString((String) fields.get("headerString"));
}

		if(fields.get("fromString") != null) {
			returnVal.setFromString((String) fields.get("fromString"));
}

		if(fields.get("toString") != null) {
			returnVal.setToString((String) fields.get("toString"));
}

		if(fields.get("ccString") != null) {
			returnVal.setCcString((String) fields.get("ccString"));
}

		if(fields.get("bccString") != null) {
			returnVal.setBccString((String) fields.get("bccString"));
}

		if(fields.get("messageId") != null) {
String buf;
buf = fields.get("messageId");
long ibuf = Long.parseLong(buf);
			returnVal.setMessageId(ibuf);
}


		return returnVal;
 } 
	public static CommunicationEvent map(GenericValue val) {

CommunicationEvent returnVal = new CommunicationEvent();
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));
		returnVal.setCommunicationEventTypeId(val.getString("communicationEventTypeId"));
		returnVal.setOrigCommEventId(val.getString("origCommEventId"));
		returnVal.setParentCommEventId(val.getString("parentCommEventId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setContactMechTypeId(val.getString("contactMechTypeId"));
		returnVal.setContactMechIdFrom(val.getString("contactMechIdFrom"));
		returnVal.setContactMechIdTo(val.getString("contactMechIdTo"));
		returnVal.setRoleTypeIdFrom(val.getString("roleTypeIdFrom"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setEntryDate(val.getTimestamp("entryDate"));
		returnVal.setDatetimeStarted(val.getTimestamp("datetimeStarted"));
		returnVal.setDatetimeEnded(val.getTimestamp("datetimeEnded"));
		returnVal.setSubject(val.getString("subject"));
		returnVal.setContentMimeTypeId(val.getString("contentMimeTypeId"));
		returnVal.setContent(val.getString("content"));
		returnVal.setNote(val.getString("note"));
		returnVal.setReasonEnumId(val.getString("reasonEnumId"));
		returnVal.setContactListId(val.getString("contactListId"));
		returnVal.setHeaderString(val.getString("headerString"));
		returnVal.setFromString(val.getString("fromString"));
		returnVal.setToString(val.getString("toString"));
		returnVal.setCcString(val.getString("ccString"));
		returnVal.setBccString(val.getString("bccString"));
		returnVal.setMessageId(val.getLong("messageId"));


return returnVal;

}

public static CommunicationEvent map(HttpServletRequest request) throws Exception {

		CommunicationEvent returnVal = new CommunicationEvent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("communicationEventId")) {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}

		if(paramMap.containsKey("communicationEventTypeId"))  {
returnVal.setCommunicationEventTypeId(request.getParameter("communicationEventTypeId"));
}
		if(paramMap.containsKey("origCommEventId"))  {
returnVal.setOrigCommEventId(request.getParameter("origCommEventId"));
}
		if(paramMap.containsKey("parentCommEventId"))  {
returnVal.setParentCommEventId(request.getParameter("parentCommEventId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("contactMechTypeId"))  {
returnVal.setContactMechTypeId(request.getParameter("contactMechTypeId"));
}
		if(paramMap.containsKey("contactMechIdFrom"))  {
returnVal.setContactMechIdFrom(request.getParameter("contactMechIdFrom"));
}
		if(paramMap.containsKey("contactMechIdTo"))  {
returnVal.setContactMechIdTo(request.getParameter("contactMechIdTo"));
}
		if(paramMap.containsKey("roleTypeIdFrom"))  {
returnVal.setRoleTypeIdFrom(request.getParameter("roleTypeIdFrom"));
}
		if(paramMap.containsKey("roleTypeIdTo"))  {
returnVal.setRoleTypeIdTo(request.getParameter("roleTypeIdTo"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
}
		if(paramMap.containsKey("entryDate"))  {
String buf = request.getParameter("entryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEntryDate(ibuf);
}
		if(paramMap.containsKey("datetimeStarted"))  {
String buf = request.getParameter("datetimeStarted");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDatetimeStarted(ibuf);
}
		if(paramMap.containsKey("datetimeEnded"))  {
String buf = request.getParameter("datetimeEnded");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDatetimeEnded(ibuf);
}
		if(paramMap.containsKey("subject"))  {
returnVal.setSubject(request.getParameter("subject"));
}
		if(paramMap.containsKey("contentMimeTypeId"))  {
returnVal.setContentMimeTypeId(request.getParameter("contentMimeTypeId"));
}
		if(paramMap.containsKey("content"))  {
returnVal.setContent(request.getParameter("content"));
}
		if(paramMap.containsKey("note"))  {
returnVal.setNote(request.getParameter("note"));
}
		if(paramMap.containsKey("reasonEnumId"))  {
returnVal.setReasonEnumId(request.getParameter("reasonEnumId"));
}
		if(paramMap.containsKey("contactListId"))  {
returnVal.setContactListId(request.getParameter("contactListId"));
}
		if(paramMap.containsKey("headerString"))  {
returnVal.setHeaderString(request.getParameter("headerString"));
}
		if(paramMap.containsKey("fromString"))  {
returnVal.setFromString(request.getParameter("fromString"));
}
		if(paramMap.containsKey("toString"))  {
returnVal.setToString(request.getParameter("toString"));
}
		if(paramMap.containsKey("ccString"))  {
returnVal.setCcString(request.getParameter("ccString"));
}
		if(paramMap.containsKey("bccString"))  {
returnVal.setBccString(request.getParameter("bccString"));
}
		if(paramMap.containsKey("messageId"))  {
String buf = request.getParameter("messageId");
Long ibuf = Long.parseLong(buf);
returnVal.setMessageId(ibuf);
}
return returnVal;

}
}
