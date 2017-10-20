package com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.role.CommunicationEventRole;

public class CommunicationEventRoleMapper  {


	public static Map<String, Object> map(CommunicationEventRole communicationeventrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(communicationeventrole.getCommunicationEventId() != null ){
			returnVal.put("communicationEventId",communicationeventrole.getCommunicationEventId());
}

		if(communicationeventrole.getPartyId() != null ){
			returnVal.put("partyId",communicationeventrole.getPartyId());
}

		if(communicationeventrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",communicationeventrole.getRoleTypeId());
}

		if(communicationeventrole.getContactMechId() != null ){
			returnVal.put("contactMechId",communicationeventrole.getContactMechId());
}

		if(communicationeventrole.getStatusId() != null ){
			returnVal.put("statusId",communicationeventrole.getStatusId());
}

		return returnVal;
}


	public static CommunicationEventRole map(Map<String, Object> fields) {

		CommunicationEventRole returnVal = new CommunicationEventRole();

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static CommunicationEventRole mapstrstr(Map<String, String> fields) throws Exception {

		CommunicationEventRole returnVal = new CommunicationEventRole();

		if(fields.get("communicationEventId") != null) {
			returnVal.setCommunicationEventId((String) fields.get("communicationEventId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}


		return returnVal;
 } 
	public static CommunicationEventRole map(GenericValue val) {

CommunicationEventRole returnVal = new CommunicationEventRole();
		returnVal.setCommunicationEventId(val.getString("communicationEventId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setStatusId(val.getString("statusId"));


return returnVal;

}

public static CommunicationEventRole map(HttpServletRequest request) throws Exception {

		CommunicationEventRole returnVal = new CommunicationEventRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("communicationEventId")) {
returnVal.setCommunicationEventId(request.getParameter("communicationEventId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
return returnVal;

}
}
